package cl.inacap.registroexamenescovid.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


import cl.inacap.registroexamenescovid.dto.Paciente;
import cl.inacap.registroexamenescovid.helper.PacienteDBOpenHelper;

public class PacientesDAOSQLite implements PacientesDAOInt {

    private PacienteDBOpenHelper db;

    public PacientesDAOSQLite(Context contexto) {
        this.db = new PacienteDBOpenHelper(contexto, "DBPacientes", null, 1);
    }

    @Override
    public void save(Paciente p) {
        SQLiteDatabase writer = this.db.getWritableDatabase();
        String sql = String.format("INSERT INTO paciente(" + "rut, nombre, apellido, fecha, area_trabajo, sintomas, temperatura, tos, presion) " +
                "VALUES('%s', '%s', '%s', '%s', '%s', '%b', '%f', '%b', '%d')",
                p.getRut(), p.getNombre(), p.getApellido(), p.getFecha(), p.getArea_trabajo(), p.isSintomas(), p.getTemperatura(), p.isTos(), p.getPresion());
        writer.execSQL(sql);
        writer.close();
    }

    @Override
    public void remove(Paciente p) {

    }

    @Override
    public List<Paciente> getAll() {

        SQLiteDatabase reader = this.db.getReadableDatabase();
        List<Paciente> listaPacientes = new ArrayList<>();
        try {
            if(reader != null){
                Cursor c = reader.rawQuery("SELECT rut, nombre, apellido, fecha, area_trabajo, sintomas, temperatura, tos, presion FROM paciente", null);
                if(c.moveToFirst()){
                    do {
                        Paciente p = new Paciente();
                        p.setRut(c.getString(0));
                        p.setNombre(c.getString(1));
                        p.setApellido(c.getString(2));
                        p.setFecha(c.getString(3));
                        p.setArea_trabajo(c.getString(4));
                        p.setSintomas(Boolean.valueOf(c.getString(5)));
                        p.setTemperatura(c.getFloat(6));
                        p.setTos(Boolean.valueOf(c.getString(7)));
                        p.setPresion(c.getInt(8));
                        listaPacientes.add(p);
                    } while(c.moveToNext());
                }
                reader.close();
            }
        } catch (SQLException ex){
            System.out.println(ex);
            listaPacientes = null;
        }
        return listaPacientes;
    }

    @Override
    public Paciente findByRut(String rut) {
        return null;
    }
}
