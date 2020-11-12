package cl.inacap.registroexamenescovid.adapters;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cl.inacap.registroexamenescovid.R;


public class AdaptadorListaPaciente extends ArrayAdapter<String> {

    private String [] paciente;
    private Activity activity;


    public AdaptadorListaPaciente(@NonNull Activity context, int resource, @NonNull String[] objects) {
        super(context, resource, objects);
        this.activity = context;
        this.paciente = objects;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.paciente_list, null, true);
        TextView rut = view.findViewById(R.id.ver_rut);
        TextView nombre = view.findViewById(R.id.ver_nombre);
        TextView apellido = view.findViewById(R.id.ver_apellido);
        TextView presion = view.findViewById(R.id.ver_presion);
        TextView area = view.findViewById(R.id.ver_area);
        TextView tos = view.findViewById(R.id.ver_toos);
        TextView temperatura = view.findViewById(R.id.ver_temperatura);
        TextView sintomas = view.findViewById(R.id.ver_sintomas);
        TextView fecha = view.findViewById(R.id.ver_fecha);
        rut.setText(this.paciente[0]);
        nombre.setText(this.paciente[1]);
        apellido.setText(this.paciente[2]);
        area.setText(this.paciente[3]);
        fecha.setText(this.paciente[4]);
        sintomas.setText(this.paciente[5]);
        tos.setText(this.paciente[6]);
        temperatura.setText(this.paciente[7]);
        presion.setText(this.paciente[8]);
        return view;
    }
}
