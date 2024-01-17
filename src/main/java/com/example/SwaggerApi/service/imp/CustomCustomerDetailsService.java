package com.example.SwaggerApi.service.imp;

import com.example.SwaggerApi.exception.ResourceNotFoundException;
import com.example.SwaggerApi.model.Customer;
import com.example.SwaggerApi.repo.CustmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomCustomerDetailsService implements UserDetailsService {

    @Autowired
    CustmRepo custmRepo;

    @Override
    public UserDetails loadUserByUsername(String customername) throws UsernameNotFoundException {
        Customer customer = this.custmRepo.findByEmail(customername)
                .orElseThrow(() -> new ResourceNotFoundException("Customer ", " email : " + customername, 0));
        return (UserDetails) customer;
    }

}
