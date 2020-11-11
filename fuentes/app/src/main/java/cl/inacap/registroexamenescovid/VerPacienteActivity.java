package cl.inacap.registroexamenescovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cl.inacap.registroexamenescovid.dto.Paciente;

public class VerPacienteActivity extends AppCompatActivity {

    private Paciente paciente;
    private TextView rut;
    private TextView nombre;
    private TextView apellido;
    private TextView areaTrabajo;
    private TextView sintomas;
    private TextView tos;
    private TextView temperatura;
    private TextView presion;
    private TextView fecha;
    private Toolbar toolbar;
    private ImageView toolbar_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_paciente);
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
        this.rut = findViewById(R.id.ver_rut);
        this.nombre = findViewById(R.id.ver_nombre);
        this.apellido = findViewById((R.id.ver_apellido));
        this.fecha = findViewById(R.id.ver_fecha);
        this.areaTrabajo = findViewById(R.id.ver_area);
        this.sintomas = findViewById(R.id.ver_sintomas);
        this.tos = findViewById(R.id.ver_toos);
        this.temperatura = findViewById(R.id.ver_temperatura);
        this.presion = findViewById(R.id.ver_presion);
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(getIntent().getExtras() != null){
            this.paciente = (Paciente) getIntent().getSerializableExtra("paciente");
            this.rut.setText(paciente.getRut());
            this.nombre.setText(paciente.getNombre());
            this.apellido.setText(paciente.getApellido());
            this.fecha.setText("Fecha de examen: " + paciente.getFecha());
            this.areaTrabajo.setText("Area de trabajo: " + paciente.getArea_trabajo());
            if(paciente.isSintomas()){
                this.sintomas.setText("Tiene síntomas de COVID");
            } else {
                this.sintomas.setText("No presenta síntomas");
            }
            if(paciente.isTos()){
                this.tos.setText("Presenta tos");
            } else {
                this.tos.setText("No presenta tos");
            }
            this.temperatura.setText("Temperatura: " + String.valueOf(paciente.getTemperatura()));
            this.presion.setText("Presion arterial: " + String.valueOf(paciente.getPresion()));
        }

    }
}