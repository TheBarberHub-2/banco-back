package com.fpmislata.banco.domain.model;

import com.fpmislata.banco.enums.OrigenMovimientoBancario;
import com.fpmislata.banco.enums.TipoMovimientoBancario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoBancario {
    private Long id;
    private TipoMovimientoBancario tipoMovimientoBancario;
    private OrigenMovimientoBancario origenMovimientoBancario;
    private TarjetaCredito tarjetaCreditoOrigen;
    private Date fecha;
    private BigDecimal importe;
    private String concepto;
}
