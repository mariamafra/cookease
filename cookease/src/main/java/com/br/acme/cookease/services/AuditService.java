package com.br.acme.cookease.services;

import com.br.acme.cookease.model.Chefe;
import com.br.acme.cookease.model.Ingrediente;
import com.br.acme.cookease.model.Receita;

import java.util.List;

public interface AuditService {
    List<Chefe> getChefeHistory(int chefeId);
    List<Ingrediente> getIngredienteHistory(int ingredienteId);
    List<Receita> getReceitaHistory(int receitaId);
}
