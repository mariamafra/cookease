package com.infnet.auth_service.auth_service.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("USUARIO-SERVICE")
public class UsuarioClient {
    //TODO chamar método do usuário service que busca o usuário por email e autentica
/*    @PostMapping("/{id}")
    Usuario getByEmail(@PathVariable("id") Long id);*/
}
