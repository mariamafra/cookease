package com.infnet.auth_service.auth_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private Integer status;
    private String password;
    private Set<Role> roles;
}
