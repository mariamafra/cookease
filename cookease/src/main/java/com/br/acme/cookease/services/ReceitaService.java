package com.br.acme.cookease.services;

import com.br.acme.cookease.model.Receita;

import java.util.List;
import java.util.Optional;

public interface ReceitaService {
    List<Receita> getAll();
    List<Receita> getAll(int page, int size, boolean asc);
    Optional<Receita> findById(Integer id);
    void deleById(Integer id);
    void save(Receita receita);
    Receita update(Integer id, Receita receitaAtualizada);
}
