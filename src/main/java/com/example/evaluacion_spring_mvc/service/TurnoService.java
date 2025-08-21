package com.example.evaluacion_spring_mvc.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.evaluacion_spring_mvc.model.Paciente;
import com.example.evaluacion_spring_mvc.model.Profesional;
import com.example.evaluacion_spring_mvc.model.Turno;

import jakarta.annotation.PostConstruct;

@Service
public class TurnoService {

    private List<Turno> turnos = new ArrayList<>();
    private List<Paciente> listaPacientes = new ArrayList<>();
    private List<Profesional> listaProfesionales = new ArrayList<>();

    @PostConstruct
    public void init(){

        Paciente p1 = new Paciente(1L, "Nicolas", "Francos", "43538408", "nfrancos@mobydigital.com");
        Paciente p2 = new Paciente(2L, "Alejandro", "Sergi", "17565437", "asergi@miranda.com");
        Paciente p3 = new Paciente(3L, "Ozzie", "Osbourne", "17423537", "ozzie@osbourne.com");
        listaPacientes.add(p1);
        listaPacientes.add(p2);
        listaPacientes.add(p3);

        Profesional pr1 = new Profesional(1L, "Nicolas", "Francos", "43538408");
        Profesional pr2 = new Profesional(2L, "Alejandro", "Sergi", "17565437");
        Profesional pr3 = new Profesional(3L, "Ozzie", "Osbourne", "17423537");
        listaProfesionales.add(pr1);
        listaProfesionales.add(pr2);
        listaProfesionales.add(pr3);
        
        Turno t1 = new Turno(1L, listaPacientes.get(0), listaProfesionales.get(0), "2001-08-13");
        Turno t2 = new Turno(2L, listaPacientes.get(1), listaProfesionales.get(1), "2001-08-13");
        Turno t3 = new Turno(3L, listaPacientes.get(2), listaProfesionales.get(2), "2001-08-13");
        turnos.add(t1);
        turnos.add(t2);
        turnos.add(t3);
    }
    
    public List<Turno> getTurnos(){
        return turnos;
    }
    
    public List<Turno> getTurnosByFecha(String fecha){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaParseada = LocalDate.parse(fecha, dtf);
        List<Turno> listaFecha = new ArrayList<>();

        for (Turno turno : turnos) {
            if(turno.getFecha().equals(fechaParseada)){
                listaFecha.add(turno);
            }
        }
        return listaFecha;
    }

    public Turno getTurnoById(Long id){
        return turnos.stream().filter(u -> u.getId().equals(id)).findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("El turno con ID %s no se encuentra en el sistema.", id)));
    }

    public Turno createTurno(Turno turno){
        if(!existePaciente(turno, listaPacientes)){                     //metodo que mira si el paciente ingresado en el turno existe y devuelve true en dicho caso, si no existe da false         
            throw new ResponseStatusException(HttpStatus.CONFLICT,      
            String.format("El paciente ingresado no existe."));
        }
        else if(!existeProfesional(turno, listaProfesionales)){         //idem paciente
            throw new ResponseStatusException(HttpStatus.CONFLICT,     
            String.format("El profesional ingresado no existe."));
        }
        else if(turnos.stream().anyMatch(u -> u.equals(turno))){        //la funcion equals() DEBE SOBREESCRIBIRSE en la clase Turno para que compare si existen 2 turnos con mismo paciente, profesional y fecha
            throw new ResponseStatusException(HttpStatus.CONFLICT,
            String.format("Ya existe un turno con los mismos datos."));
        }
        turnos.add(turno);
        return turno;
    }

    public void deleteTurno(Long id){
        Turno turno = getTurnoById(id);
        turnos.remove(turno);
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
