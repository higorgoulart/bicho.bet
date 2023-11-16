package com.bicho.bet.apostador;

import com.bicho.bet.core.CustomQuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostadorRepository extends JpaRepository<Apostador, Long>, CustomQuerydslPredicateExecutor<Apostador> {
    
}
