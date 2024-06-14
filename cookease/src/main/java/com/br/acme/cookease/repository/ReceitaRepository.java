package com.br.acme.cookease.repository;

import com.br.acme.cookease.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
    List<Receita> findAllByNome(String nome);
}
