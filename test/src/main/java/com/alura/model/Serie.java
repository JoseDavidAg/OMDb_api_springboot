package com.alura.model;

import java.util.Optional;
import java.util.OptionalDouble;


public class  Serie {
        private String titulo; 
        private int totalTemporadas; 
        private double evaluacion; 
        private Categoria genero;
        private String autores;
        private String sinopsis;
        private String poster;


    public Serie(DatosSeries datos) {
        this.titulo = datos.titulo();
        this.totalTemporadas =Optional.ofNullable(  datos.totalTemporadas()).orElse(0);
        this.evaluacion = OptionalDouble.of(Double.parseDouble(datos.evaluacion())).orElse(0.0);    //usar OptionalDouble para evitar valores null
        this.genero = Categoria.fromString(datos.genero().split(",")[0].trim());                    //obtener el primer genero, y asegurarse que no este vacio
        this.autores = datos.autores();
        this.sinopsis = datos.sinopsis();
        this.poster = datos.poster();
    }



    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTotalTemporadas() {
        return this.totalTemporadas;
    }

    public void setTotalTemporadas(int totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public double getEvaluacion() {
        return this.evaluacion;
    }

    public void setEvaluacion(double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Categoria getGenero() {
        return this.genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getAutores() {
        return this.autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getSinopsis() {
        return this.sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "{" +
            " titulo='" + getTitulo() + "'" +
            ", totalTemporadas='" + getTotalTemporadas() + "'" +
            ", evaluacion='" + getEvaluacion() + "'" +
            ", genero='" + getGenero() + "'" +
            ", autores='" + getAutores() + "'" +
            ", sinopsis='" + getSinopsis() + "'" +
            ", poster='" + getPoster() + "'" +
            "}";
    }
    
}





    

