package com.example.evaluacion_spring_mvc.model;

public class Profesional {
    
    private Long id;
    private String nombreCompleto;
    private String especialidad;
    
    public Profesional(Long id, String nombreCompleto, String especialidad) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
    }
    public Profesional() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    @Override
    public String toString() {
        return "Profesional [id=" + id + ", nombreCompleto=" + nombreCompleto + ", especialidad=" + especialidad + "]";
    }

}
