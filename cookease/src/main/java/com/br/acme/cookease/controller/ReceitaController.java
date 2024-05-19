package com.br.acme.cookease.controller;

import com.br.acme.cookease.exception.ResourceNotFoundException;
import com.br.acme.cookease.model.Receita;
import com.br.acme.cookease.payload.MessagePayload;
import com.br.acme.cookease.services.ReceitaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/receita")
public class ReceitaController {
    Logger logger = LoggerFactory.getLogger(ReceitaController.class);
    final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping
    public ResponseEntity<List<Receita>> getAll(@RequestParam(required = false) Optional<String> nome){
        logger.info("Listando todos os receitaS");
        if(nome.isEmpty()){
            return ResponseEntity.ok(receitaService.getAll());
        } else {
            List<Receita> receitas = receitaService.filterByName(nome.get());
            if(receitas.isEmpty()){
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(receitas);
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try {
            logger.info("Listando uma receita pelo id");
            Receita localizado = receitaService.getById(id);
            return ResponseEntity.ok(localizado);
        } catch (ResourceNotFoundException ex) {
            Map<String, String> message = Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PostMapping
    public ResponseEntity<MessagePayload> save(@RequestBody Receita receita) {
        logger.info("Criando um receita");
        receitaService.save(receita);
        return ResponseEntity.ok(new MessagePayload("Criado com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable Integer id, @RequestBody Receita receita) {
        logger.info("Atualizando um receita");
        try{
            receitaService.update(id, receita);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable Integer id) {
        logger.info("Deletando um receita");
        try {
            receitaService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
