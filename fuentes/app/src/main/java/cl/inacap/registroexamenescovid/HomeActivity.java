package cl.inacap.registroexamenescovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

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
    private ImageView toolbar_image;
    private Toolbar toolbar;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.toolbar_image = findViewById(R.id.imagen_toolbar);
        Picasso.get().load("https://cdn.pixabay.com/photo/2020/03/23/10/26/covid-19-4960254_960_720.png").resize(102, 59).centerCrop().into(this.toolbar_image);
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
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RegistrarPacienteActivity.class);
                startActivity(intent);
            }
        });
        pacientes_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeActivity.this, VerPacienteActivity.class);
                intent.putExtra("paciente", listaPacientes.get(i));
                startActivity(intent);
            }
        });



    }
}