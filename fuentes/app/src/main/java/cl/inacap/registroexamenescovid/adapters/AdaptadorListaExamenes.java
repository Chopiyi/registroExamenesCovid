package cl.inacap.registroexamenescovid.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import cl.inacap.registroexamenescovid.R;
import cl.inacap.registroexamenescovid.dto.Paciente;

public class AdaptadorListaExamenes extends ArrayAdapter<Paciente> {

    private List<Paciente> listaPacientes;
    private Activity activity;

    public AdaptadorListaExamenes(@NonNull Activity context, int resource, @NonNull List<Paciente> objects) {
        super(context, resource, objects);
        this.listaPacientes = objects;
        this.activity = context;
    }

    @Override
    public int getCount() {
        return this.listaPacientes.size();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View fila = inflater.inflate(R.layout.examenes_list, null, true);
        TextView rut = fila.findViewById(R.id.home_rut);
        TextView nombre = fila.findViewById(R.id.home_nombre);
        TextView apellido = fila.findViewById(R.id.home_apellido);
        TextView fecha = fila.findViewById(R.id.homa_fecha);
        ImageView alerta_covid = fila.findViewById(R.id.home_imagen);
        Paciente paciente = listaPacientes.get(i);
        nombre.setText(paciente.getNombre());
        apellido.setText(paciente.getApellido());
        fecha.setText(paciente.getFecha());
        rut.setText(paciente.getRut());
        if(paciente.isSintomas()){
            alerta_covid.setImageResource(R.drawable.logo_covid_positivo);
        } else {
            alerta_covid.setImageResource(R.drawable.logo_sin_sintomas);
        }
        return fila;
    }
}
