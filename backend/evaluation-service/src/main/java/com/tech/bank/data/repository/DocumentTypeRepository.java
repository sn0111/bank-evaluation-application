package com.tech.bank.data.repository;

import com.tech.bank.data.entity.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity,Long> {
    Optional<DocumentTypeEntity> findByDocumentUuid(String documentUuid);
}
