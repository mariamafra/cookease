package com.br.acme.cookease;

import com.br.acme.cookease.model.Chefe;
import com.br.acme.cookease.services.ChefeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ChefeServiceTests {
    @Autowired
    ChefeService chefeService;
    @Test
    @DisplayName("Deve retornar todos chefe")
    public void testarGetAll() {
        List<Chefe> all = chefeService.getAll();
        assertEquals(2, all.size());
    }

    @Test
    @DisplayName("Deve retornar por Id")
    public void testarFindById() {
        Optional<Chefe> byId = chefeService.findById(1);
        assertTrue(byId.isPresent());

        Optional<Chefe> inexistente = chefeService.findById(-1);
        assertTrue(inexistente.isEmpty());
    }
}
