package com.br.acme.cookease.repository;

import com.br.acme.cookease.model.Ingrediente;
import com.br.acme.cookease.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
    List<Ingrediente> findAllByNome(String nome);
//    List<Ingrediente> findAllByReceitaId(Receita receita);
}
