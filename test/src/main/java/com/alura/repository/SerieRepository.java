package com.alura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.model.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
} 