package com.example.SwaggerApi.service.imp;

import com.example.SwaggerApi.dto.CustomerDto;
import com.example.SwaggerApi.model.Customer;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustomerService {

    CustomerDto registerNewCustomer(CustomerDto customer);


   CustomerDto getCustomerByEmailPassword(String customername, String password);

  //  CustomerDto loadUserByCustomername(String getCustomername);

    // CustomerDto getUserByEmailPassword(String username, String password);
}
