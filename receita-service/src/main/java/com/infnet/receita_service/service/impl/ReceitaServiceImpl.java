package com.infnet.receita_service.service.impl;

import com.infnet.receita_service.model.Receita;
import com.infnet.receita_service.repository.ReceitaRepository;
import com.infnet.receita_service.service.ReceitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceitaServiceImpl implements ReceitaService {

    private final ReceitaRepository receitaRepository;

    @Override
    public List<Receita> getAll() {
        return receitaRepository.findAll();
    }

    @Override
    public Optional<Receita> findById(Integer id) {
        return receitaRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        receitaRepository.deleteById(id);
    }

    @Override
    public void save(Receita receita) {
        receitaRepository.save(receita);
    }

    @Override
    public Receita update(Integer id, Receita receitaAtualizada) {
        receitaAtualizada.setId(id);
        return receitaRepository.save(receitaAtualizada);
    }
}
