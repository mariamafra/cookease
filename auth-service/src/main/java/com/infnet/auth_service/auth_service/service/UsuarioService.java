package com.infnet.auth_service.auth_service.service;

import com.infnet.auth_service.auth_service.model.Usuario;
import com.infnet.auth_service.auth_service.service.feign.UsuarioClient;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Autowired
    private UsuarioClient usuarioClient;

    public Usuario getByEmail(String email) {
        return usuarioClient.getByEmail(email);
    }
}