package com.br.acme.cookease.services;

import com.br.acme.cookease.model.Ingrediente;

import java.util.List;
import java.util.Optional;

public interface IngredienteService {
    List<Ingrediente> getAll();
    Optional<Ingrediente> findById(Integer id);
    void deleById(Integer id);
    void save(Ingrediente ingrediente);
    Ingrediente update(Integer id, Ingrediente ingredienteAtualizada);
}
