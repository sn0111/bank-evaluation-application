package com.tech.bank.data.repository;

import com.tech.bank.data.entity.BorrowersDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowersDetailsRepository extends JpaRepository<BorrowersDetailsEntity,Long> {
}
