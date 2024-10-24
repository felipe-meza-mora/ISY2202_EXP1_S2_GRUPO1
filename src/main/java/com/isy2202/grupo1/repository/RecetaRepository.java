package com.isy2202.grupo1.repository;

import com.isy2202.grupo1.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {

  /* 
    @Query("SELECT r FROM Receta r")
    List<Receta> findAllRecetas();

    @Query(value = "SELECT * FROM recetas", nativeQuery = true)
    List<Receta> findAllRecetasSQL();
    */
    
}