package com.br.acme.cookease.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
    private int codigo;
    private String nome;
    private String sobrenome;
}
