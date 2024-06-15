package com.br.acme.cookease.repository;

import com.br.acme.cookease.model.Role;
import com.br.acme.cookease.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.status =1")
    List<Usuario> findAllAtivos();

    @Query("SELECT u FROM Usuario u WHERE u.email=?2 OR u.nome=?1 ORDER BY u.nome asc limit 5")
    List<Usuario> findAllBy(String nome, String email);

    @Query(value = "SELECT u FROM Usuario u WHERE u.email=:emailUsuario OR u.nome=:nome ORDER BY u.email desc limit 2")
    List<Usuario> findAllByNamed(@Param("nome") String nome, @Param("emailUsuario") String email);

    @Query("SELECT u FROM Usuario u WHERE u.status IN :statusList")
    List<Usuario> findAllByStatusIn(@Param("statusList") List<Integer> status);

    @Query("SELECT u FROM Usuario u INNER JOIN u.roles roles WHERE roles in :rolesList")
    List<Usuario> findAllByRoles(@Param("rolesList") List<Role> roles);

}
