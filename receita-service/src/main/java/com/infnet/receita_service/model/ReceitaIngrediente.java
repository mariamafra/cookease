package com.infnet.receita_service.model;

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
@IdClass(ReceitaIngredienteId.class)
@Table(name = "RECEITA_INGREDIENTE")
public class ReceitaIngrediente {
    @Id
    private int receitaId;
    @Id
    private int ingredienteId;

    private int quantidade;
}
