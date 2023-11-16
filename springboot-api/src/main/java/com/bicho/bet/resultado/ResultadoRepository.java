package com.bicho.bet.resultado;

import com.bicho.bet.core.CustomQuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long>, CustomQuerydslPredicateExecutor<Resultado> {
    
}
