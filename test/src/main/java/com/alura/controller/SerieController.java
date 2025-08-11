package com.alura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alura.dto.EpisodioDto;
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
        return service.obtenerTodasSeries();
    }

    @GetMapping("/series/top5")
    public List<SerieDto> obtenerTop5Series(){
        return service.obtenerTopSeries();
    }

    @GetMapping("/series/lanzamientos")
    public List<SerieDto> obtenerLanzamientos(){
        return service.obtenerSeriesRecientes();
    }

    @GetMapping("series/{id}")
    public SerieDto obtenerSerieId(@PathVariable Long id){
        return service.obtenerId(id);
    }

    @GetMapping("series/{id}/temporadas/todas")
    public List<EpisodioDto> obtenerEpisodios(@PathVariable Long id){
        return service.obtenerTodosEpisodios(id);

    }

     @GetMapping("/series/{id}/temporadas/{numeroTemporada}")
     public List<EpisodioDto> obtenerEpisodiosTemporada(@PathVariable Long id, @PathVariable Long numeroTemporada){
        return service.obtenerEpisodioPorTemporada(id, numeroTemporada);
    } 

    @GetMapping("/series/categoria/{categoriaSeleccionada}")
    public List<SerieDto> obtenerSeriePorCategoria(@PathVariable String categoriaSeleccionada){
        return service.obtenerSerieCategoria(categoriaSeleccionada);
    }
    


}
