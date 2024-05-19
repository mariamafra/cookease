package com.br.acme.cookease.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Receita {
    private int codigo;
    private String nome;
    private List<Ingrediente> ingredientes;
}
