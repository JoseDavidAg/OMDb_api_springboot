package com.alura.model;

import java.time.LocalDate;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numEpisodio;
    private Double evaluacion;
    private LocalDate fechaLanzamiento;


    public Episodio(Integer temporada, DataEpisodio e){
        this.temporada= temporada;
        this.titulo = e.titulo();
        this.numEpisodio = e.numEpisodio();
        try {
            this.evaluacion = Double.valueOf(e.evaluacion());
        } catch (NumberFormatException m) {
            this.evaluacion = 0.0;
        }
        try {
            this.fechaLanzamiento = LocalDate.parse( e.fechaLanzamiento());

        } catch (Exception m) {
            this.fechaLanzamiento = null;
          
        }
        
    }
    public Integer getTemporada() {
        return this.temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumEpisodio() {
        return this.numEpisodio;
    }

    public void setNumEpisodio(Integer numEpisodio) {
        this.numEpisodio = numEpisodio;
    }

    public Double getEvaluacion() {
        return this.evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public LocalDate getFechaLanzamiento() {
        return this.fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    @Override
    public String toString() {
        return "{" +
            " temporada='" + getTemporada() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", numEpisodio='" + getNumEpisodio() + "'" +
            ", evaluacion='" + getEvaluacion() + "'" +
            ", fechaLanzamiento='" + getFechaLanzamiento() + "'" +
            "}";
    }



    
}
