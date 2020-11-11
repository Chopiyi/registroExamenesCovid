package cl.inacap.registroexamenescovid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cl.inacap.registroexamenescovid.dao.PacientesDAOSQLite;
import cl.inacap.registroexamenescovid.dto.Paciente;

public class RegistrarPacienteActivity extends AppCompatActivity {

    private EditText rut;
    private EditText nombre;
    private EditText apellido;
    private EditText fecha;
    private Spinner area;
    private Switch sintomas;
    private Switch tos;
    private EditText temperatura;
    private EditText presion;
    private Button boton_agregar;
    private PacientesDAOSQLite pacientesDAO = new PacientesDAOSQLite(RegistrarPacienteActivity.this);
    private ArrayAdapter<CharSequence> adapter;
    private Toolbar toolbar;
    private ImageView toolbar_image;

    public static Boolean validaRut ( String rut ) {
        Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
        Matcher matcher = pattern.matcher(rut);
        if ( matcher.matches() == false ) return false;
        String[] stringRut = rut.split("-");
        return stringRut[1].toLowerCase().equals(MainActivity.dv(stringRut[0]));
    }

    public static String dv ( String rut ) {
        Integer M=0,S=1,T=Integer.parseInt(rut);
        for (;T!=0;T=(int) Math.floor(T/=10))
            S=(S+T%10*(9-M++%6))%11;
        return ( S > 0 ) ? String.valueOf(S-1) : "k";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_paciente);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.toolbar_image = findViewById(R.id.imagen_toolbar);
        Picasso.get().load("https://cdn.pixabay.com/photo/2020/03/23/10/26/covid-19-4960254_960_720.png").resize(102, 59).centerCrop().into(this.toolbar_image);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.rut = findViewById(R.id.registro_rut);
        this.nombre = findViewById(R.id.registro_nombre);
        this.apellido = findViewById(R.id.registro_apellido);
        this.fecha = findViewById(R.id.registro_fecha);
        this.area = findViewById(R.id.registro_area);
        this.sintomas = findViewById(R.id.registro_sintomas);
        this.tos = findViewById(R.id.registro_tos);
        this.temperatura = findViewById(R.id.registro_temperatura);
        this.presion = findViewById(R.id.registro_presion);
        this.boton_agregar = findViewById(R.id.boton_registrar);
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        Calendar calendar = Calendar.getInstance();
        this.adapter = ArrayAdapter.createFromResource(this, R.array.selector_area, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area.setAdapter(adapter);
        final int year = calendar.YEAR;
        final int month = calendar.MONTH;
        final int day = calendar.DAY_OF_MONTH;

        this.fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrarPacienteActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month +1;
                        String date = day + "/" + month + "/" + year;
                        fecha.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        this.boton_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                if ( rut.getText().toString().isEmpty() || validaRut(rut.getText().toString()) == false){
                    errores.add("El rut no es válido");
                }
                if (nombre.getText().toString().isEmpty()){
                    errores.add("No ingresó el nombre");
                }
                if(apellido.getText().toString().isEmpty()){
                    errores.add("No ingresó el apellido");
                }
                if(fecha.getText().toString().isEmpty()){
                    errores.add("No seleccionó la fecha");
                }
                if(temperatura.getText().toString().isEmpty() ){
                    errores.add("La temperatura puede ser entero o decimal");
                }
                if(presion.getText().toString().isEmpty()){
                    errores.add("No ingresó la presión");
                }
                if(area.getSelectedItemPosition() == 0){
                    errores.add("No seleccionó el área de trabajo");
                }

                if (errores.isEmpty()){
                    Paciente p = new Paciente();
                    p.setRut(rut.getText().toString());
                    p.setNombre(nombre.getText().toString());
                    p.setApellido(apellido.getText().toString());
                    p.setFecha(fecha.getText().toString());
                    p.setArea_trabajo(area.getSelectedItem().toString());
                    p.setSintomas(sintomas.isChecked());
                    p.setTos(tos.isChecked());
                    p.setTemperatura(Float.valueOf(temperatura.getText().toString()));
                    p.setPresion(Integer.parseInt(presion.getText().toString()));
                    pacientesDAO.save(p);
                    startActivity(new Intent(RegistrarPacienteActivity.this, HomeActivity.class));
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarPacienteActivity.this);
                    builder.setTitle("Error en el ingreso");
                    builder.setMessage(errores.toString().replace(',', '\n').replace('[', ' ').replace(']', ' '));
                    builder.setPositiveButton("Aceptar", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        });

    }
}