package com.fpmislata.banco.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private Long id;
    private String login;
    private String password;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;
    private String apiToken;
    
}
