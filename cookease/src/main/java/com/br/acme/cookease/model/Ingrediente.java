package com.br.acme.cookease.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ingrediente {
    private int codigo;
    private String nome;
    private double preco;
}
