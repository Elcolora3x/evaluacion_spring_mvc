package com.example.evaluacion_spring_mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.evaluacion_spring_mvc.model.Profesional;

import jakarta.annotation.PostConstruct;

@Service
public class ProfesionalService {

    private List<Profesional> profesionales = new ArrayList<>();
    public List<Profesional> listadoEspecialidad = new ArrayList<>();

    @PostConstruct
    public void init(){
        Profesional p1 = new Profesional(1L, "Nicolas", "Francos", "43538408");
        Profesional p2 = new Profesional(2L, "Alejandro", "Sergi", "17565437");
        Profesional p3 = new Profesional(3L, "Ozzie", "Osbourne", "17423537");
        profesionales.add(p1);
        profesionales.add(p2);
        profesionales.add(p3);
    }

    public Profesional createProfesional(Profesional profesional){
         if(profesionales.stream().anyMatch(u -> u.getId().equals(profesional.getId()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, 
            String.format("El profesional con ID %s ya se encuentra en el sistema.", profesional.getId())); 
        }
        profesionales.add(profesional);
        return profesional;
    }

    public List<Profesional> ListarPorEspecialidad(String especialidad){
    
        for (Profesional profesional : profesionales) {
            if(profesional.getEspecialidad().equals(especialidad)){
                listadoEspecialidad.add(profesional);
            }
        }
        return  listadoEspecialidad;
    }
}
