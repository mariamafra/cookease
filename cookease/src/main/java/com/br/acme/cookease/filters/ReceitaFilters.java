package com.br.acme.cookease.filters;

import com.br.acme.cookease.model.Chefe;
import com.br.acme.cookease.model.Ingrediente;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReceitaFilters {
    private Optional<String> nome;
    private Optional<Chefe> chefe;
    private Optional<List<Ingrediente>> ingredientes;
}
