package com.example.evaluacion_spring_mvc.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Turno {
    private Long id;
    private Paciente paciente;
    private Profesional profesional;
    private LocalDate fecha;
    
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Turno(Long id, Paciente paciente, Profesional profesional, String fecha) {
        this.formatoCorrecto(fecha);

        this.id = id;
        this.paciente = paciente;
        this.profesional = profesional;
        this.fecha = LocalDate.parse(fecha,dtf);
    }

    public Turno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public LocalDate getFecha(){
        return fecha;
    }

    public void setFecha(String fecha){
        this.formatoCorrecto(fecha);

        this.fecha = LocalDate.parse(fecha,dtf);
    }

    public boolean formatoCorrecto(String fecha){
        
        try {
            LocalDate.parse(fecha, dtf);
            return true; 
        } catch (DateTimeParseException e) {
            return false; 
        }
    }
    
    @Override
    public String toString() {
        return "Turno [id=" + id + ", paciente=" + paciente + ", profesional=" + profesional + ", fecha=" + fecha + "]";
    }
    
    @Override
    public boolean equals(Object obj) {

        final Turno turno = (Turno) obj;

        if(this.profesional.equals(turno.profesional) && this.paciente.equals(turno.paciente) && this.fecha.isEqual(turno.fecha)){
            return true;
        }

        return false;
    }
    
}
