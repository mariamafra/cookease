package com.br.acme.cookease.controller;

import com.br.acme.cookease.exception.ResourceNotFoundException;
import com.br.acme.cookease.model.Receita;
import com.br.acme.cookease.model.Usuario;
import com.br.acme.cookease.payload.MessagePayload;
import com.br.acme.cookease.services.ReceitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @Operation(summary = "Obtém todos as receitas ou filtra por nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receitas encontradas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Receita[].class))}),
            @ApiResponse(responseCode = "404", description = "Nenhum receita encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))})
    })
    @GetMapping
    public ResponseEntity<List<Receita>> getAll(@RequestParam(required = false) Optional<String> nome){
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

    @Operation(summary = "Busca uma receita por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receita encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Receita.class))}),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try {
            Receita localizado = receitaService.getById(id);
            return ResponseEntity.ok(localizado);
        } catch (ResourceNotFoundException ex) {
            Map<String, String> message = Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @Operation(summary = "Cria uma receita")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @PostMapping
    public ResponseEntity<MessagePayload> save(@RequestBody Receita receita) {
        receitaService.save(receita);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));
    }

    @Operation(summary = "Atualiza uma receita")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Atualizado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um Erro",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable Integer id, @RequestBody Receita receita) {
        try{
            receitaService.update(id, receita);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @Operation(summary = "Deleta uma receita")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Deletado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))
                    })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable Integer id) {
        try {
            receitaService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
