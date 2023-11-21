package com.bicho.bet.resultado;

import com.bicho.bet.aposta.Aposta;
import com.bicho.bet.aposta.ApostaService;
import com.bicho.bet.apostador.ApostadorService;
import com.bicho.bet.core.BetNumber;
import com.bicho.bet.exceptions.NotFoundException;
import com.bicho.bet.jogo.JogoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResultadoService {
    private ModelMapper modelMapper;
    private ResultadoRepository repository;
    private JogoService jogoService;
    private ApostaService apostaService;
//    private ApostadorService apostadorService;

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
                .numeros(create.getNumeros().stream().map(BetNumber::parseBetNumber).toList())
                .build());
    }

    public Double premiarVencedores(Long idJogo, List<BetNumber> resultados) {
        var total = 0.0;

        for (Aposta aposta : apostaService.obterApostasPorJogo(idJogo)) {
            var apostador = aposta.getApostador();

            var premio = aposta.obterPremio(resultados);

            apostador.setSaldo(apostador.getSaldo() + premio);

//            apostadorService.update(apostador.getId(), apostador);

            total += premio;
        }

        return total;
    }
}
