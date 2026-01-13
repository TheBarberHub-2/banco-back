package com.fpmislata.banco.controller.webModel.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

public record PagoRequest(
        @DecimalMin(value = "0.01", inclusive = true, message = "El precio debe ser mayor que 0") BigDecimal importe,
        @Size(min = 3) String concepto) {

}
