package com.example.SwaggerApi.repo;

import com.example.SwaggerApi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustmRepo extends JpaRepository<Customer,Long> {

    Optional<Customer> findByEmail(String email);

    List<Customer> findByCustomername(String customername);

    //  List<Customer> findByUsername(String username);
}
