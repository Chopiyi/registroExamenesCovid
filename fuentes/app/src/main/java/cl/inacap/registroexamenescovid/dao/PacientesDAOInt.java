package cl.inacap.registroexamenescovid.dao;

import java.util.List;

import cl.inacap.registroexamenescovid.dto.Paciente;

public interface PacientesDAOInt {

    void save(Paciente p);
    void remove(Paciente p);
    List<Paciente> getAll();
    Paciente findByRut(String rut);

}
