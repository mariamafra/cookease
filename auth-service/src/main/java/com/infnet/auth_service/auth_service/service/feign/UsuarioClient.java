package com.infnet.auth_service.auth_service.service.feign;

import com.infnet.auth_service.auth_service.model.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("USUARIO-SERVICE")
public class UsuarioClient {
    @GetMapping("email/{email}")
    Usuario getByEmail(@PathVariable("email") String email);
}
