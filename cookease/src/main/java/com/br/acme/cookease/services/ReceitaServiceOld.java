package com.br.acme.cookease.services;

import com.br.acme.cookease.exception.ResourceNotFoundException;
import com.br.acme.cookease.model.Ingrediente;
import com.br.acme.cookease.model.Receita;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceitaServiceOld {
    List<Receita> receitas = initValues();
    private List<Receita> initValues() {
        ArrayList<Receita> receitas1 = new ArrayList<>();
        ArrayList<Ingrediente> ingredientes1 = new ArrayList<>();
        ingredientes1.add(new Ingrediente(0, "Fuba", 8.38));
        ingredientes1.add(new Ingrediente(0, "Farinha", 2.28));
//        receitas1.add(new Receita(0, "Bolo de Fuba", ingredientes1));
//        receitas1.add(new Receita(1, "Estrogonofe de frango", ingredientes1));
        return receitas1;
    }

    public List<Receita> getAll() {
        return this.receitas;
    }

    public Receita getById(int id) {
        if(id < 0){
            throw new ResourceNotFoundException("Valor inválido - Não pode ser negativo");
        } else {
            Optional<Receita> receitaOpt = receitas.stream().filter(receita -> receita.getId() == id).findFirst();
            if(receitaOpt.isEmpty()) throw  new ResourceNotFoundException("Objeto não encontrado");
            return receitaOpt.get();
        }
    }

    public List<Receita> filterByName(String nome) {
        List<Receita> all = getAll();
        return all.stream().filter(receita -> receita.getNome().startsWith(nome)).toList();
    }

    public void save(Receita receita) {
        receitas.add(receita);
    }

    public void update(Integer id, Receita receita) {
        if(resourceNotFound(id)) {
            throw new ResourceNotFoundException("Receita Não Localizada");
        }
        receitas.set(id, receita);
    }

    public void deleteById(Integer id) {
        if(resourceNotFound(id)) {
            throw new ResourceNotFoundException("Receita Não Localizada");
        }
        receitas.remove(receitas.get(id));
    }

    private boolean resourceNotFound(Integer id) {
        return receitas.stream().filter(receita -> receita.getId() == id).findFirst().isEmpty();
    }
}
