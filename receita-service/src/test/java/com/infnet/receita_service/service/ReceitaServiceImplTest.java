package com.infnet.receita_service.service;

import com.infnet.receita_service.model.Receita;
import com.infnet.receita_service.repository.ReceitaRepository;
import com.infnet.receita_service.service.impl.ReceitaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReceitaServiceImplTest {

    @Mock
    private ReceitaRepository receitaRepository;

    @InjectMocks
    private ReceitaServiceImpl receitaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Receita receita1 = new Receita();
        Receita receita2 = new Receita();
        when(receitaRepository.findAll()).thenReturn(Arrays.asList(receita1, receita2));

        List<Receita> receitas = receitaService.getAll();

        assertEquals(2, receitas.size());
        verify(receitaRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Receita receita = new Receita();
        when(receitaRepository.findById(1)).thenReturn(Optional.of(receita));

        Optional<Receita> foundReceita = receitaService.findById(1);

        assertTrue(foundReceita.isPresent());
        assertEquals(receita, foundReceita.get());
        verify(receitaRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteById() {
        receitaService.deleteById(1);

        verify(receitaRepository, times(1)).deleteById(1);
    }

    @Test
    void testSave() {
        Receita receita = new Receita();

        receitaService.save(receita);

        verify(receitaRepository, times(1)).save(receita);
    }

    @Test
    void testUpdate() {
        Receita receitaAtualizada = new Receita();
        when(receitaRepository.save(receitaAtualizada)).thenReturn(receitaAtualizada);

        Receita updatedReceita = receitaService.update(1, receitaAtualizada);

        assertEquals(receitaAtualizada, updatedReceita);
        assertEquals(1, receitaAtualizada.getId());
        verify(receitaRepository, times(1)).save(receitaAtualizada);
    }
}
