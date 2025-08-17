package com.example.evaluacion_spring_mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.evaluacion_spring_mvc.model.Paciente;

@Service
public class PacienteService {

    private List<Paciente> pacientes = new ArrayList<>();

    public List<Paciente> getPacientes(){
        return pacientes;
    }

    public Paciente getId (Long id){
        return pacientes.stream().filter(u -> u.getId().equals(id)).findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("El paciente con ID %s no se encuentra.", id)));
    }

    public Paciente createPaciente (Paciente paciente){
        if(pacientes.stream().anyMatch(u -> u.getId().equals(paciente.getId()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, 
            String.format("El paciente con ID %s ya se encuentra en el sistema.", paciente.getId())); 
        }
        pacientes.add(paciente);
        return paciente;
    }
}
