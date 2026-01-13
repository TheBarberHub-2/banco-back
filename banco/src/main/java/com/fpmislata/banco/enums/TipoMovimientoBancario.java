package com.fpmislata.banco.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fpmislata.banco.exception.BusinessException;

public enum TipoMovimientoBancario {
    DEBE,
    HABER;

    @JsonCreator
    public static TipoMovimientoBancario fromString(String value) {
        try {
            return TipoMovimientoBancario.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Tipo de movimiento bancario no válido: " + value);
        }
    }
}
