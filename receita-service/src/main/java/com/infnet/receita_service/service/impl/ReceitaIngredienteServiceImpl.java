package com.infnet.receita_service.service.impl;

import com.infnet.receita_service.model.ReceitaIngrediente;
import com.infnet.receita_service.repository.ReceitaIngredienteRepository;
import com.infnet.receita_service.service.ReceitaIngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceitaIngredienteServiceImpl implements ReceitaIngredienteService {

    private final ReceitaIngredienteRepository receitaIngredienteRepository;

    @Override
    public List<ReceitaIngrediente> getAll() {
        return receitaIngredienteRepository.findAll();
    }

    @Override
    public Optional<ReceitaIngrediente> findById(Integer id) {
        return receitaIngredienteRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        receitaIngredienteRepository.deleteById(id);
    }

    @Override
    public void save(ReceitaIngrediente receitaIngrediente) {
        receitaIngredienteRepository.save(receitaIngrediente);
    }

//    @Override
//    public ReceitaIngrediente update(Integer id, ReceitaIngrediente receitaIngredienteAtualizada) {
//        receitaIngredienteAtualizada.set(id);
//        return receitaIngredienteRepository.save(receitaIngredienteAtualizada);
//    }
}
