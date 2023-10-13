package com.bicho.bet.bicho.bet.controllers;

import com.bicho.bet.bicho.bet.models.core.EntityId;
import com.bicho.bet.bicho.bet.services.BaseService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

public abstract class BaseController<T extends EntityId, ID> {
    protected abstract BaseService<T, ID> getService();

    @GetMapping
    public List<T> getAll() {
        return getService().getAll();
    }

    @GetMapping("/{id}")
    public T getById(@PathVariable ID id) {
        return getService().getById(id);
    }

    @PostMapping
    public T add(@RequestBody T entity) {
        return getService().add(entity);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable ID id, @RequestBody T entity) {
        return getService().update(id, entity);
    }

    @DeleteMapping
    public void delete(@PathParam("id") ID id) {
        getService().delete(id);
    }
}
