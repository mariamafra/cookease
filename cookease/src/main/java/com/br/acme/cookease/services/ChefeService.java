package com.br.acme.cookease.services;

import com.br.acme.cookease.model.Chefe;

import java.util.List;
import java.util.Optional;

public interface ChefeService {
    List<Chefe> getAll();
    Optional<Chefe> findById(Integer id);
    List<Chefe> getAllByName(String nome);
    List<Chefe> getAllByNameContains(String nome);
    void deleById(Integer id);
    void save(Chefe chefe);
    Chefe update(Integer id, Chefe chefeAtualizada);
}
