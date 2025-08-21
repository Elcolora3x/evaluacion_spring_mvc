package com.example.evaluacion_spring_mvc.model;

public class Paciente {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
   
    
    public Paciente(Long id, String nombre, String apellido, String dni, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
    }

    public Paciente() {
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
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Paciente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", email="
                + email + "]";
    }
    
    @Override
    public boolean equals(Object obj) {

        final Paciente paciente = (Paciente) obj;

        if(this.dni.equals(paciente.dni)){
            return true;
        }

        return false;
    }
}
