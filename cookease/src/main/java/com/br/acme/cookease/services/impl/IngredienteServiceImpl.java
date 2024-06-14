package com.br.acme.cookease.services.impl;

import com.br.acme.cookease.model.Ingrediente;
import com.br.acme.cookease.repository.IngredienteRepository;
import com.br.acme.cookease.services.IngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredienteServiceImpl implements IngredienteService {
    private final IngredienteRepository ingredienteRepository;

    @Override
    public List<Ingrediente> getAll() {
        return ingredienteRepository.findAll();
    }

    @Override
    public Optional<Ingrediente> findById(Integer id) {
        return ingredienteRepository.findById(id);
    }

    @Override
    public void deleById(Integer id) {
        ingredienteRepository.deleteById(id);
    }

    @Override
    public void save(Ingrediente ingrediente) {
        ingredienteRepository.save(ingrediente);
    }

    @Override
    public Ingrediente update(Integer id, Ingrediente ingredienteAtualizada) {
        ingredienteAtualizada.setId(id);
        return ingredienteRepository.save(ingredienteAtualizada);
    }
}
