package com.fpmislata.banco.controller.webModel.response;

public record ClienteDetailResponse(
        String login,
        String nombre,
        String apellido1,
        String apellido2,
        String dni) {
}
