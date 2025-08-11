package com.alura.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.dto.EpisodioDto;
import com.alura.dto.SerieDto;
import com.alura.model.Categoria;
import com.alura.model.Episodio;
import com.alura.model.Serie;
import com.alura.repository.SerieRepository;

@Service

public class SerieService {
    @Autowired
    private SerieRepository repositorio;

    
    public List<SerieDto> obtenerTodasSeries(){
        return convertirDatos(repositorio.findAll());  
    }

    public List<SerieDto> obtenerTopSeries(){
        return convertirDatos(repositorio.findTop5ByOrderByEvaluacionDesc()); 
    }

    public List<SerieDto> obtenerSeriesRecientes(){
        return convertirDatos(repositorio.buscarSeriesRecientes());

    }

    public SerieDto obtenerId(Long id){
        Optional<Serie> serie = repositorio.findById(id);
        if(serie.isPresent()){
            Serie s = serie.get();
            return convertirDato(s);
        }
        return null;
        
    }

    public List<EpisodioDto> obtenerTodosEpisodios(Long id){
        Optional<Serie> serie = repositorio.findById(id);
        if(serie.isPresent()){
            Serie s = serie.get();
            List<Episodio> e = s.getEpisodios();
            return convertirEpisodio(e);
        }
        return null;
    }

    public List<EpisodioDto> obtenerEpisodioPorTemporada(Long id, Long numTemporada){
        List<Episodio> epi = repositorio.obtenerTemporadaEpisodioPorNumeroTemporada(id, numTemporada);
        return convertirEpisodio(epi);
       
    }

    public List<SerieDto> obtenerSerieCategoria(String categoria){
        List<Serie> s = repositorio.findByGenero(Categoria.fromCategoriaEspanol(categoria));
        return convertirDatos(s);
    }


    public List<SerieDto> convertirDatos(List<Serie> serie){
        return serie.stream().map(t -> new SerieDto(t.getId(),t.getTitulo(), t.getTotalTemporadas(), t.getEvaluacion(), t.getGenero(), t.getAutores(), t.getSinopsis(), t.getPoster())).collect(Collectors.toList());
    }

    public SerieDto  convertirDato(Serie t){
        return new SerieDto(t.getId(), t.getTitulo(), t.getTotalTemporadas(), t.getEvaluacion(), t.getGenero(), t.getAutores(), t.getSinopsis(), t.getPoster());
    }

    public List<EpisodioDto> convertirEpisodio(List<Episodio> epi){
         return epi.stream().map(m-> new EpisodioDto(m.getTemporada(),m.getTitulo(),m.getNumEpisodio())).collect(Collectors.toList());
    }

    

    
}
