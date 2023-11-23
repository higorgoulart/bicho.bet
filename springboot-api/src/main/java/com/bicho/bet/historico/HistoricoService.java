package com.bicho.bet.historico;

import com.bicho.bet.aposta.ApostaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HistoricoService {
    private ApostaService apostaService;

    public Page<HistoricoResponse> getAll(Long idApostador, Pageable pageable) {
        var historicos = apostaService.findHistoricosByIdApostador(idApostador, pageable);

        historicos.forEach(x -> x.setGanhos(x.getGanhos() + apostaService.obterPremio(
                x.getAposta(), x.getTipoAposta(), x.getNumerosAposta(), x.getNumerosResultado())));

        return historicos;
    }
}
