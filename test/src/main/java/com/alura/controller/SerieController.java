package com.alura.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.dto.SerieDto;
import com.alura.repository.SerieRepository;

@RestController
public class SerieController {
    @Autowired
    private SerieRepository repositorio;


    
    //para obtener un respuesta ante localhost:5080/series
    //DTO para proteger datos 
    @GetMapping("/series")
    public List<SerieDto> mostrarMensaje(){
        return repositorio.findAll().stream().map(t-> new SerieDto(t.getTitulo(), t.getTotalTemporadas(), t.getEvaluacion(), t.getGenero(), t.getAutores(), t.getSinopsis(), t.getPoster())).collect(Collectors.toList());
    }


}
