package com.infnet.usuario_service;

import com.infnet.usuario_service.model.Role;
import com.infnet.usuario_service.model.Usuario;
import com.infnet.usuario_service.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UsuarioServiceTests {

    @Autowired
    UsuarioService usuarioService;

    @Test
    @DisplayName("Deve retornar todos os usuários")
    public void testarGetAll() {
        List<Usuario> usuarios = usuarioService.getAll();
        assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar todos os usuários ativos")
    public void testarFindAllUsuariosAtivos() {
        List<Usuario> usuariosAtivos = usuarioService.findAllUsuariosAtivos();
        assertNotNull(usuariosAtivos);
        assertFalse(usuariosAtivos.isEmpty());
        assertTrue(usuariosAtivos.stream().allMatch(u -> u.getStatus() == 1));
    }

    @Test
    @DisplayName("Deve retornar usuários por lista de status")
    public void testarFindAllByStatusList() {
        List<Integer> statusList = Arrays.asList(1, 2);
        List<Usuario> usuariosPorStatus = usuarioService.findAllByStatusList(statusList);
        assertNotNull(usuariosPorStatus);
        assertFalse(usuariosPorStatus.isEmpty());
        assertTrue(usuariosPorStatus.stream().allMatch(u -> statusList.contains(u.getStatus())));
    }

}