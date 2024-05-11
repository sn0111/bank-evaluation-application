package com.tech.bank.data.repository;

import com.tech.bank.data.entity.ReferenceNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReferenceNumberRepository extends JpaRepository<ReferenceNumberEntity,Long> {
    Optional<ReferenceNumberEntity> findByReferenceName(String referenceName);
}
