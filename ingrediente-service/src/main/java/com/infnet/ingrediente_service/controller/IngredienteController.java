package com.infnet.ingrediente_service.controller;

import com.infnet.ingrediente_service.exception.ResourceNotFoundException;
import com.infnet.ingrediente_service.model.Ingrediente;
import com.infnet.ingrediente_service.payload.MessagePayload;
import com.infnet.ingrediente_service.service.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ingrediente")
@RequiredArgsConstructor
public class IngredienteController {
    @Autowired
    IngredienteService ingredienteService;

    @Operation(summary = "Obtém todas as ingredientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingredientes encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ingrediente[].class))}),
            @ApiResponse(responseCode = "404", description = "Nenhum ingrediente encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))})
    })
    @GetMapping
    public ResponseEntity<List<Ingrediente>> getAll(){
        List<Ingrediente> ingredientes = ingredienteService.getAll();
        if(ingredientes.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredientes);
    }

    @Operation(summary = "Busca um ingrediente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ingrediente.class))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try {
            Optional<Ingrediente> localizado = ingredienteService.findById(id);
            return localizado.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (ResourceNotFoundException ex) {
            Map<String, String> message = Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @Operation(summary = "Cria um ingrediente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @PostMapping
    public ResponseEntity<MessagePayload> save(@RequestBody Ingrediente ingrediente) {
        ingredienteService.save(ingrediente);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));
    }

    @Operation(summary = "Atualiza um ingrediente")
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
    public ResponseEntity<MessagePayload> update(@PathVariable Integer id, @RequestBody Ingrediente ingrediente) {
        try{
            ingredienteService.update(id, ingrediente);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @Operation(summary = "Deleta um ingrediente")
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
            ingredienteService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
