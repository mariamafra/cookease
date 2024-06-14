package com.br.acme.cookease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "chefe_id")
    private Chefe chefe;
//    @ManyToMany(mappedBy = "receitas")
//    private List<Ingrediente> ingredientes;
}
