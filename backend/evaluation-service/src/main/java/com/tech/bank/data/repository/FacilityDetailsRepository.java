package com.tech.bank.data.repository;

import com.tech.bank.data.entity.FacilityDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityDetailsRepository extends JpaRepository<FacilityDetailsEntity,Long> {
//    Optional<CurrencyEntity> findByCurrencyUuid(String currencyUuid);
}
