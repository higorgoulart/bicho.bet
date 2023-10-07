package com.bicho.bet.bicho.bet.repositories;

import com.bicho.bet.bicho.bet.models.conta.Apostador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostadorRepository extends JpaRepository<Apostador, Long> {
    
}
