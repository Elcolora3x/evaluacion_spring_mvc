package com.example.evaluacion_spring_mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evaluacion_spring_mvc.model.Turno;
import com.example.evaluacion_spring_mvc.service.TurnoService;


@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @PostMapping
    public ResponseEntity<Turno> createTurno(@RequestBody Turno turno){
        return new ResponseEntity<Turno>(turnoService.createTurno(turno), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Turno>> getTurnos(){
        return new ResponseEntity<List<Turno>>(turnoService.getTurnos(), HttpStatus.OK);
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Turno>> getTurnosByFecha(@PathVariable("fecha") String fecha){
        return new ResponseEntity<List<Turno>>(turnoService.getTurnosByFecha(fecha), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTurno(@PathVariable("id") Long id){
        turnoService.deleteTurno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
