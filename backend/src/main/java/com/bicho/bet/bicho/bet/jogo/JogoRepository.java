package com.bicho.bet.bicho.bet.jogo;

import com.bicho.bet.bicho.bet.core.CustomQuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long>, CustomQuerydslPredicateExecutor<Jogo> {
    
}
