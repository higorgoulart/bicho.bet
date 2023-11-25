package com.bicho.bet.loterica;

import com.bicho.bet.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LotericaService {
    @Autowired
    private LotericaRepository repository;
    private ModelMapper modelMapper;

    public List<Loterica> getAll() {
        return repository.findAll();
    }

    public Loterica getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Loterica"));
    }

    public Loterica add(LotericaRepresentation.LotericaUpdate create){
        return repository.save(new Loterica(
                create.getNome(),
                create.getUsername(),
                create.getEmail(),
                create.getPassword(),
                create.getCnpj(),
                create.getTelefone(),
                create.getSaldo()
        ));
    }

    public Loterica update(Long id, LotericaRepresentation.LotericaUpdate entity){
        var dbEntity = repository.findById(id).orElseThrow(() -> new NotFoundException("Loterica"));

        modelMapper.map(entity, dbEntity);

        return repository.save(dbEntity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
