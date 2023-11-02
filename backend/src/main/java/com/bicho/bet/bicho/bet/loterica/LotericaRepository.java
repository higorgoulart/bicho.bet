package com.bicho.bet.bicho.bet.loterica;

import com.bicho.bet.bicho.bet.core.CustomQuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotericaRepository extends JpaRepository<Loterica, Long>, CustomQuerydslPredicateExecutor<Loterica> {
    
}
