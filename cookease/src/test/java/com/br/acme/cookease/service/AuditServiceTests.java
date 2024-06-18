package com.br.acme.cookease.service;

import com.br.acme.cookease.model.Chefe;
import com.br.acme.cookease.model.Ingrediente;
import com.br.acme.cookease.model.Receita;
import com.br.acme.cookease.services.AuditService;
import com.br.acme.cookease.services.ChefeService;
import com.br.acme.cookease.services.IngredienteService;
import com.br.acme.cookease.services.ReceitaService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AuditServiceTests {

    @Autowired
    AuditService auditService;

    @Autowired
    ChefeService chefeService;

    @Autowired
    IngredienteService ingredienteService;

    @Autowired
    ReceitaService receitaService;

    @Test
    @DisplayName("Deve retornar histórico de alterações para um chefe")
    @Transactional
    public void testarGetChefeHistory() {
        Chefe chefe = new Chefe();
        chefe.setId(7);
        chefe.setNome("Nome Teste");
        chefe.setSobrenome("Sobrenome Teste");
        chefeService.save(chefe);
        chefe.setSobrenome("Teste Sobrenome 2");
        chefeService.update(chefe.getId(), chefe);

        List<Chefe> historicoChefe = auditService.getChefeHistory(chefe.getId());
        assertNotNull(historicoChefe);
    }

    @Test
    @DisplayName("Deve retornar histórico de alterações para um ingrediente")
    @Transactional
    public void testarGetIngredienteHistory() {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setId(7);
        ingrediente.setNome("Sal");
        ingrediente.setPreco(0.99);
        ingredienteService.save(ingrediente);
        ingrediente.setPreco(1.20);
        ingredienteService.update(ingrediente.getId(), ingrediente);

        List<Ingrediente> historicoIngrediente = auditService.getIngredienteHistory(ingrediente.getId());
        assertNotNull(historicoIngrediente);
    }

    @Test
    @DisplayName("Deve retornar histórico de alterações para uma receita")
    @Transactional
    public void testarGetReceitaHistory() {
        Chefe chefe = Chefe.builder().id(1).build();
        Receita receita = new Receita();
        receita.setId(7);
        receita.setNome("Brownie da Duda");
        receita.setChefe(chefe);
        receitaService.save(receita);
        String novoNome = "Brownie da Debora";
        receita.setNome(novoNome);
        receitaService.update(receita.getId(), receita);

        List<Receita> historicoReceita = auditService.getReceitaHistory(receita.getId());
        assertNotNull(historicoReceita);
    }
}
