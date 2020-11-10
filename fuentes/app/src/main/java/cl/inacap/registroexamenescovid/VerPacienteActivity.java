package cl.inacap.registroexamenescovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

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
    private TextView fecha;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_paciente);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
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