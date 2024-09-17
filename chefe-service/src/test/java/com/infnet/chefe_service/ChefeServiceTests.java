package com.infnet.chefe_service;

import com.infnet.chefe_service.model.Chefe;
import com.infnet.chefe_service.service.ChefeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ChefeServiceTests {

    @Autowired
    ChefeService chefeService;

    @Test
    @DisplayName("Deve retornar todos chefe")
    public void testarGetAll() {
        List<Chefe> all = chefeService.getAll();
        assertEquals(4, all.size());
    }

    @Test
    @DisplayName("Deve retornar por Id")
    public void testarFindById() {
        Optional<Chefe> byId = chefeService.findById(1);
        assertTrue(byId.isPresent());

        Optional<Chefe> inexistente = chefeService.findById(-1);
        assertTrue(inexistente.isEmpty());
    }

    @Test
    @DisplayName("Deve buscar o chefe pelo nome")
    public void testarGetByNome() {
        List<Chefe> result = chefeService.getAllByNameStartsWith("Duda");
        assertEquals(2, result.size());

        List<Chefe> resultContain = chefeService.getAllByNameContains("a");
        assertEquals(4, resultContain.size());
    }

    @Test
    @DisplayName("Deve salvar o chefe")
    public void testarSave() {
        Chefe chefe = new Chefe();
        chefe.setNome("Nome Teste");
        chefe.setSobrenome("Sobrenome Teste");
        chefeService.save(chefe);

        assertNotNull(chefe.getId(), "O ID do chefe não deveria ser nulo após o salvamento.");

        Optional<Chefe> chefeEncontrado = chefeService.findById(chefe.getId());
        assertTrue(chefeEncontrado.isPresent(), "O chefe deveria estar presente no banco de dados.");
        assertEquals(chefe, chefeEncontrado.get());
    }


    @Test
    @DisplayName("Deve atualizar o chefe pelo nome")
    public void testarUpdate() {
        Chefe chefe = new Chefe();
        chefe.setId(5);
        chefe.setNome("Nome Teste");
        chefe.setSobrenome("Sobrenome Teste");
        chefeService.save(chefe);
        Chefe chefeEncontrado = chefeService.findById(5).get();
        assertEquals(chefe, chefeEncontrado);

        chefe.setSobrenome("Teste Sobrenome 2");
        chefeService.update(5, chefe);

        Chefe chefeAtualizado = chefeService.findById(5).get();
        assertEquals(chefe.getSobrenome(), chefeAtualizado.getSobrenome());
    }

    @Test
    @DisplayName("Deve deletar o chefe")
    public void testarDeletar() {
        Chefe chefe = new Chefe();
        chefe.setId(5);
        chefe.setNome("Nome Teste");
        chefe.setSobrenome("Sobrenome Teste");
        chefeService.save(chefe);
        Chefe chefeEncontrado = chefeService.findById(5).get();
        assertEquals(chefe, chefeEncontrado);

        chefeService.deleteById(5);
        Optional<Chefe> chefeDeletado = chefeService.findById(5);
        assertTrue(chefeDeletado.isEmpty());
    }
}
