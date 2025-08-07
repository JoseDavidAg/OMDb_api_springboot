package com.alura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alura.model.Categoria;
import com.alura.model.Episodio;
import com.alura.model.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainsIgnoreCase(String nombreSerie);
    //select * from series.series order by evaluacion limit 2; findByAgeOrderByLastnameDesc
    List<Serie> findTop5ByOrderByEvaluacionDesc();
    List<Serie> findByGenero(Categoria categoria);

    @Query("select s from Serie s where s.totalTemporadas <= :totalTemporadas and s.evaluacion >= :evaluacion" )
    List<Serie> buscarTemporadasValoracion(int totalTemporadas,int evaluacion );

    @Query("SELECT e FROM Serie s join s.episodios e where e.titulo ILIKE %:nombreEpisodio")
    List<Episodio> buscarEpisodioNombre(String nombreEpisodio);

    @Query("SELECT e FROM Serie s join s.episodios e where s = :serie ORDER BY e.evaluacion DESC LIMIT 5")
    List<Episodio> topEpisodios(Serie serie);
}