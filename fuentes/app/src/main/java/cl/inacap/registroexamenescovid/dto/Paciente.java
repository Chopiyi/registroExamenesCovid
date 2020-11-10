package cl.inacap.registroexamenescovid.dto;

import android.app.DatePickerDialog;

import java.sql.Date;

public class Paciente {

    private String rut;
    private String nombre;
    private String apellido;
    private String fecha;
    private String area_trabajo;
    private boolean sintomas;
    private float temperatura;
    private boolean tos;
    private int presion;


    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getArea_trabajo() {
        return area_trabajo;
    }

    public void setArea_trabajo(String area_trabajo) {
        this.area_trabajo = area_trabajo;
    }

    public boolean isSintomas() {
        return sintomas;
    }

    public void setSintomas(boolean sintomas) {
        this.sintomas = sintomas;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public boolean isTos() {
        return tos;
    }

    public void setTos(boolean tos) {
        this.tos = tos;
    }

    public int getPresion() {
        return presion;
    }

    public void setPresion(int presion) {
        this.presion = presion;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fecha=" + fecha +
                ", area_trabajo='" + area_trabajo + '\'' +
                ", sintomas=" + sintomas +
                ", temperatura=" + temperatura +
                ", tos=" + tos +
                ", presion=" + presion +
                '}';
    }
}

