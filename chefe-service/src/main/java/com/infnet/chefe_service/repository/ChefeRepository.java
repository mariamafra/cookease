package com.infnet.chefe_service.repository;

import com.infnet.chefe_service.model.Chefe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChefeRepository extends JpaRepository<Chefe, Integer> {
    List<Chefe> findAllByNomeStartsWith(String nome);
    List<Chefe> findAllByNomeContains(String nome);
}
