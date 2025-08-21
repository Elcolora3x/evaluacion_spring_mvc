package com.example.evaluacion_spring_mvc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.evaluacion_spring_mvc.model.Profesional;

import jakarta.annotation.PostConstruct;

@Component
public class ProfesionalRepository {
    private List<Profesional> listaProfesionales = new ArrayList<>();

    @PostConstruct
    public void init(){
        Profesional p1 = new Profesional(1L, "Nicolas", "Francos", "Gato");
        Profesional p2 = new Profesional(2L, "Alejandro", "Sergi", "Pediatra");
        Profesional p3 = new Profesional(3L, "Ozzie", "Osbourne", "Guardia");
        listaProfesionales.add(p1);
        listaProfesionales.add(p2);
        listaProfesionales.add(p3);
    }

    public List<Profesional> getProfesionales(){
        return listaProfesionales;
    }

    public Profesional agregarProfesional(Profesional profesional){
        listaProfesionales.add(profesional);
        return profesional;
    }

}
