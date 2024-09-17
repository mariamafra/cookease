package com.infnet.auth_service.auth_service;

import com.infnet.auth_service.auth_service.service.AuthService;
import com.infnet.auth_service.auth_service.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticateUser() {
        String username = "user";
        String password = "password";
        String expectedToken = "jwt_token";

        // Simulando o comportamento do AuthenticationManager
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        // Simulando o comportamento do TokenService
        when(tokenService.generateToken(authentication)).thenReturn(expectedToken);

        // Executando o método a ser testado
        String token = authService.authenticateUser(username, password);

        // Verificando se o token foi gerado corretamente
        assertEquals(expectedToken, token);
        assertEquals(authentication, SecurityContextHolder.getContext().getAuthentication());
    }
}
