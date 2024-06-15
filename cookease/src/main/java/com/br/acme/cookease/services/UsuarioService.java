package com.br.acme.cookease.services;

import com.br.acme.cookease.model.Role;
import com.br.acme.cookease.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> getAll();
    List<Usuario> findAllUsuariosAtivos();
    List<Usuario> findAllByStatusList(List<Integer> status);
    List<Usuario> findAllByRoles(List<Role> roles);
}
