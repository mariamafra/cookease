package com.br.acme.cookease.services.impl;

import com.br.acme.cookease.filters.ReceitaFilters;
import com.br.acme.cookease.model.Ingrediente;
import com.br.acme.cookease.model.Receita;
import com.br.acme.cookease.repository.ReceitaRepository;
import com.br.acme.cookease.services.ReceitaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceitaServiceImpl implements ReceitaService {
    private final ReceitaRepository receitaRepository;
    private final EntityManager entityManager;

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
    public List<Receita> getAllByNome(String nome) {
        return receitaRepository.findAllByNome(nome);
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

    @Override
    public List<Receita> findWithFilters(ReceitaFilters filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Receita> cq = cb.createQuery(Receita.class);
        Root<Receita> receita = cq.from(Receita.class);
        List<Predicate> predicates = new ArrayList<>();

        if(filters.getNome().isPresent()) {
            String query = filters.getNome().get() + "%";
            Predicate nome = cb.like(receita.get("nome"), query);
            predicates.add(nome);
        }
        if(filters.getChefe().isPresent()) {
            String query = filters.getChefe().get() + "%";
            Predicate chefe = cb.like(receita.get("chefe"), query);
            predicates.add(chefe);
        }
        if(filters.getIngredientes().isPresent()) {
            List<Ingrediente> ingredientes = filters.getIngredientes().get();
            List<Integer> ids = ingredientes.stream().map(Ingrediente::getId).toList();
            Predicate ingredientePredicate = receita.join("ingredientes").get("id").in(ids);
            predicates.add(ingredientePredicate);
        }

        cq.where(predicates.toArray(Predicate[]::new));
        return entityManager.createQuery(cq).getResultList();
    }
}
