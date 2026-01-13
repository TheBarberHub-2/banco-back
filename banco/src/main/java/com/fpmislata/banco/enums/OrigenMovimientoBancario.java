package com.fpmislata.banco.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fpmislata.banco.exception.BusinessException;

public enum OrigenMovimientoBancario {
    TRANSFERENCIA,
    DOMICILIACION,
    TARJETA_BANCARIA;

    @JsonCreator
    public static OrigenMovimientoBancario fromString(String value) {
        try {
            return OrigenMovimientoBancario.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Origen de movimiento bancario no válido: " + value);
        }
    }
}
