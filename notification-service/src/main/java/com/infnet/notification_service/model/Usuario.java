package com.infnet.notification_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Usuario implements Serializable {
    private Long id;
    private String nome;
    private String email;
    private Integer status;
    private String password;

}
