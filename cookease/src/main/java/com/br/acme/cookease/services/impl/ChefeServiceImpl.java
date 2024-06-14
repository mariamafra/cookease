package com.br.acme.cookease.services.impl;

import com.br.acme.cookease.model.Chefe;
import com.br.acme.cookease.repository.ChefeRepository;
import com.br.acme.cookease.services.ChefeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChefeServiceImpl implements ChefeService {
    private final ChefeRepository chefeRepository;
    @Override
    public List<Chefe> getAll() {
        return chefeRepository.findAll();
    }

    @Override
    public Optional<Chefe> findById(Integer id) {
        return chefeRepository.findById(id);
    }

    @Override
    public void deleById(Integer id) {
        chefeRepository.deleteById(id);
    }

    @Override
    public void save(Chefe chefe) {
        chefeRepository.save(chefe);
    }

    @Override
    public Chefe update(Integer id, Chefe chefeAtualizada) {
        chefeAtualizada.setId(id);
        return chefeRepository.save(chefeAtualizada);
    }
}
