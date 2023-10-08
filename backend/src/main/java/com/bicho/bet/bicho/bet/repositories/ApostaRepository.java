package com.bicho.bet.bicho.bet.repositories;

import com.bicho.bet.bicho.bet.models.aposta.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Long>, CustomQuerydslPredicateExecutor<Aposta> {
}
