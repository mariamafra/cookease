package com.br.acme.cookease.controller;

import com.br.acme.cookease.execption.ResourceNotFoundException;
import com.br.acme.cookease.model.Usuario;
import com.br.acme.cookease.payload.MessagePayload;
import com.br.acme.cookease.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(@RequestParam(required = false) Optional<String> nome){
        logger.info("Listando todos os usuarios");
        if(nome.isEmpty()){
            return ResponseEntity.ok(usuarioService.getAll());
        } else {
            List<Usuario> usuarios = usuarioService.filterByName(nome.get());
            if(usuarios.isEmpty()){
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(usuarios);
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try {
            logger.info("Listando um usuario pelo id");
            Usuario localizado = usuarioService.getById(id);
            return ResponseEntity.ok(localizado);
        } catch (ResourceNotFoundException ex) {
            Map<String, String> message = Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PostMapping
    public ResponseEntity<MessagePayload> save(@RequestBody Usuario usuario) {
        logger.info("Criando um usuario");
        usuarioService.save(usuario);
        return ResponseEntity.ok(new MessagePayload("Criado com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable Integer id, @RequestBody Usuario usuario) {
        logger.info("Atualizando um usuario");
        try{
            usuarioService.update(id, usuario);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    /*@Operation(summary = "Deleta uma usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Deletado com sucesso",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = MessagePayload.class))
            })
    })*/
    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable Integer id) {
        logger.info("Deletando um usuario");
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
