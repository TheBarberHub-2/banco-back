package com.fpmislata.banco.domain.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public record CuentaBancariaDto(
                Long id,
                ClienteDto cliente,
                @NotNull(message = "El saldo es obligatorio") BigDecimal saldo,
                @NotBlank(message = "El IBAN es obligatorio") @Size(min = 24, max = 24, message = "El IBAN debe tener 24 caracteres") String iban,
                List<TarjetaCreditoDto> tarjetas,
                List<MovimientoBancarioDto> movimientos) {

        public CuentaBancariaDto(Long id, ClienteDto cliente, BigDecimal saldo, String iban,
                        List<TarjetaCreditoDto> tarjetas, List<MovimientoBancarioDto> movimientos) {
                this.id = id;
                this.cliente = cliente;
                this.saldo = saldo;
                this.iban = iban;
                this.tarjetas = tarjetas == null ? List.of() : List.copyOf(tarjetas);
                this.movimientos = movimientos == null ? List.of() : List.copyOf(movimientos);
        }
}