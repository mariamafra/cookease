package com.infnet.auth_service.auth_service.service;

import com.infnet.auth_service.auth_service.model.Usuario;
import com.infnet.auth_service.auth_service.service.feign.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

   /* @Autowired
    private UsuarioClient usuarioClient;*/

    public String authenticateUser(String username, String password) {
        // Autenticar o usuário
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenService.generateToken(authentication);
    }

    private Usuario fetchUserDetailsFromUserService(String username) {
        return new Usuario(1, "teste@terste.com", "senha", "user");
       // return usuarioClient.getUserByEmail();
    }
}
