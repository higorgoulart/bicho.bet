package com.bicho.bet.bicho.bet.resultado;

import com.bicho.bet.bicho.bet.core.CustomQuerydslPredicateExecutor;
import com.bicho.bet.bicho.bet.resultado.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long>, CustomQuerydslPredicateExecutor<Resultado> {
    
}
