package com.br.acme.cookease;

import com.br.acme.cookease.model.Receita;
import com.br.acme.cookease.services.ReceitaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReceitaServiceTest {
    @Autowired
    ReceitaService receitaService;

    @Test
    @DisplayName("Deve inserir Receita no Banco.")
    public void testarInsert() {
        List<Receita> all = receitaService.getAll();
        int estadoInicial = all.size();

        Receita receita = new Receita();
        receita.setNome("Brownie da Duda");
//      receita.setIngredientes(); VOLTAR AQUI
        receitaService.save(receita);

        all = receitaService.getAll();
        int estadoFinal = all.size();

        assertEquals(estadoInicial + 1, estadoFinal);
    }

    @Test
    @DisplayName("Deve deletar um Receita do Banco.")
    public void testarDelete() {
        Receita receita = new Receita();
        receita.setNome("Brownie da Duda");
        receitaService.save(receita);

        List<Receita> all = receitaService.getAll();
        int estadoInicial = all.size();
        Receita receita2 = all.get(0);
        receitaService.deleById(receita2.getId());

        all = receitaService.getAll();
        int estadoFinal = all.size();

        assertEquals(estadoFinal, estadoInicial - 1);
    }

}
