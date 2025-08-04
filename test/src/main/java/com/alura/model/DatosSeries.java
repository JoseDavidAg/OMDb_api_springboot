package com.alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosSeries(
        @JsonAlias("Title")  String titulo, 
        @JsonAlias("totalSeasons") Integer totalTemporadas, 
        @JsonAlias("imdbRating") String evaluacion, 
        //mapear más atributos: genero, actores, sinopsis, poster
        @JsonAlias("Genre")String genero,
        @JsonAlias("Actors")String autores,
        @JsonAlias("Plot")String sinopsis,
        @JsonAlias("Poster")String poster

)
        {
    
}
