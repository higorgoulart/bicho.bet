package com.bicho.bet.apostador;

import com.bicho.bet.core.CustomQuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApostadorRepository extends JpaRepository<Apostador, Long>, CustomQuerydslPredicateExecutor<Apostador> {
    Optional<Apostador> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
