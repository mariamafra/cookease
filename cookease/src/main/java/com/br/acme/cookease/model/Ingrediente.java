package com.br.acme.cookease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String nome;
    private double preco;
//    @ManyToMany/*(cascade = CascateType.ALL)*/
//    @JoinTable(name = "ingrediente_receitas",
//            joinColumns = @JoinColumn(name = "ingredient_id"),
//            inverseJoinColumns = @JoinColumn( name = "receita_id"))
//    private List<Receita> receitas;
}
