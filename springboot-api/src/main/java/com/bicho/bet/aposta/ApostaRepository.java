package com.bicho.bet.aposta;

import com.bicho.bet.core.CustomQuerydslPredicateExecutor;
import com.bicho.bet.apostador.historico.HistoricoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Long>, CustomQuerydslPredicateExecutor<Aposta> {
    @Query("SELECT new com.bicho.bet.apostador.historico.HistoricoResponse(a.data, a.jogo.loterica.nome, a.tipo, a.numeros, COALESCE(r.numeros, '[]'), a.valor, COALESCE(a.premio, 0.0)) " +
            "FROM Aposta a " +
            "LEFT JOIN Resultado r ON a.jogo = r.jogo " +
            "WHERE a.apostador.id = :idApostador")
    Page<HistoricoResponse> findHistoricosByIdApostador(@Param("idApostador") Long idApostador, Pageable pageable);
}
