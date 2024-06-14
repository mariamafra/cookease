package com.br.acme.cookease.repository;

import com.br.acme.cookease.model.Chefe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefeRepository extends JpaRepository<Chefe, Integer> {
}
