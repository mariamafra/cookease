package com.infnet.auth_service.auth_service.service.feign;

import com.infnet.auth_service.auth_service.model.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("USUARIO-SERVICE")
public interface UsuarioClient {
    @GetMapping("/usuarios/email/{email}")
    Usuario getByEmail(@PathVariable String email);
}
