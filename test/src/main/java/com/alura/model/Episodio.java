package com.alura.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "episodios")
public class Episodio {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer temporada;
    private String titulo;
    private Integer numEpisodio;
    private Double evaluacion;
    private LocalDate fechaLanzamiento;
        // Relación ManyToOne: muchos episodios pertenecen a una serie
    @ManyToOne
    private Serie serie;
    public Episodio(){
        
    }


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

    public Serie getSerie() {
        return this.serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
