package com.fpmislata.banco.persistence.repository;

import com.fpmislata.banco.domain.model.CuentaBancaria;
import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.repository.CuentaBancariaRepository;
import com.fpmislata.banco.persistence.entity.CuentaBancariaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CuentaBancariaRepositoryImpl implements CuentaBancariaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<CuentaBancaria> findAll(int page, int size) {
        String jpql = "SELECT c FROM CuentaBancariaEntity c";
        List<CuentaBancariaEntity> entities = entityManager.createQuery(jpql, CuentaBancariaEntity.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();

        long total = (Long) entityManager.createQuery("SELECT COUNT(c) FROM CuentaBancariaEntity c")
                .getSingleResult();

        List<CuentaBancaria> cuentas = entities.stream()
                .map(this::toModel)
                .toList();

        return new Page<>(cuentas, page, size, (int) total);
    }

    @Override
    public Optional<CuentaBancaria> findById(long id) {
        CuentaBancariaEntity entity = entityManager.find(CuentaBancariaEntity.class, id);
        return Optional.ofNullable(entity).map(this::toModel);
    }

    @Override
    public Optional<CuentaBancaria> findByIban(String iban) {
        String jpql = "SELECT c FROM CuentaBancariaEntity c WHERE c.iban = :iban";
        List<CuentaBancariaEntity> results = entityManager.createQuery(jpql, CuentaBancariaEntity.class)
                .setParameter("iban", iban)
                .getResultList();

        if (results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(toModel(results.get(0)));
    }

    @Override
    @Transactional
    public CuentaBancaria save(CuentaBancaria cuentaBancaria) {
        CuentaBancariaEntity entity = toEntity(cuentaBancaria);
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return toModel(entity);
    }

    @Override
    @Transactional
    public void delete(long id) {
        CuentaBancariaEntity entity = entityManager.find(CuentaBancariaEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    private CuentaBancaria toModel(CuentaBancariaEntity entity) {
        return new CuentaBancaria(
                entity.getId(),
                entity.getSaldo(),
                entity.getIban());
    }

    private CuentaBancariaEntity toEntity(CuentaBancaria model) {
        CuentaBancariaEntity entity = new CuentaBancariaEntity();
        entity.setId(model.getId());
        entity.setSaldo(model.getSaldo());
        entity.setIban(model.getIban());
        return entity;
    }
}
