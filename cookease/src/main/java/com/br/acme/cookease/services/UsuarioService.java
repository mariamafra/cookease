package com.br.acme.cookease.services;

import com.br.acme.cookease.exception.ResourceNotFoundException;
import com.br.acme.cookease.model.Chefe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    List<Chefe> usuarios = initValues();
    private List<Chefe> initValues() {
        ArrayList<Chefe> usuarios1 = new ArrayList<>();
        usuarios1.add(new Chefe(0, "Duda", "Mafra"));
        usuarios1.add(new Chefe(1, "Rafaela", "Curty"));
        return usuarios1;
    }

    public List<Chefe> getAll() {
        return this.usuarios;
    }

    public Chefe getById(int id) {
        if(id < 0){
            throw new ResourceNotFoundException("Valor inválido - Não pode ser negativo");
        } else {
            Optional<Chefe> usuarioOpt = usuarios.stream().filter(usuario -> usuario.getId() == id).findFirst();
            if(usuarioOpt.isEmpty()) throw  new ResourceNotFoundException("Objeto não encontrado");
            return usuarioOpt.get();
        }
    }

    public List<Chefe> filterByName(String nome) {
        List<Chefe> all = getAll();
        return all.stream().filter(usuario -> usuario.getNome().startsWith(nome)).toList();
    }

    public void save(Chefe usuario) {
        usuarios.add(usuario);
    }

    public void update(Integer id, Chefe usuario) {
        if(resourceNotFound(id)) {
            throw new ResourceNotFoundException("usuario Não Localizada");
        }
        usuarios.set(id, usuario);
    }

    public void deleteById(Integer id) {
        if(resourceNotFound(id)) {
            throw new ResourceNotFoundException("usuario Não Localizada");
        }
        usuarios.remove(usuarios.get(id));
    }

    private boolean resourceNotFound(Integer id) {
        return usuarios.stream().filter(usuario -> usuario.getId() == id).findFirst().isEmpty();
    }
}
