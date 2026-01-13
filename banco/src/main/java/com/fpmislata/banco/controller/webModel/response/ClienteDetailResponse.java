package com.fpmislata.banco.controller.webModel.response;

public record ClienteDetailResponse(
        Long id,
        String nombre,
        String apellido1,
        String apellido2,
        String dni) {
}
