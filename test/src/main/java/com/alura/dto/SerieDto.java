package com.alura.dto;

import com.alura.model.Categoria;
//clase para modelar el objeto que vamos a devolver en nuestro servidor

public record SerieDto(
         String titulo, 
         int totalTemporadas, 
         double evaluacion,
         Categoria genero,
         String autores,
         String sinopsis,
         String poster
) {

    
}
