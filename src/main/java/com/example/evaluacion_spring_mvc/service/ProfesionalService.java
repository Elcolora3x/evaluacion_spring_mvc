package com.example.evaluacion_spring_mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.evaluacion_spring_mvc.model.Profesional;
import com.example.evaluacion_spring_mvc.repository.ProfesionalRepository;

@Service
public class ProfesionalService {

    @Autowired
    private ProfesionalRepository profesionalRepository;

    public List<Profesional> getProfesionales(){
        return profesionalRepository.getProfesionales();
    }
    
    public Profesional createProfesional(Profesional profesional){
         if(profesionalRepository.getProfesionales().stream().anyMatch(u -> u.getId().equals(profesional.getId()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, 
            String.format("El profesional con ID %s ya se encuentra en el sistema.", profesional.getId())); 
        }
        profesionalRepository.agregarProfesional(profesional);
        return profesional;
    }

    public List<Profesional> ListarPorEspecialidad(String especialidad){
        List<Profesional> listadoEspecialidad = new ArrayList<>();

        for (Profesional profesional : profesionalRepository.getProfesionales()) {
            if(profesional.getEspecialidad().equals(especialidad)){
                listadoEspecialidad.add(profesional);
            }
        }
        return  listadoEspecialidad;
    }
}
