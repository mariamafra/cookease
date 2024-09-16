package com.infnet.usuario_service.repository;

import com.infnet.usuario_service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByNomeIgnoreCase(String nome);
}
