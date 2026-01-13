package com.fpmislata.banco.domain.model;

import java.time.LocalDateTime;

public class Sesion {

    private Long id;
    private Cliente usuario;
    private String token;
    private LocalDateTime expiredDate;

    public Sesion(Long id, Cliente usuario, String token, LocalDateTime expiredDate) {
        this.id = id;
        this.usuario = usuario;
        this.token = token;
        this.expiredDate = expiredDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getUsuario() {
        return usuario;
    }

    public void setUsuario(Cliente usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }
}
