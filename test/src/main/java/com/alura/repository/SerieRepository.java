package com.alura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.model.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainsIgnoreCase(String nombreSerie);
    //select * from series.series order by evaluacion limit 2; findByAgeOrderByLastnameDesc
    List<Serie> findTop5ByOrderByEvaluacionDesc();
}