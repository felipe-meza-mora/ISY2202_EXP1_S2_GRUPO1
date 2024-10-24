package com.isy2202.grupo1.service;

import com.isy2202.grupo1.model.Receta;
import java.util.List;

public interface RecetaService {
    List<Receta> getAllRecetas();
    Receta saveReceta(Receta receta);
    Receta getRecetaById(Long id);
    Receta updateReceta(Long id, Receta receta);
    void deleteReceta(Long id);
}