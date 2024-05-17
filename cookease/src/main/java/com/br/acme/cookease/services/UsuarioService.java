package com.br.acme.cookease.services;

import com.br.acme.cookease.execption.ResourceNotFoundException;
import com.br.acme.cookease.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    List<Usuario> usuarios = initValues();
    private List<Usuario> initValues() {
        ArrayList<Usuario> usuarios1 = new ArrayList<>();
        usuarios1.add(new Usuario(0, "Duda", "Mafra"));
        usuarios1.add(new Usuario(1, "Rafaela", "Curty"));
        return usuarios1;
    }

    public List<Usuario> getAll() {
        return this.usuarios;
    }

    public Usuario getById(int id) {
        if(id < 0){
            throw new ResourceNotFoundException("Valor inválido - Não pode ser negativo");
        } else {
            Optional<Usuario> usuarioOpt = usuarios.stream().filter(usuario -> usuario.getCodigo() == id).findFirst();
            if(usuarioOpt.isEmpty()) throw  new ResourceNotFoundException("Objeto não encontrado");
            return usuarioOpt.get();
        }
    }

    public List<Usuario> filterByName(String nome) {
        List<Usuario> all = getAll();
        return all.stream().filter(usuario -> usuario.getNome().startsWith(nome)).toList();
    }

    public void save(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void update(Integer id, Usuario usuario) {
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
        return usuarios.stream().filter(usuario -> usuario.getCodigo() == id).findFirst().isEmpty();
    }
}
