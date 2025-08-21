package com.example.evaluacion_spring_mvc.model;

public class Profesional {
    
    private Long id;
    private String nombre;
    private String apellido;
    private String especialidad;
    
    
    
    public Profesional(Long id, String nombre, String apellido, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
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
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    @Override
    public String toString() {
        return "Profesional [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", especialidad="
                + especialidad + "]";
    }
    
     @Override
    public boolean equals(Object obj) {

        final Profesional profesional = (Profesional) obj;

        if(this.id.equals(profesional.id) && this.especialidad.equals(profesional.especialidad)){
            return true;
        }

        return false;
    }

}
