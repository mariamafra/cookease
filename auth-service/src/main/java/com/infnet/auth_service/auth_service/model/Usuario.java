package com.infnet.auth_service.auth_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Usuario {
    private int id;
    private String username;
    private String password;
    private String role;
}
