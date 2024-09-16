package com.infnet.receita_service.repository;

import com.infnet.receita_service.model.ReceitaIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaIngredienteRepository extends JpaRepository<ReceitaIngrediente, Integer> {
}
