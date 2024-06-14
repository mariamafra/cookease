package com.br.acme.cookease;

import com.br.acme.cookease.model.Chefe;
import com.br.acme.cookease.model.Receita;
import com.br.acme.cookease.services.ReceitaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReceitaServiceTests {
    @Autowired
    ReceitaService receitaService;

    @Test
    @DisplayName("Deve inserir Receita no Banco.")
    public void testarInsert() {
        Chefe chefe = Chefe.builder().id(1).build();
        List<Receita> all = receitaService.getAll();
        int estadoInicial = all.size();

        Receita receita = new Receita();
        receita.setNome("Brownie da Duda");
        receita.setChefe(chefe);
        receitaService.save(receita);

        all = receitaService.getAll();
        int estadoFinal = all.size();

        assertEquals(estadoInicial + 1, estadoFinal);
    }

    @Test
    @DisplayName("Deve deletar um Receita do Banco.")
    public void testarDelete() {
        Chefe chefe = Chefe.builder().id(1).build();
        Receita receita = new Receita();
        receita.setNome("Brownie da Duda");
        receita.setChefe(chefe);
        receitaService.save(receita);

        List<Receita> all = receitaService.getAll();
        int estadoInicial = all.size();
        Receita receita2 = all.get(0);
        receitaService.deleById(receita2.getId());

        all = receitaService.getAll();
        int estadoFinal = all.size();

        assertEquals(estadoFinal, estadoInicial - 1);
    }

    @Test
    @DisplayName("Deve retornar uma cerveja pelo ID")
    public void testarGetById() {
        Chefe chefe = Chefe.builder().id(1).build();
        Receita receita = new Receita();
        receita.setNome("Brownie da Duda");
        receita.setChefe(chefe);
        receitaService.save(receita);

        List<Receita> all = receitaService.getAll();
        Receita receita2 = all.get(0);
        Optional<Receita> byId = receitaService.findById(receita2.getId());
        assertTrue(byId.isPresent());

        Optional<Receita> inexistente = receitaService.findById(-1);
        assertTrue(inexistente.isEmpty());
    }
}
