package com.tech.bank.dao;

import com.tech.bank.data.entity.CustomersEntity;
import com.tech.bank.data.repository.CustomersRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomersDAO {

    private final CustomersRepository customersRepository;

    public CustomersDAO(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public CustomersEntity saveCustomer(CustomersEntity entity){
        return customersRepository.save(entity);
    }

    public List<CustomersEntity> findByCustomerNumber(String customerNumber){
        return customersRepository.findByCustomerNumberIgnoreCaseContaining(customerNumber);
    }

    public Optional<CustomersEntity> findByCustomerUuid(String customerUuid){
        return customersRepository.findByCustomerUuid(customerUuid);
    }
}
