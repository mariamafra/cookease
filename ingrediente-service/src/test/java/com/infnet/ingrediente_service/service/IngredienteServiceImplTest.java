package com.infnet.ingrediente_service.service;

import com.infnet.ingrediente_service.model.Ingrediente;
import com.infnet.ingrediente_service.repository.IngredienteRepository;
import com.infnet.ingrediente_service.service.impl.IngredienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class IngredienteServiceImplTest {

    @Mock
    private IngredienteRepository ingredienteRepository;

    @InjectMocks
    private IngredienteServiceImpl ingredienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Ingrediente ingrediente1 = new Ingrediente();
        Ingrediente ingrediente2 = new Ingrediente();
        when(ingredienteRepository.findAll()).thenReturn(Arrays.asList(ingrediente1, ingrediente2));

        List<Ingrediente> ingredientes = ingredienteService.getAll();

        assertEquals(2, ingredientes.size());
        verify(ingredienteRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Ingrediente ingrediente = new Ingrediente();
        when(ingredienteRepository.findById(1)).thenReturn(Optional.of(ingrediente));

        Optional<Ingrediente> foundIngrediente = ingredienteService.findById(1);

        assertTrue(foundIngrediente.isPresent());
        assertEquals(ingrediente, foundIngrediente.get());
        verify(ingredienteRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteById() {
        ingredienteService.deleteById(1);
        verify(ingredienteRepository, times(1)).deleteById(1);
    }

    @Test
    void testSave() {
        Ingrediente ingrediente = new Ingrediente();
        ingredienteService.save(ingrediente);
        verify(ingredienteRepository, times(1)).save(ingrediente);
    }

    @Test
    void testUpdate() {
        Ingrediente ingredienteAtualizada = new Ingrediente();
        when(ingredienteRepository.save(ingredienteAtualizada)).thenReturn(ingredienteAtualizada);

        Ingrediente updatedIngrediente = ingredienteService.update(1, ingredienteAtualizada);

        assertEquals(ingredienteAtualizada, updatedIngrediente);
        assertEquals(1, ingredienteAtualizada.getId());
        verify(ingredienteRepository, times(1)).save(ingredienteAtualizada);
    }
}
