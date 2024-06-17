package com.br.acme.cookease;

import com.br.acme.cookease.filters.ReceitaFilters;
import com.br.acme.cookease.model.Chefe;
import com.br.acme.cookease.model.Receita;
import com.br.acme.cookease.services.ReceitaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
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
    @DisplayName("Deve deletar uma Receita do Banco.")
    public void testarDelete() {
        Chefe chefe = Chefe.builder().id(1).build();
        Receita receita = new Receita();
        receita.setNome("Brownie da Duda");
        receita.setChefe(chefe);
        receitaService.save(receita);

        List<Receita> all = receitaService.getAll();
        int estadoInicial = all.size();
        Receita receita2 = all.get(0);
        receitaService.deleteById(receita2.getId());

        all = receitaService.getAll();
        int estadoFinal = all.size();

        assertEquals(estadoFinal, estadoInicial - 1);
    }

    @Test
    @DisplayName("Deve retornar uma receita pelo ID")
    public void testarGetById() {
        Chefe chefe = Chefe.builder().id(1).build();
        Receita receita = new Receita();
        receita.setNome("Brownie da Duda");
        receita.setChefe(chefe);
        receitaService.save(receita);

        List<Receita> all = receitaService.getAll();
        Receita receita2 = all.get(0);
        Optional<Receita> byId = receitaService.findById(receita2.getId());
        assertTrue(byId.isPresent(), "Deveria ter encontrado uma receita com o ID existente.");

        Optional<Receita> inexistente = receitaService.findById(-1);
        assertTrue(inexistente.isEmpty(), "Não deveria ter encontrado uma receita com o ID inexistente.");

        if (byId.isPresent()) {
            Receita receita3 = byId.get();
            log.info("Receita" + receita3);
        }
    }

    @Test
    @DisplayName("Deve atualizar uma Receita no Banco.")
    public void testarUpdate() {
        Chefe chefe = Chefe.builder().id(1).build();
        Receita receita = new Receita();
        receita.setNome("Brownie da Duda");
        receita.setChefe(chefe);
        receitaService.save(receita);

        String novoNome = "Brownie da Debora";
        receita.setNome(novoNome);
        receitaService.update(receita.getId(), receita);

        Optional<Receita> receitaAtualizadaOpt = receitaService.findById(receita.getId());
        assertTrue(receitaAtualizadaOpt.isPresent(), "A receita atualizada deveria estar presente no banco de dados.");

        Receita receitaAtualizada = receitaAtualizadaOpt.get();
        assertEquals(novoNome, receitaAtualizada.getNome(), "O nome da receita não foi atualizado corretamente.");
    }

    @Test
    @DisplayName("Deve buscar Receitas com filtros.")
    public void testarFindWithFilters() {
        Chefe chefe = Chefe.builder().id(1).build();
        Receita receita = new Receita();
        receita.setNome("Brownie da Duda");
        receita.setChefe(chefe);
        receitaService.save(receita);

        ReceitaFilters filters = ReceitaFilters.builder().nome(Optional.of("Brownie")).chefe(Optional.empty()).ingredientes(Optional.empty()).build();

        List<Receita> receitasEncontradas = receitaService.findWithFilters(filters);

        assertFalse(receitasEncontradas.isEmpty(), "Deveria ter encontrado pelo menos uma receita com os filtros.");
        assertTrue(receitasEncontradas.stream().anyMatch(r -> r.getNome().equals(receita.getNome())), "A receita inicial não foi encontrada com os filtros.");
    }

}
