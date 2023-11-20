package com.bicho.bet.resultado;

import com.bicho.bet.exceptions.NotFoundException;
import com.bicho.bet.jogo.JogoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResultadoService {
    private ModelMapper modelMapper;
    private ResultadoRepository repository;
    private JogoService jogoService;

    public Page<Resultado> getAll(String filter, Pageable pageable) {
        return repository.findAll(filter, Resultado.class, pageable);
    }

    public Resultado getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Resultado"));
    }

    public Resultado add(ResultadoRepresentation.ResultadoCreate create) {
        return repository.save(Resultado.builder()
                .jogo(jogoService.getById(create.getJogo()))
                .data(create.getData())
                .numeros(new NumeroResultado(create.getNumeros().stream().map(ResultadoRepresentation.NumeroResultadoCreate::getNumero).toList()))
                .build());
    }
}
