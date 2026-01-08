package com.fpmislata.banco.persistence.repository;

import com.fpmislata.banco.domain.model.MovimientoBancario;
import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.model.TarjetaCredito;
import com.fpmislata.banco.domain.repository.MovimientoBancarioRepository;
import com.fpmislata.banco.persistence.entity.MovimientoBancarioEntity;
import com.fpmislata.banco.persistence.entity.TarjetaCreditoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class MovimientoBancarioRepositoryImpl implements MovimientoBancarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<MovimientoBancario> findAll(int page, int size) {
        String jpql = "SELECT m FROM MovimientoBancarioEntity m ORDER BY m.fecha DESC";
        List<MovimientoBancarioEntity> entities = entityManager.createQuery(jpql, MovimientoBancarioEntity.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();

        long total = (Long) entityManager.createQuery("SELECT COUNT(m) FROM MovimientoBancarioEntity m")
                .getSingleResult();

        List<MovimientoBancario> movimientos = entities.stream()
                .map(this::toModel)
                .toList();

        return new Page<>(movimientos, page, size, (int) total);
    }

    @Override
    public Optional<MovimientoBancario> findById(long id) {
        MovimientoBancarioEntity entity = entityManager.find(MovimientoBancarioEntity.class, id);
        return Optional.ofNullable(entity).map(this::toModel);
    }

    @Override
    @Transactional
    public MovimientoBancario save(MovimientoBancario movimiento) {
        MovimientoBancarioEntity entity = toEntity(movimiento);
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return toModel(entity);
    }

    private MovimientoBancario toModel(MovimientoBancarioEntity entity) {
        TarjetaCredito tarjeta = null;
        if (entity.getTarjetaCreditoOrigen() != null) {
            tarjeta = new TarjetaCredito(
                    entity.getTarjetaCreditoOrigen().getNumeroTarjeta(),
                    entity.getTarjetaCreditoOrigen().getFechaCaducidad(),
                    entity.getTarjetaCreditoOrigen().getCvc(),
                    entity.getTarjetaCreditoOrigen().getNombreCompleto());
        }

        return new MovimientoBancario(
                entity.getId(),
                entity.getTipoMovimientoBancario(),
                entity.getOrigenMovimientoBancario(),
                tarjeta,
                Date.from(entity.getFecha().atZone(ZoneId.systemDefault()).toInstant()),
                entity.getImporte(),
                entity.getConcepto());
    }

    private MovimientoBancarioEntity toEntity(MovimientoBancario model) {
        MovimientoBancarioEntity entity = new MovimientoBancarioEntity();
        entity.setId(model.getId());
        entity.setTipoMovimientoBancario(model.getTipoMovimientoBancario());
        entity.setOrigenMovimientoBancario(model.getOrigenMovimientoBancario());

        if (model.getTarjetaCreditoOrigen() != null) {
            TarjetaCreditoEntity tarjetaEntity = new TarjetaCreditoEntity();
            tarjetaEntity.setNumeroTarjeta(model.getTarjetaCreditoOrigen().getNumeroTarjeta());
            tarjetaEntity.setFechaCaducidad(model.getTarjetaCreditoOrigen().getFechaCaducidad());
            tarjetaEntity.setCvc(model.getTarjetaCreditoOrigen().getCvc());
            tarjetaEntity.setNombreCompleto(model.getTarjetaCreditoOrigen().getNombreCompleto());
            entity.setTarjetaCreditoOrigen(tarjetaEntity);
        }

        entity.setFecha(model.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        entity.setImporte(model.getImporte());
        entity.setConcepto(model.getConcepto());
        return entity;
    }
}
