package com.br.acme.cookease.services;

import com.br.acme.cookease.exception.ResourceNotFoundException;
import com.br.acme.cookease.model.Ingrediente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredienteServiceOdl {
    List<Ingrediente> ingredientes = initValues();
    private List<Ingrediente> initValues() {
        ArrayList<Ingrediente> ingredientes1 = new ArrayList<>();
//        ingredientes1.add(new Ingrediente(0, "Cebola", 8.38));
//        ingredientes1.add(new Ingrediente(1, "Milho", 1.22));
        return ingredientes1;
    }

    public List<Ingrediente> getAll() {
        return this.ingredientes;
    }

    public Ingrediente getById(int id) {
        if(id < 0){
            throw new ResourceNotFoundException("Valor inválido - Não pode ser negativo");
        } else {
            Optional<Ingrediente> ingredienteOpt = ingredientes.stream().filter(ingrediente -> ingrediente.getId() == id).findFirst();
            if(ingredienteOpt.isEmpty()) throw  new ResourceNotFoundException("Objeto não encontrado");
            return ingredienteOpt.get();
        }
    }

    public List<Ingrediente> filterByName(String nome) {
        List<Ingrediente> all = getAll();
        return all.stream().filter(ingrediente -> ingrediente.getNome().startsWith(nome)).toList();
    }

    public void save(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    public void update(Integer id, Ingrediente ingrediente) {
        if(resourceNotFound(id)) {
            throw new ResourceNotFoundException("Ingrediente Não Localizada");
        }
        ingredientes.set(id, ingrediente);
    }

    public void deleteById(Integer id) {
        if(resourceNotFound(id)) {
            throw new ResourceNotFoundException("Ingrediente Não Localizada");
        }
        ingredientes.remove(ingredientes.get(id));
    }

    private boolean resourceNotFound(Integer id) {
        return ingredientes.stream().filter(ingrediente -> ingrediente.getId() == id).findFirst().isEmpty();
    }
}
