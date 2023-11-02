package com.bicho.bet.bicho.bet.apostador;

import com.bicho.bet.bicho.bet.core.CustomQuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostadorRepository extends JpaRepository<Apostador, Long>, CustomQuerydslPredicateExecutor<Apostador> {
    
}
