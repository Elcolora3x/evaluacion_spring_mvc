package com.example.evaluacion_spring_mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.evaluacion_spring_mvc.model.Paciente;

import jakarta.annotation.PostConstruct;

@Service
public class PacienteService {

    private List<Paciente> pacientes = new ArrayList<>();

    @PostConstruct
    public void init(){
        Paciente p1 = new Paciente(1L, "Nicolas", "Francos", "43538408", "nfrancos@mobydigital.com");
        Paciente p2 = new Paciente(2L, "Alejandro", "Sergi", "17565437", "asergi@miranda.com");
        Paciente p3 = new Paciente(3L, "Ozzie", "Osbourne", "17423537", "ozzie@osbourne.com");
        pacientes.add(p1);
        pacientes.add(p2);
        pacientes.add(p3);
    }

    public List<Paciente> getPacientes(){
        return pacientes;
    }

    public Paciente getPacienteById (Long id){
        return pacientes.stream().filter(u -> u.getId().equals(id)).findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("El paciente con ID %s no se encuentra en el sistema.", id)));
    }

    public Paciente createPaciente (Paciente paciente){
        if(pacientes.stream().anyMatch(u -> u.getId().equals(paciente.getId()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, 
            String.format("El paciente con ID %s ya se encuentra en el sistema.", paciente.getId())); 
        }
        pacientes.add(paciente);
        return paciente;
    }

    public void deletePaciente(Long id){
        Paciente paciente = getPacienteById(id);
        pacientes.remove(paciente);
    }
}
