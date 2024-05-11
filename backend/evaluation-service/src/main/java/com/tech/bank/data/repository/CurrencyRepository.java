package com.tech.bank.data.repository;

import com.tech.bank.data.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity,Long> {
    Optional<CurrencyEntity> findByCurrencyUuid(String currencyUuid);
}
