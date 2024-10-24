package com.isy2202.grupo1.service;

import com.isy2202.grupo1.model.Receta;
import com.isy2202.grupo1.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    @Override
    public List<Receta> getAllRecetas() {
        List<Receta> recetas = recetaRepository.findAll();
        System.out.println("Cantidad de recetas obtenidas: " + recetas.size());
        recetas.forEach(r -> System.out.println("Receta: " + r.getTitulo()));
        return recetas;
    }

    @Override
    public Receta saveReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    @Override
    public Receta getRecetaById(Long id) {
        Optional<Receta> receta = recetaRepository.findById(id);
        if (receta.isPresent()) {
            return receta.get();
        } else {
            throw new RuntimeException("Receta no encontrada con el ID: " + id);
        }
    }

    @Override
    public Receta updateReceta(Long id, Receta recetaDetails) {
        Receta receta = getRecetaById(id);
        receta.setTitulo(recetaDetails.getTitulo());
        receta.setDescripcion(recetaDetails.getDescripcion());
        receta.setImagenUrl(recetaDetails.getImagenUrl());
        receta.setTiempoPreparacion(recetaDetails.getTiempoPreparacion());
        receta.setPasos(recetaDetails.getPasos());
        return recetaRepository.save(receta);
    }

    @Override
    public void deleteReceta(Long id) {
        Receta receta = getRecetaById(id);
        recetaRepository.delete(receta);
    }
}