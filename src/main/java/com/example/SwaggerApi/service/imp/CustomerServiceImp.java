package com.example.SwaggerApi.service.imp;

import com.example.SwaggerApi.dto.CustomerDto;
import com.example.SwaggerApi.model.Customer;
import com.example.SwaggerApi.repo.CustmRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService{

    @Autowired
    CustmRepo custmRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CustomerDto registerNewCustomer(CustomerDto customer) {
        Customer customer1  = toEntity(customer);

       customer1 .setPassword(this.passwordEncoder.encode(customer.getPassword()));
        Customer newCustomer = this.custmRepo.save(customer1);
        return toDto(newCustomer);
    }

    private Customer toEntity(CustomerDto dto) {
        return modelMapper.map(dto,Customer.class);
    }

    private CustomerDto toDto(Customer entity) {
        return modelMapper.map(entity,CustomerDto.class);
    }

    @Override
    public CustomerDto getCustomerByEmailPassword(String customername, String password) {
        Customer customer = custmRepo.findByEmail(customername).get();
        if(passwordEncoder.matches(password,customer.getPassword())) {
            return toDto(customer);
        }else{
            return null;
        }
    }

}
