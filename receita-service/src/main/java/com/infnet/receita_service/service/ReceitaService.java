package com.infnet.receita_service.service;

import com.infnet.receita_service.model.Receita;

import java.util.List;
import java.util.Optional;


public interface ReceitaService {
    List<Receita> getAll();
    Optional<Receita> findById(Integer id);
    void deleteById(Integer id);
    void save(Receita receita);
    Receita update(Integer id, Receita receitaAtualizada);
}
