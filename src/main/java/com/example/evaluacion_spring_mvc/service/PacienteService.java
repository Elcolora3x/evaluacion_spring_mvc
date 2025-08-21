package com.example.evaluacion_spring_mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.evaluacion_spring_mvc.model.Paciente;
import com.example.evaluacion_spring_mvc.repository.PacienteRepository;


@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> getPacientes(){
        return pacienteRepository.getPacientes();
    }

    public Paciente getPacienteById (Long id){
        return pacienteRepository.getPacientes().stream().filter(u -> u.getId().equals(id)).findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("El paciente con ID %s no se encuentra en el sistema.", id)));
    }

    public Paciente createPaciente (Paciente paciente){
        if(pacienteRepository.getPacientes().stream().anyMatch(u -> u.getId().equals(paciente.getId()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, 
            String.format("El paciente con ID %s ya se encuentra en el sistema.", paciente.getId())); 
        }
        pacienteRepository.getPacientes().add(paciente);
        return paciente;
    }

    public void deletePaciente(Long id){
        pacienteRepository.eliminarPaciente(id);
    }
}
