package com.br.acme.cookease.services.impl;

import com.br.acme.cookease.model.Receita;
import com.br.acme.cookease.repository.ReceitaRepository;
import com.br.acme.cookease.services.ReceitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public List<Receita> getAll(int page, int size, boolean asc) {
        Sort order = asc ? Sort.by("nome").ascending() : Sort.by("nome").descending();
        PageRequest pageRequest = PageRequest.of(page, size, order);
        return receitaRepository.findAll(pageRequest).stream().toList();
    }

    @Override
    public Optional<Receita> findById(Integer id) {
        return receitaRepository.findById(id);
    }

    @Override
    public void deleById(Integer id) {
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
