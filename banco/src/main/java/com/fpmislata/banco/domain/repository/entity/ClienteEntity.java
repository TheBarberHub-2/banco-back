package com.fpmislata.banco.domain.repository.entity;

import java.util.List;

public record ClienteEntity(
        Long id,
        String login,
        String password,
        String nombre,
        String apellido1,
        String apellido2,
        String dni,
        String apiToken,
        List<CuentaBancariaEntity> cuentas) {
    public ClienteEntity(
            Long id,
            String login,
            String password,
            String nombre,
            String apellido1,
            String apellido2,
            String dni,
            String apiToken,
            List<CuentaBancariaEntity> cuentas) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.apiToken = apiToken;
        this.cuentas = cuentas;
    }
}
