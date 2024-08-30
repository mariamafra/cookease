package com.infnet.receita_service.service;

import com.infnet.receita_service.model.ReceitaIngrediente;

import java.util.List;
import java.util.Optional;


public interface ReceitaIngredienteService {
    List<ReceitaIngrediente> getAll();

    Optional<ReceitaIngrediente> findById(Integer id);

    void deleteById(Integer id);

    void save(ReceitaIngrediente receitaIngrediente);

//    ReceitaIngrediente update(Integer id, ReceitaIngrediente receitaIngredienteAtualizada);
}
