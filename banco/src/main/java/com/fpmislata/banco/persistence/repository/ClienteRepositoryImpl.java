package com.fpmislata.banco.persistence.repository;

import com.fpmislata.banco.domain.model.Cliente;
import com.fpmislata.banco.domain.repository.ClienteRepository;
import com.fpmislata.banco.persistence.entity.ClienteEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Cliente> findByLogin(String login) {
        String jpql = "SELECT c FROM ClienteEntity c WHERE c.login = :login";
        List<ClienteEntity> results = entityManager.createQuery(jpql, ClienteEntity.class)
                .setParameter("login", login)
                .getResultList();
        return results.stream().findFirst().map(this::toModel);
    }

    @Override
    public Optional<Cliente> findByLoginAndApiToken(String login, String apiToken) {
        String jpql = "SELECT c FROM ClienteEntity c WHERE c.login = :login AND c.apiToken = :apiToken";
        List<ClienteEntity> results = entityManager.createQuery(jpql, ClienteEntity.class)
                .setParameter("login", login)
                .setParameter("apiToken", apiToken)
                .getResultList();
        return results.stream().findFirst().map(this::toModel);
    }

    @Override
    public Optional<Cliente> findByDni(String dni) {
        String jpql = "SELECT c FROM ClienteEntity c WHERE c.dni = :dni";
        List<ClienteEntity> results = entityManager.createQuery(jpql, ClienteEntity.class)
                .setParameter("dni", dni)
                .getResultList();
        return results.stream().findFirst().map(this::toModel);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = toEntity(cliente);
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return toModel(entity);
    }

    private Cliente toModel(ClienteEntity entity) {
        return new Cliente(
            entity.getId(),
                entity.getLogin(),
                entity.getPassword(),
                entity.getNombre(),
                entity.getApellido1(),
                entity.getApellido2(),
                entity.getDni(),
                entity.getApiToken());
    }

    private ClienteEntity toEntity(Cliente model) {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(model.getId());
        entity.setLogin(model.getLogin());
        entity.setPassword(model.getPassword());
        entity.setNombre(model.getNombre());
        entity.setApellido1(model.getApellido1());
        entity.setApellido2(model.getApellido2());
        entity.setDni(model.getDni());
        entity.setApiToken(model.getApiToken());
        return entity;
    }
}
