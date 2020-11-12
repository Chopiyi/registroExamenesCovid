package cl.inacap.registroexamenescovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;


import com.squareup.picasso.Picasso;

import cl.inacap.registroexamenescovid.adapters.AdaptadorListaPaciente;

public class VerPacienteActivity extends AppCompatActivity {

    private String[] paciente;
    private Toolbar toolbar;
    private ImageView toolbar_image;
    private AdaptadorListaPaciente adapter;
    private ListView paciente_lv;

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
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(getIntent().getExtras() != null){
            this.paciente_lv = findViewById(R.id.lista_detalle_paciente);
            this.paciente = (String[]) getIntent().getSerializableExtra("paciente");
            this.adapter = new AdaptadorListaPaciente(VerPacienteActivity.this, R.layout.paciente_list, paciente);
            this.paciente_lv.setAdapter(adapter);
            this.paciente_lv.setVisibility(View.VISIBLE);
        }

    }
}