package com.infnet.usuario_service.service;

import com.infnet.usuario_service.model.Role;
import com.infnet.usuario_service.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> getAll();
    List<Usuario> findAllUsuariosAtivos();
    List<Usuario> findAllByStatusList(List<Integer> status);
//    List<Usuario> findAllByRoles(List<Role> roles);
    void deleteById(Long id);
    void save(Usuario usuario);
    Usuario update(Long id, Usuario usuarioAtualizado);
    Usuario adicionarRoleAoUsuario(String emailUsuario, String nomeRole);
    Usuario findByEmail(String email);
}