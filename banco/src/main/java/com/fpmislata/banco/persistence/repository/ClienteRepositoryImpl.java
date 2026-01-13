package com.fpmislata.banco.persistence.repository;

import com.fpmislata.banco.domain.repository.ClienteRepository;
import com.fpmislata.banco.domain.repository.entity.ClienteEntity;
import com.fpmislata.banco.persistence.dao.jpa.ClienteJpaDao;
import com.fpmislata.banco.persistence.repository.mapper.ClienteMapper;

import java.util.Optional;

public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteJpaDao clienteJpaDao;

    public ClienteRepositoryImpl(ClienteJpaDao clienteJpaDao) {
        this.clienteJpaDao = clienteJpaDao;
    }

    @Override
    public Optional<ClienteEntity> findByLogin(String login) {
        return clienteJpaDao.findByLogin(login)
                .map(ClienteMapper.getInstance()::fromJpaToEntity);
    }
}
