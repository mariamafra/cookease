package com.infnet.ingrediente_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "INGREDIENTE_RECEITA")
public class IngredienteReceita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    Long receitaId;
    Long ingredienteId;
    int quantidade;
}
