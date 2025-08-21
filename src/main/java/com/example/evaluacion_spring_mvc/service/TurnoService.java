package com.example.evaluacion_spring_mvc.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.evaluacion_spring_mvc.model.Paciente;
import com.example.evaluacion_spring_mvc.model.Profesional;
import com.example.evaluacion_spring_mvc.model.Turno;
import com.example.evaluacion_spring_mvc.repository.PacienteRepository;
import com.example.evaluacion_spring_mvc.repository.ProfesionalRepository;
import com.example.evaluacion_spring_mvc.repository.TurnoRepository;


@Service
public class TurnoService {

    @Autowired
    TurnoRepository turnoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    ProfesionalRepository profesionalRepository;

    public List<Turno> getTurnos(){
        return turnoRepository.getTurnos();
    }
    
    public List<Turno> getTurnosByFecha(String fecha){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaParseada = LocalDate.parse(fecha, dtf);
        List<Turno> listaFecha = new ArrayList<>();

        for (Turno turno : turnoRepository.getTurnos()) {
            if(turno.getFecha().equals(fechaParseada)){
                listaFecha.add(turno);
            }
        }
        return listaFecha;
    }

    public Turno getTurnoById(Long id){
        return turnoRepository.getTurnos().stream().filter(u -> u.getId().equals(id)).findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("El turno con ID %s no se encuentra en el sistema.", id)));
    }

    public Turno createTurno(Turno turno){
        if(!existePaciente(turno, pacienteRepository.getPacientes())){                     //metodo que mira si el paciente ingresado en el turno existe y devuelve true en dicho caso, si no existe da false         
            throw new ResponseStatusException(HttpStatus.CONFLICT,      
            String.format("El paciente ingresado no existe."));
        }
        else if(!existeProfesional(turno, profesionalRepository.getProfesionales())){         //idem paciente
            throw new ResponseStatusException(HttpStatus.CONFLICT,     
            String.format("El profesional ingresado no existe."));
        }
        else if(turnoRepository.getTurnos().stream().anyMatch(u -> u.equals(turno))){        //la funcion equals() DEBE SOBREESCRIBIRSE en la clase Turno para que compare si existen 2 turnos con mismo paciente, profesional y fecha
            throw new ResponseStatusException(HttpStatus.CONFLICT,
            String.format("Ya existe un turno con los mismos datos."));
        }
        turnoRepository.agregarTurno(turno);
        return turno;
    }

    public void deleteTurno(Long id){
        turnoRepository.eliminarTurno(id);
    }

   private Boolean existePaciente(Turno turno, List<Paciente> lista){
        for (Paciente paciente : lista) {
            if(turno.getPaciente().equals(paciente)){
                return true;
            }
        }

        return false;
    }

    private Boolean existeProfesional(Turno turno, List<Profesional> lista){
        for (Profesional profesional : lista) {
            if(turno.getProfesional().equals(profesional)){
                return true;
            }
        }

        return false;
    }
}
