package com.tech.bank.data.repository;

import com.tech.bank.data.entity.PropertyEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyEvaluationRepository extends JpaRepository<PropertyEvaluationEntity,Long> {
}
