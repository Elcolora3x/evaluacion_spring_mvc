package com.example.evaluacion_spring_mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evaluacion_spring_mvc.model.Profesional;
import com.example.evaluacion_spring_mvc.service.ProfesionalService;

@RestController
@RequestMapping("/profesionales")
public class ProfesionalController {

    @Autowired
    private ProfesionalService profesionalService;

    @PostMapping
    public ResponseEntity<Profesional> createProfesional(@RequestBody Profesional profesional){
        return new ResponseEntity<Profesional>(profesionalService.createProfesional(profesional), HttpStatus.OK);
    }

    @GetMapping("/{especialidad}") 
    public ResponseEntity<List<Profesional>> ListarPorEspecialidad(@PathVariable("especialidad") String especialidad){
        return new ResponseEntity<List<Profesional>>(profesionalService.ListarPorEspecialidad(especialidad), HttpStatus.OK);
    }
}