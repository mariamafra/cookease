package com.infnet.receita_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReceitaIngredienteId implements Serializable {
    private int receitaId;
    private int ingredienteId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceitaIngredienteId that = (ReceitaIngredienteId) o;
        return receitaId == that.receitaId && ingredienteId == that.ingredienteId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(receitaId, ingredienteId);
    }
}
