package com.bicho.bet.apostador;

import com.bicho.bet.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApostadorService {
    private ModelMapper modelMapper;
    private ApostadorRepository repository;

    public List<Apostador> getAll() {
        return repository.findAll();
    }

    public Apostador getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Apostador"));
    }

    public Apostador add(ApostadorRepresentation.ApostadorUpdate create) {
        return repository.save(new Apostador(
                create.getNome(),
                create.getUsername(),
                create.getEmail(),
                create.getPassword(),
                create.getCpf(),
                create.getSaldo(),
                create.getDepositado(),
                create.getDivida(),
                create.getLimite()
        ));
    }

    public Apostador update(Long id, ApostadorRepresentation.ApostadorUpdate entity) {
        var dbEntity = repository.findById(id).orElseThrow(() -> new NotFoundException("Apostador"));

        modelMapper.map(entity, dbEntity);

        return repository.save(dbEntity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void sacar(Long id, double valor) {
        var apostador = repository.findById(id).orElseThrow(() -> new NotFoundException("Apostador"));

        apostador.sacar(valor);

        repository.save(apostador);
    }

    public void depositar(Long id, double valor) {
        var apostador = repository.findById(id).orElseThrow(() -> new NotFoundException("Apostador"));

        apostador.depositar(valor);

        repository.save(apostador);
    }
}
