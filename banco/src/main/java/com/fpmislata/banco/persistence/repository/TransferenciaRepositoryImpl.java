package com.fpmislata.banco.persistence.repository;

import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.model.Transferencia;
import com.fpmislata.banco.domain.repository.TransferenciaRepository;
import com.fpmislata.banco.persistence.entity.TransferenciaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransferenciaRepositoryImpl implements TransferenciaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Transferencia> findAll(int page, int size) {
        String jpql = "SELECT t FROM TransferenciaEntity t ORDER BY t.fecha DESC";
        List<TransferenciaEntity> entities = entityManager.createQuery(jpql, TransferenciaEntity.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();

        long total = (Long) entityManager.createQuery("SELECT COUNT(t) FROM TransferenciaEntity t")
                .getSingleResult();

        List<Transferencia> transferencias = entities.stream()
                .map(this::toModel)
                .toList();

        return new Page<>(transferencias, page, size, (int) total);
    }

    @Override
    public Optional<Transferencia> findById(long id) {
        TransferenciaEntity entity = entityManager.find(TransferenciaEntity.class, id);
        return Optional.ofNullable(entity).map(this::toModel);
    }

    @Override
    @Transactional
    public Transferencia save(Transferencia transferencia) {
        TransferenciaEntity entity = toEntity(transferencia);
        entityManager.persist(entity);
        return toModel(entity);
    }

    private Transferencia toModel(TransferenciaEntity entity) {
        return new Transferencia(
                entity.getId(),
                entity.getCuentaOrigen(),
                entity.getCuentaDestino(),
                entity.getMonto(),
                entity.getFecha(),
                entity.getConcepto());
    }

    private TransferenciaEntity toEntity(Transferencia model) {
        TransferenciaEntity entity = new TransferenciaEntity();
        entity.setId(model.getId());
        entity.setCuentaOrigen(model.getCuentaOrigen());
        entity.setCuentaDestino(model.getCuentaDestino());
        entity.setMonto(model.getMonto());
        entity.setFecha(model.getFecha());
        entity.setConcepto(model.getConcepto());
        return entity;
    }
}
