package com.infnet.usuario_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infnet.usuario_service.exception.ResourceNotFoundException;
import com.infnet.usuario_service.model.Usuario;
import com.infnet.usuario_service.payload.MessagePayload;
import com.infnet.usuario_service.service.UsuarioService;
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

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @Operation(summary = "Obtém todas as usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario[].class))}),
            @ApiResponse(responseCode = "404", description = "Nenhum usuario encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))})
    })
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        List<Usuario> usuarios = usuarioService.getAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Busca um usuario por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))})
    })
    @GetMapping("/ativos")
    public ResponseEntity<List<Usuario>> getAllActive(){
        List<Usuario> usuarios = usuarioService.findAllUsuariosAtivos();
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Cria um usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<MessagePayload> save(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));
    }


    @Operation(summary = "Adiciona uma role no usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Adicionado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @PostMapping("/{email}/roles/{roleNome}")
    public ResponseEntity<Usuario> adicionarRoleAoUsuario(@PathVariable String email, @PathVariable String roleNome) {
        Usuario usuario = usuarioService.adicionarRoleAoUsuario(email, roleNome);
        return ResponseEntity.ok(usuario);
    }


    @Operation(summary = "Atualiza um usuario")
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
    public ResponseEntity<MessagePayload> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        try{
            usuarioService.update(id, usuario);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @Operation(summary = "Deleta um usuario")
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
    public ResponseEntity<MessagePayload> delete(@PathVariable Long id) {
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> getByEmail(@PathVariable String email) {
        try {
            Usuario usuario = usuarioService.findByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/notificacao")
    public ResponseEntity<Map<String, String>> notificar(@RequestBody Usuario usuario) {
        try {
            usuarioService.notificar(usuario);
        } catch (JsonProcessingException e) {
            ResponseEntity.internalServerError().build();
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(Map.of("message", "Notificação emitida com sucesso"));
    }
}
