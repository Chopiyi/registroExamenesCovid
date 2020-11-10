package cl.inacap.registroexamenescovid.dao;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.registroexamenescovid.dto.Paciente;

public class PacientesDAO implements PacientesDAOInt{

    private static List<Paciente> listaPacientes = new ArrayList<>();
    private static PacientesDAO instancia;

    public static PacientesDAO getInstance(){
        if(instancia == null){
            instancia = new PacientesDAO();
        }
        return instancia;
    }

    private PacientesDAO() {
    }

    @Override
    public void save(Paciente p) {

    }

    @Override
    public void remove(Paciente p) {

    }

    @Override
    public List<Paciente> getAll() {
        return null;
    }

    @Override
    public Paciente findByRut(String rut) {
        return null;
    }
}
