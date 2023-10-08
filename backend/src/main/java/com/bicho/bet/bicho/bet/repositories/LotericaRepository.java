package com.bicho.bet.bicho.bet.repositories;

import com.bicho.bet.bicho.bet.models.conta.Loterica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotericaRepository extends JpaRepository<Loterica, Long>, CustomQuerydslPredicateExecutor<Loterica> {
    
}
