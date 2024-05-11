package com.tech.bank.servie;

import com.tech.bank.dao.CustomersDAO;
import com.tech.bank.data.dto.CustomersDTO;
import com.tech.bank.data.entity.CustomersEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomersDAO customersDAO;

    private final ModelMapper modelMapper;

    public CustomerService(CustomersDAO customersDAO, ModelMapper modelMapper) {
        this.customersDAO = customersDAO;
        this.modelMapper = modelMapper;
    }


    public CustomersDTO saveCustomer(CustomersDTO dto){
        CustomersEntity entity = modelMapper.map(dto, CustomersEntity.class);
        CustomersEntity propertyEvaluationEntity = customersDAO.saveCustomer(entity);
        return modelMapper.map(propertyEvaluationEntity, CustomersDTO.class);
    }

    public List<CustomersDTO> findCustomers(String customerNumber){
        return customersDAO.findByCustomerNumber(customerNumber)
                .stream().map(e->modelMapper.map(e,CustomersDTO.class))
                .toList();
    }

}
