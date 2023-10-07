package com.bicho.bet.bicho.bet.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractService<T, ID> implements Service<T, ID> {
    protected abstract JpaRepository<T, ID> getRepository();

    @Override
    public List<T> obterTodos() {
        return getRepository().findAll();
    }

    @Override
    public T obterPorId(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public T adicionar(T agendamento) throws Exception {
        return getRepository().save(agendamento);
    }

    @Override
    public T atualizar(ID id, T agendamento) {
        return getRepository().save(agendamento);
    }

    @Override
    public void deletar(ID id) {
        getRepository().deleteById(id);
    }
}
