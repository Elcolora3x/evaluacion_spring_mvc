package com.example.evaluacion_spring_mvc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.evaluacion_spring_mvc.model.Paciente;

import jakarta.annotation.PostConstruct;

@Component
public class PacienteRepository {
    private List<Paciente> listaPacientes = new ArrayList<>();

    @PostConstruct
    public void init(){
        Paciente p1 = new Paciente(1L, "Nicolas", "Francos", "43538408", "nfrancos@mobydigital.com");
        Paciente p2 = new Paciente(2L, "Alejandro", "Sergi", "17565437", "asergi@miranda.com");
        Paciente p3 = new Paciente(3L, "Ozzie", "Osbourne", "17423537", "ozzie@osbourne.com");
        listaPacientes.add(p1);
        listaPacientes.add(p2);
        listaPacientes.add(p3);
    }
    
    public List<Paciente> getPacientes(){
        return listaPacientes;
    }

    public Paciente agregarPaciente(Paciente paciente){
        listaPacientes.add(paciente);
        return paciente;
    }

    public Paciente buscarPorId(Long id){
        return listaPacientes.stream().filter(u -> u.getId().equals(id)).findAny()
        .orElse(null);
    }
    
    public void eliminarPaciente(Long id){
        Paciente paciente = buscarPorId(id);
        listaPacientes.remove(paciente);
    }
}
