package com.alura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.dto.SerieDto;
import com.alura.services.SerieService;

@RestController
public class SerieController {
    @Autowired
    private SerieService service;


    
    //para obtener un respuesta ante localhost:5080/series
    //DTO para proteger datos 
    
    @GetMapping("/series")
    public List<SerieDto> mostrarMensaje(){
        return service.mostrarMensaje();
    }


}
