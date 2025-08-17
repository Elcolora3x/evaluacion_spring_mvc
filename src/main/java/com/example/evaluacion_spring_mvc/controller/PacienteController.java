package com.example.evaluacion_spring_mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evaluacion_spring_mvc.service.PacienteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping
public class PacienteController {
    
    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/pacientes")
    
}
