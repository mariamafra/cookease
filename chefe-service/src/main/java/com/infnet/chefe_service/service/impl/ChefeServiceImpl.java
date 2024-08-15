package com.infnet.chefe_service.service.impl;

import com.infnet.chefe_service.model.Chefe;
import com.infnet.chefe_service.repository.ChefeRepository;
import com.infnet.chefe_service.service.ChefeService;
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
    public List<Chefe> getAllByNameStartsWith(String nome) {
        return chefeRepository.findAllByNomeStartsWith(nome);
    }

    @Override
    public List<Chefe> getAllByNameContains(String nome) {
        return chefeRepository.findAllByNomeContains(nome);
    }

    @Override
    public void deleteById(Integer id) {
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
