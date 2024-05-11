package com.tech.bank.data.repository;

import com.tech.bank.data.entity.PropertyValuationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyValuationRepository extends JpaRepository<PropertyValuationEntity,Long> {
}
