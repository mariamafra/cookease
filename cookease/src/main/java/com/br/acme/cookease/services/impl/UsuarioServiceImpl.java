package com.br.acme.cookease.services.impl;

import com.br.acme.cookease.model.Role;
import com.br.acme.cookease.model.Usuario;
import com.br.acme.cookease.repository.UsuarioRepository;
import com.br.acme.cookease.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> findAllUsuariosAtivos() {
        return usuarioRepository.findAllAtivos();
    }

    @Override
    public List<Usuario> findAllByStatusList(List<Integer> status) {
        return usuarioRepository.findAllByStatusIn(status);
    }

    @Override
    public List<Usuario> findAllByRoles(List<Role> roles) {
        return null; //usuarioRepository.findAllByRoles(roles);
    }
}
