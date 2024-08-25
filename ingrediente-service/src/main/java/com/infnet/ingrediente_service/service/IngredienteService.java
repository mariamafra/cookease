package com.infnet.ingrediente_service.service;

import com.infnet.ingrediente_service.model.Ingrediente;

import java.util.List;
import java.util.Optional;


public interface IngredienteService {
    List<Ingrediente> getAll();
    Optional<Ingrediente> findById(Integer id);
    void deleteById(Integer id);
    void save(Ingrediente ingrediente);
    Ingrediente update(Integer id, Ingrediente ingredienteAtualizada);
}
