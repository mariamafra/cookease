package com.br.acme.cookease.services.impl;

import com.br.acme.cookease.model.Chefe;
import com.br.acme.cookease.model.Ingrediente;
import com.br.acme.cookease.model.Receita;
import com.br.acme.cookease.services.AuditService;
import jakarta.persistence.EntityManager;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditServiceImpl implements AuditService {
    @Autowired
    private EntityManager entityManager;

    public List<Chefe> getChefeHistory(int chefeId) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        List<Number> revisions = auditReader.getRevisions(Chefe.class, chefeId);
        return revisions.stream()
                .map(revision -> auditReader.find(Chefe.class, chefeId, revision))
                .collect(Collectors.toList());
    }

    public List<Ingrediente> getIngredienteHistory(int ingredienteId) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        List<Number> revisions = auditReader.getRevisions(Ingrediente.class, ingredienteId);
        return revisions.stream()
                .map(revision -> auditReader.find(Ingrediente.class, ingredienteId, revision))
                .collect(Collectors.toList());
    }

    public List<Receita> getReceitaHistory(int receitaId) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        List<Number> revisions = auditReader.getRevisions(Receita.class, receitaId);
        return revisions.stream()
                .map(revision -> auditReader.find(Receita.class, receitaId, revision))
                .collect(Collectors.toList());
    }

}
