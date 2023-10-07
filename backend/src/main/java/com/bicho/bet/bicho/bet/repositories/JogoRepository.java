package com.bicho.bet.bicho.bet.repositories;

import com.bicho.bet.bicho.bet.models.jogo.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {
    
}
