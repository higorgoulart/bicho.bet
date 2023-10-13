package com.bicho.bet.bicho.bet.services;

import com.bicho.bet.bicho.bet.models.core.EntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseService<T extends EntityId, ID> {
    protected abstract JpaRepository<T, ID> getRepository();

    public List<T> getAll() {
        return getRepository().findAll();
    }

    public T getById(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    public T add(T agendamento) {
        return getRepository().save(agendamento);
    }

    public T update(ID id, T entity) {
        return getRepository().findById(id)
                .map(e -> saveAndReturnSavedEntity(entity, e))
                .orElse(null);
    }

    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    private T saveAndReturnSavedEntity(T entity, T entityFromDB) {
        entity.setId(entityFromDB.getId());
        return getRepository().save(entity);
    }
}
