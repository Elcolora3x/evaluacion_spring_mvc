package com.example.evaluacion_spring_mvc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.evaluacion_spring_mvc.model.Turno;

import jakarta.annotation.PostConstruct;

@Component
public class TurnoRepository {
    private List<Turno> listaTurnos = new ArrayList<>();

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    ProfesionalRepository profesionalRepository;

    @PostConstruct
    public void init(){
        
        Turno t1 = new Turno(1L, pacienteRepository.getPacientes().get(0), profesionalRepository.getProfesionales().get(0), "2001-08-13");
        Turno t2 = new Turno(2L, pacienteRepository.getPacientes().get(1), profesionalRepository.getProfesionales().get(1), "2001-08-13");
        Turno t3 = new Turno(3L, pacienteRepository.getPacientes().get(2), profesionalRepository.getProfesionales().get(2), "2001-08-13");
        listaTurnos.add(t1);
        listaTurnos.add(t2);
        listaTurnos.add(t3);
    }

    public List<Turno> getTurnos(){
        return listaTurnos;
    }

    public Turno agregarTurno(Turno turno){
        listaTurnos.add(turno);
        return turno;
    }

    public Turno buscarPorId(Long id){
        return listaTurnos.stream().filter(u -> u.getId().equals(id)).findAny()
        .orElse(null);
    }
    
    public void eliminarTurno(Long id){
        Turno turno = buscarPorId(id);
        listaTurnos.remove(turno);
    }
}
