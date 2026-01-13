package com.fpmislata.banco.persistence;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fpmislata.banco.persistence.dao.jpa.ClienteJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.CuentaBancariaJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.MovimientoBancarioJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.SesionJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.TarjetaCreditoJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.impl.ClienteJpaDaoImpl;
import com.fpmislata.banco.persistence.dao.jpa.impl.CuentaBancariaJpaDaoImpl;
import com.fpmislata.banco.persistence.dao.jpa.impl.MovimientoBancarioJpaDaoImpl;
import com.fpmislata.banco.persistence.dao.jpa.impl.SesionJpaDaoImpl;
import com.fpmislata.banco.persistence.dao.jpa.impl.TarjetaCreditoJpaDaoImpl;

@Configuration
@EntityScan(basePackages = "com.fpmislata.banco.persistence.dao.jpa.entity")
public class PersistenceConfig {

    @Bean
    public ClienteJpaDao clienteJpaDao() {
        return new ClienteJpaDaoImpl();
    }

    @Bean
    public CuentaBancariaJpaDao cuentaBancariaJpaDao() {
        return new CuentaBancariaJpaDaoImpl();
    }

    @Bean
    public MovimientoBancarioJpaDao movimientoBancarioJpaDao() {
        return new MovimientoBancarioJpaDaoImpl();
    }

    @Bean
    public TarjetaCreditoJpaDao tarjetaCreditoJpaDao() {
        return new TarjetaCreditoJpaDaoImpl();
    }

    @Bean
    public SesionJpaDao sesionJpaDao() {
        return new SesionJpaDaoImpl();
    }
}
