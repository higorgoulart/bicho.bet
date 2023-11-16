package com.bicho.bet.aposta;

import com.bicho.bet.core.CustomQuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Long>, CustomQuerydslPredicateExecutor<Aposta> {
}
