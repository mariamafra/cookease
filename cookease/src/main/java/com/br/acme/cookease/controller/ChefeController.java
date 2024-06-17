package com.br.acme.cookease.controller;

import com.br.acme.cookease.exception.ResourceNotFoundException;
import com.br.acme.cookease.model.Chefe;
import com.br.acme.cookease.payload.MessagePayload;
import com.br.acme.cookease.services.AuditService;
import com.br.acme.cookease.services.ChefeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/chefe")
@RequiredArgsConstructor
public class ChefeController {
    final ChefeService chefeService;
    final AuditService auditService;

    @Operation(summary = "Obtém todos os usuários ou filtra por nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Chefe[].class))}),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))})
    })
    @GetMapping
    public ResponseEntity<List<Chefe>> getAll(@RequestParam(required = false) Optional<String> nome){
        if(nome.isEmpty()){
            return ResponseEntity.ok(chefeService.getAll());
        } else {
            List<Chefe> chefes = chefeService.getAllByNameStartsWith(nome.get());
            if(chefes.isEmpty()){
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(chefes);
            }
        }
    }

    @Operation(summary = "Busca um usuário por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Chefe.class))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try {
            Optional<Chefe> localizado = chefeService.findById(id);
            return localizado.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (ResourceNotFoundException ex) {
            Map<String, String> message = Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @Operation(summary = "Cria um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @PostMapping
    public ResponseEntity<MessagePayload> save(@RequestBody Chefe chefe) {
        chefeService.save(chefe);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));
    }

    @Operation(summary = "Atualiza um usuário")
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
    public ResponseEntity<MessagePayload> update(@PathVariable Integer id, @RequestBody Chefe chefe) {
        try{
            chefeService.update(id, chefe);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @Operation(summary = "Deleta um usuário")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "202", description = "Deletado com sucesso",
                content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = MessagePayload.class))
                }),
                @ApiResponse(responseCode = "404", description = "Ocorreu um Erro",
                        content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = MessagePayload.class))}
                )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable Integer id) {
        try {
            chefeService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @Operation(summary = "Busca histórico de chefe por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Histórico",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Chefe[].class))})
    })
    @GetMapping("/history/{id}")
    public ResponseEntity<List<Chefe>> getChefeHistory(@PathVariable int id) {
        List<Chefe> history = auditService.getChefeHistory(id);
        return ResponseEntity.ok(history);
    }
}
