package com.fpmislata.banco.domain.repository;

import com.fpmislata.banco.domain.model.Cliente;
import java.util.Optional;

public interface ClienteRepository {
    Optional<Cliente> findByLogin(String login);

    Optional<Cliente> findByLoginAndApiToken(String login, String apiToken);

    Optional<Cliente> findByDni(String dni);

    Cliente save(Cliente cliente);
}
