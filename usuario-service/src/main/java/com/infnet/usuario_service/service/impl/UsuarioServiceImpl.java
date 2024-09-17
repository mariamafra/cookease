package com.infnet.usuario_service.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.infnet.usuario_service.model.Role;
import com.infnet.usuario_service.model.Usuario;
import com.infnet.usuario_service.rabbitmq.UsuarioProducer;
import com.infnet.usuario_service.repository.RoleRepository;
import com.infnet.usuario_service.service.UsuarioService;
import com.infnet.usuario_service.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final UsuarioProducer producer;

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> findAllUsuariosAtivos() {
        return usuarioRepository.findAllAtivos();
    }

    @Override
    public List<Usuario> findAllByStatusList(List<Integer> status) {
        return usuarioRepository.findAllByStatusIn(status);
    }

//    @Override
//    public List<Usuario> findAllByRoles(List<Role> roles) {
//        return usuarioRepository.findAllByRoles(roles);
//    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void save(Usuario usuario) {
        usuarioRepository.save(encode(usuario));
    }

    private Usuario encode(Usuario usuario) {
        String bcryptHashString = BCrypt.withDefaults().hashToString(10, usuario.getPassword().toCharArray());
        usuario.setPassword(bcryptHashString);
        return usuario;
    }
    @Override
    public Usuario update(Long id, Usuario usuarioAtualizado) {
        usuarioAtualizado.setId(id);
        return usuarioRepository.save(usuarioAtualizado);
    }

    @Override
    public Usuario adicionarRoleAoUsuario(String emailUsuario, String nomeRole) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(emailUsuario);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Optional<Role> roleOpt = roleRepository.findByNomeIgnoreCase(nomeRole);
        if (roleOpt.isEmpty()) {
            throw new RuntimeException("Role não encontrada");
        }

        Usuario usuario = usuarioOpt.get();
        Role role = roleOpt.get();

        usuario.getRoles().add(role);

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }


    public void notificar(Usuario usuario) throws JsonProcessingException {
        producer.send(usuario);
    }
}