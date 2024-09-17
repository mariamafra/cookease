package com.infnet.notification_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
//@Entity
@Table(name = "USUARIO")
public class Usuario {
    private String nome;
    private String email;
}
