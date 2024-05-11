package com.tech.bank.data.repository;

import com.tech.bank.data.entity.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomersRepository extends JpaRepository<CustomersEntity,Long> {

    List<CustomersEntity> findByCustomerNumberIgnoreCaseContaining(String customerNumber);

    Optional<CustomersEntity> findByCustomerUuid(String customerUuid);

}
