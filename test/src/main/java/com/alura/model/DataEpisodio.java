package com.alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEpisodio (
    @JsonAlias("Title") String titulo,
    @JsonAlias("Episode") Integer numEpisodio,
    @JsonAlias("imdbRating") String evaluacion,
    @JsonAlias("Released") String fechaLanzamiento
){}
