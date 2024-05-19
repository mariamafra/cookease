package com.br.acme.cookease.controller;

import com.br.acme.cookease.exception.ResourceNotFoundException;
import com.br.acme.cookease.model.Ingrediente;
import com.br.acme.cookease.payload.MessagePayload;
import com.br.acme.cookease.services.IngredienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ingrediente")
public class IngredienteController {
    Logger logger = LoggerFactory.getLogger(IngredienteController.class);
    final IngredienteService ingredienteService;

    public IngredienteController(IngredienteService ingredienteService) {
        this.ingredienteService = ingredienteService;
    }

    @GetMapping
    public ResponseEntity<List<Ingrediente>> getAll(@RequestParam(required = false) Optional<String> nome){
        logger.info("Listando todos os ingredientes");
        if(nome.isEmpty()){
            return ResponseEntity.ok(ingredienteService.getAll());
        } else {
            List<Ingrediente> ingrediente = ingredienteService.filterByName(nome.get());
            if(ingrediente.isEmpty()){
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(ingrediente);
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try {
            logger.info("Listando um ingrediente pelo id");
            Ingrediente localizado = ingredienteService.getById(id);
            return ResponseEntity.ok(localizado);
        } catch (ResourceNotFoundException ex) {
            Map<String, String> message = Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PostMapping
    public ResponseEntity<MessagePayload> save(@RequestBody  Ingrediente ingrediente) {
        logger.info("Criando um  ingrediente");
        ingredienteService.save(ingrediente);
        return ResponseEntity.ok(new MessagePayload("Criado com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable Integer id, @RequestBody Ingrediente ingrediente) {
        logger.info("Atualizando um ingrediente");
        try{
            ingredienteService.update(id, ingrediente);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable Integer id) {
        logger.info("Deletando um ingrediente");
        try {
            ingredienteService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
