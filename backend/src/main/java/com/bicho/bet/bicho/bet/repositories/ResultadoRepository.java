package com.bicho.bet.bicho.bet.repositories;

import com.bicho.bet.bicho.bet.models.resultado.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long>, CustomQuerydslPredicateExecutor<Resultado> {
    
}
