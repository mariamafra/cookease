package com.infnet.auth_service.auth_service.controller;

import com.infnet.auth_service.auth_service.model.JwtResponse;
import com.infnet.auth_service.auth_service.model.LoginRequest;
import com.infnet.auth_service.auth_service.model.Usuario;
import com.infnet.auth_service.auth_service.service.AuthService;
import com.infnet.auth_service.auth_service.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Operation(summary = "Realiza o login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "403", description = "Credenciais incorretas",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
