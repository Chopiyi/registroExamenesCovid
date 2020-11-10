package cl.inacap.registroexamenescovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import cl.inacap.registroexamenescovid.adapters.AdaptadorListaExamenes;
import cl.inacap.registroexamenescovid.dao.PacientesDAOSQLite;
import cl.inacap.registroexamenescovid.dto.Paciente;

public class HomeActivity extends AppCompatActivity {

    private List<Paciente> listaPacientes;
    private PacientesDAOSQLite pacientesDAO = new PacientesDAOSQLite(this);
    private ListView pacientes_lv;
    private AdaptadorListaExamenes adaptador;
    private View button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.pacientes_lv = findViewById(R.id.lista_examenes);
        this.button = findViewById(R.id.home_agregar);
        this.listaPacientes = this.pacientesDAO.getAll();
        this.adaptador = new AdaptadorListaExamenes(this, R.layout.examenes_list, this.listaPacientes);
        this.pacientes_lv.setAdapter(this.adaptador);
        this.pacientes_lv.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RegistrarPacienteActivity.class);
                startActivity(intent);
            }
        });



    }
}