package com.example.SwaggerApi.controller;

import ch.qos.logback.core.model.Model;
import com.example.SwaggerApi.dto.CustomerDto;
import com.example.SwaggerApi.model.Customer;
import com.example.SwaggerApi.repo.CustmRepo;
import com.example.SwaggerApi.service.imp.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    CustmRepo custmRepo;


    @PostMapping("/list")
    public ResponseEntity<?>userlist() {
        Map<String, Object> map = new HashMap<>();
        map.put("message","this is api");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

//    @GetMapping("/details")
//    public ResponseEntity<?> getUserDetails(@AuthenticationPrincipal  Model model, Principal principal) {
//
//        Customer customer = (Customer) this.custmRepo.findByCustomername(princialToCusomername(principal.getName()));
//        List<Customer> customers = this.custmRepo.findByCustomername(String.valueOf(customer));
//        return ResponseEntity.ok(customer);
//
//    }

//    private String princialToCusomername(String principalName) {
//        List<Customer> customers = custmRepo.findByCustomername(principalName);
//        if (customers.isEmpty()) {
//            return null;
//        }
//
//        return customers.get(0).getCustomerName();
//    }

    @GetMapping("/details")
    public ResponseEntity<?> getUserDetails(Principal principal) {
       Optional<Customer> customerList = this.custmRepo.findByEmail(principal.getName());
        return new ResponseEntity<>(customerList,HttpStatus.ACCEPTED);
    }



}
