package com.alura.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.alura.dto.SerieDto;
import com.alura.repository.SerieRepository;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repositorio;

    @GetMapping("/series")
    public List<SerieDto> mostrarMensaje(){
        return repositorio.findAll().stream().map(t-> new SerieDto(t.getTitulo(), t.getTotalTemporadas(), t.getEvaluacion(), t.getGenero(), t.getAutores(), t.getSinopsis(), t.getPoster())).collect(Collectors.toList());
    }

    
}
