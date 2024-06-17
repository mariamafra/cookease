package com.br.acme.cookease;

import com.br.acme.cookease.model.Ingrediente;
import com.br.acme.cookease.model.Receita;
import com.br.acme.cookease.services.IngredienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IngredienteServiceTests {

    @Autowired
    IngredienteService ingredienteService;

    @Test
    @DisplayName("Deve retornar todos os ingredientes")
    public void testarGetAll() {
        List<Ingrediente> all = ingredienteService.getAll();
        assertEquals(5, all.size());
    }

    @Test
    @DisplayName("Deve retornar ingrediente por Id")
    public void testarFindById() {
        Optional<Ingrediente> byId = ingredienteService.findById(1);
        assertTrue(byId.isPresent());

        Optional<Ingrediente> inexistente = ingredienteService.findById(-1);
        assertTrue(inexistente.isEmpty());
    }

    @Test
    @DisplayName("Deve buscar ingredientes pelo nome")
    public void testarGetByNome() {
        List<Ingrediente> result = ingredienteService.getAllByNome("Chocolate Nobre");
        assertEquals(1, result.size());

        List<Ingrediente> resultContain = ingredienteService.getAllByNome("Açucar");
        assertEquals(1, resultContain.size());
    }

    @Test
    @DisplayName("Deve salvar o ingrediente")
    public void testarSave() {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome("Pimenta");
        ingrediente.setPreco(1.50);
        ingrediente.setReceitas(List.of());
        ingredienteService.save(ingrediente);

        assertNotNull(ingrediente.getId(), "O ID do ingrediente não deveria ser nulo após o salvamento.");

        Optional<Ingrediente> ingredienteEncontrado = ingredienteService.findById(ingrediente.getId());
        assertTrue(ingredienteEncontrado.isPresent(), "O ingrediente deveria estar presente no banco de dados.");

        Ingrediente ingredienteSalvo = ingredienteEncontrado.get();
        assertEquals(ingrediente.getId(), ingredienteSalvo.getId());
        assertEquals(ingrediente.getNome(), ingredienteSalvo.getNome());
        assertEquals(ingrediente.getPreco(), ingredienteSalvo.getPreco());
    }

    @Test
    @DisplayName("Deve atualizar o ingrediente pelo nome")
    public void testarUpdate() {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome("Sal");
        ingrediente.setPreco(0.99);
        ingredienteService.save(ingrediente);

        Optional<Ingrediente> ingredienteEncontradoOpt = ingredienteService.findById(ingrediente.getId());
        assertTrue(ingredienteEncontradoOpt.isPresent());
        Ingrediente ingredienteEncontrado = ingredienteEncontradoOpt.get();

        assertEquals(ingrediente.getId(), ingredienteEncontrado.getId());
        assertEquals(ingrediente.getNome(), ingredienteEncontrado.getNome());
        assertEquals(ingrediente.getPreco(), ingredienteEncontrado.getPreco());

        ingrediente.setPreco(1.20);
        ingredienteService.update(ingrediente.getId(), ingrediente);

        Optional<Ingrediente> ingredienteAtualizadoOpt = ingredienteService.findById(ingrediente.getId());
        assertTrue(ingredienteAtualizadoOpt.isPresent());
        Ingrediente ingredienteAtualizado = ingredienteAtualizadoOpt.get();

        assertEquals(ingrediente.getId(), ingredienteAtualizado.getId());
        assertEquals(ingrediente.getNome(), ingredienteAtualizado.getNome());
        assertEquals(ingrediente.getPreco(), ingredienteAtualizado.getPreco());
    }


    @Test
    @DisplayName("Deve deletar o ingrediente")
    public void testarDeletar() {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome("Pimenta");
        ingrediente.setPreco(1.50);
        ingrediente.setReceitas(List.of());
        ingredienteService.save(ingrediente);

        assertNotNull(ingrediente.getId(), "O ID do ingrediente não deveria ser nulo após o salvamento.");

        Optional<Ingrediente> ingredienteEncontrado = ingredienteService.findById(ingrediente.getId());
        assertTrue(ingredienteEncontrado.isPresent(), "O ingrediente deveria estar presente no banco de dados.");

        List<Receita> receitas = ingredienteEncontrado.get().getReceitas();
        if (receitas != null) {
            for (Receita receita : receitas) {
                receita.getIngredientes().remove(ingredienteEncontrado.get());
            }
        }

        ingredienteService.deleteById(ingrediente.getId());
        Optional<Ingrediente> ingredienteDeletado = ingredienteService.findById(ingrediente.getId());
        assertTrue(ingredienteDeletado.isEmpty());
    }
}
