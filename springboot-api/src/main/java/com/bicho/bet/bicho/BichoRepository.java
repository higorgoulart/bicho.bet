package com.bicho.bet.bicho;

import com.bicho.bet.core.CustomQuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BichoRepository extends JpaRepository<Bicho, Long>, CustomQuerydslPredicateExecutor<Bicho> {

}
