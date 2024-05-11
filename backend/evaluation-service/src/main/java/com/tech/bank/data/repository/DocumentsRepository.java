package com.tech.bank.data.repository;

import com.tech.bank.data.entity.DocumentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentsRepository extends JpaRepository<DocumentsEntity,Long> {
    Optional<DocumentsEntity> findByDocumentsUuid(String documentsUuid);
}
