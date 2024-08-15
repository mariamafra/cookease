package com.infnet.chefe_service.service;

import com.infnet.chefe_service.model.Chefe;

import java.util.List;
import java.util.Optional;

public interface ChefeService {
    List<Chefe> getAll();
    Optional<Chefe> findById(Integer id);
    List<Chefe> getAllByNameStartsWith(String nome);
    List<Chefe> getAllByNameContains(String nome);
    void deleteById(Integer id);
    void save(Chefe chefe);
    Chefe update(Integer id, Chefe chefeAtualizada);
}
