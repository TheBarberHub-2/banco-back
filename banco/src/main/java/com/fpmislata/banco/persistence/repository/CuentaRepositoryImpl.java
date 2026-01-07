package com.fpmislata.banco.persistence.repository;

import com.fpmislata.banco.domain.model.Cuenta;
import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.repository.CuentaRepository;
import com.fpmislata.banco.persistence.entity.CuentaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CuentaRepositoryImpl implements CuentaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Cuenta> findAll(int page, int size) {
        String jpql = "SELECT c FROM CuentaEntity c";
        List<CuentaEntity> entities = entityManager.createQuery(jpql, CuentaEntity.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();

        long total = (Long) entityManager.createQuery("SELECT COUNT(c) FROM CuentaEntity c")
                .getSingleResult();

        List<Cuenta> cuentas = entities.stream()
                .map(this::toModel)
                .toList();

        return new Page<>(cuentas, page, size, (int) total);
    }

    @Override
    public Page<Cuenta> findByTipoCuenta(com.fpmislata.banco.enums.TipoCuenta tipoCuenta, int page, int size) {
        String jpql = "SELECT c FROM CuentaEntity c WHERE c.tipoCuenta = :tipoCuenta";
        List<CuentaEntity> entities = entityManager.createQuery(jpql, CuentaEntity.class)
                .setParameter("tipoCuenta", tipoCuenta)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();

        long total = (Long) entityManager
                .createQuery("SELECT COUNT(c) FROM CuentaEntity c WHERE c.tipoCuenta = :tipoCuenta")
                .setParameter("tipoCuenta", tipoCuenta)
                .getSingleResult();

        List<Cuenta> cuentas = entities.stream()
                .map(this::toModel)
                .toList();

        return new Page<>(cuentas, page, size, (int) total);
    }

    @Override
    public Optional<Cuenta> findById(long id) {
        CuentaEntity entity = entityManager.find(CuentaEntity.class, id);
        return Optional.ofNullable(entity).map(this::toModel);
    }

    @Override
    public Optional<Cuenta> findByNumeroCuenta(String numeroCuenta) {
        String jpql = "SELECT c FROM CuentaEntity c WHERE c.numeroCuenta = :numeroCuenta";
        List<CuentaEntity> results = entityManager.createQuery(jpql, CuentaEntity.class)
                .setParameter("numeroCuenta", numeroCuenta)
                .getResultList();

        if (results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(toModel(results.get(0)));
    }

    @Override
    @Transactional
    public Cuenta save(Cuenta cuenta) {
        CuentaEntity entity = toEntity(cuenta);
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
        CuentaEntity entity = entityManager.find(CuentaEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    private Cuenta toModel(CuentaEntity entity) {
        return new Cuenta(
                entity.getId(),
                entity.getNumeroCuenta(),
                entity.getTipoCuenta(),
                entity.getSaldo(),
                entity.getTitular());
    }

    private CuentaEntity toEntity(Cuenta model) {
        CuentaEntity entity = new CuentaEntity();
        entity.setId(model.getId());
        entity.setNumeroCuenta(model.getNumeroCuenta());
        entity.setTipoCuenta(model.getTipoCuenta());
        entity.setSaldo(model.getSaldo());
        entity.setTitular(model.getTitular());
        return entity;
    }
}
