package com.fpmislata.banco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fpmislata.banco.domain.repository.ClienteRepository;
import com.fpmislata.banco.domain.repository.CuentaBancariaRepository;
import com.fpmislata.banco.domain.repository.MovimientoBancarioRepository;
import com.fpmislata.banco.domain.repository.SesionRepository;
import com.fpmislata.banco.domain.repository.TarjetaCreditoRepository;
import com.fpmislata.banco.domain.service.AuthService;
import com.fpmislata.banco.domain.service.ClienteService;
import com.fpmislata.banco.domain.service.CuentaBancariaService;
import com.fpmislata.banco.domain.service.MovimientoBancarioService;
import com.fpmislata.banco.domain.service.PagoTarjetaService;
import com.fpmislata.banco.domain.service.SesionService;
import com.fpmislata.banco.domain.service.TarjetaCreditoService;
import com.fpmislata.banco.domain.service.TransferenciaService;
import com.fpmislata.banco.domain.service.impl.AuthServiceImpl;
import com.fpmislata.banco.domain.service.impl.ClienteServiceImpl;
import com.fpmislata.banco.domain.service.impl.CuentaBancariaServiceImpl;
import com.fpmislata.banco.domain.service.impl.MovimientoBancarioServiceImpl;
import com.fpmislata.banco.domain.service.impl.PagoTarjetaServiceImpl;
import com.fpmislata.banco.domain.service.impl.SesionServiceImpl;
import com.fpmislata.banco.domain.service.impl.TarjetaCreditoServiceImpl;
import com.fpmislata.banco.domain.service.impl.TransferenciaServiceImpl;
import com.fpmislata.banco.persistence.PersistenceConfig;
import com.fpmislata.banco.persistence.dao.jpa.ClienteJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.CuentaBancariaJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.MovimientoBancarioJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.SesionJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.TarjetaCreditoJpaDao;
import com.fpmislata.banco.persistence.repository.ClienteRepositoryImpl;
import com.fpmislata.banco.persistence.repository.CuentaBancariaRepositoryImpl;
import com.fpmislata.banco.persistence.repository.MovimientoBancarioRepositoryImpl;
import com.fpmislata.banco.persistence.repository.SesionRepositoryImpl;
import com.fpmislata.banco.persistence.repository.TarjetaCreditoRepositoryImpl;

@Configuration
@Import(PersistenceConfig.class)
public class SpringConfig {

    @Bean
    public ClienteRepository clienteRepository(ClienteJpaDao clienteJpaDao) {
        return new ClienteRepositoryImpl(clienteJpaDao);
    }

    @Bean
    public ClienteService clienteService(ClienteRepository clienteRepository) {
        return new ClienteServiceImpl(clienteRepository);
    }

    @Bean
    public CuentaBancariaRepository cuentaBancariaRepository(CuentaBancariaJpaDao cuentaBancariaJpaDao) {
        return new CuentaBancariaRepositoryImpl(cuentaBancariaJpaDao);
    }

    @Bean
    public CuentaBancariaService cuentaBancariaService(CuentaBancariaRepository cuentaBancariaRepository,
            TarjetaCreditoService tarjetaCreditoService) {
        return new CuentaBancariaServiceImpl(cuentaBancariaRepository, tarjetaCreditoService);
    }

    @Bean
    public MovimientoBancarioRepository movimientoBancarioRepository(
            MovimientoBancarioJpaDao movimientoBancarioJpaDao) {
        return new MovimientoBancarioRepositoryImpl(movimientoBancarioJpaDao);
    }

    @Bean
    public MovimientoBancarioService movimientoBancarioService(
            MovimientoBancarioRepository movimientoBancarioRepository) {
        return new MovimientoBancarioServiceImpl(movimientoBancarioRepository);
    }

    @Bean
    public TarjetaCreditoRepository tarjetaCreditoRepository(TarjetaCreditoJpaDao tarjetaCreditoJpaDao) {
        return new TarjetaCreditoRepositoryImpl(tarjetaCreditoJpaDao);
    }

    @Bean
    public TarjetaCreditoService tarjetaCreditoService(TarjetaCreditoRepository tarjetaCreditoRepository) {
        return new TarjetaCreditoServiceImpl(tarjetaCreditoRepository);
    }

    @Bean
    public SesionRepository sesionRepository(SesionJpaDao sesionJpaDao) {
        return new SesionRepositoryImpl(sesionJpaDao);
    }

    @Bean
    public SesionService sesionService(SesionRepository sesionRepository) {
        return new SesionServiceImpl(sesionRepository);
    }

    @Bean
    public AuthService authService(ClienteService clienteService, SesionService sesionService) {
        return new AuthServiceImpl(clienteService, sesionService);
    }

    @Bean
    public PagoTarjetaService pagoTarjetaService(ClienteService clienteService,
            TarjetaCreditoService tarjetaCreditoService,
            CuentaBancariaService cuentaBancariaService,
            MovimientoBancarioService movimientoBancarioService) {
        return new PagoTarjetaServiceImpl(clienteService, tarjetaCreditoService, cuentaBancariaService,
                movimientoBancarioService);
    }

    @Bean
    public TransferenciaService transferenciaService(ClienteService clienteService,
            CuentaBancariaService cuentaBancariaService,
            MovimientoBancarioService movimientoBancarioService) {
        return new TransferenciaServiceImpl(clienteService, cuentaBancariaService,
                movimientoBancarioService);
    }
}
