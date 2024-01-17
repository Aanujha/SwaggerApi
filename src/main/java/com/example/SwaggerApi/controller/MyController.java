package com.example.SwaggerApi.controller;

import com.example.SwaggerApi.dto.CustomerDto;
import com.example.SwaggerApi.exception.ApiException;
import com.example.SwaggerApi.repo.CustmRepo;
import com.example.SwaggerApi.security.JwtAuthRequest;
import com.example.SwaggerApi.security.JwtAuthRespons;
import com.example.SwaggerApi.security.JwtTokenHelper;
import com.example.SwaggerApi.service.imp.CustomCustomerDetailsService;
import com.example.SwaggerApi.service.imp.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/controller")
public class MyController {

    @Autowired
    CustomerService customerService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomCustomerDetailsService customerDetailsService;

    @Autowired
    CustmRepo custmRepo;

    @Autowired
    JwtTokenHelper jwtTokenHelper;
    @Autowired
    AuthenticationManager authenticationManager;



    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody CustomerDto customerDto){
       CustomerDto registeredCustomer = this.customerService.registerNewCustomer(customerDto);
        return new ResponseEntity<CustomerDto>(registeredCustomer, HttpStatus.CREATED);
    }

    //login

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody JwtAuthRequest request) throws Exception, ApiException {

        this.authenticate(request.getGetCustomername(), request.getPassword());

        UserDetails userDetails =  this.userDetailsService.loadUserByUsername(request.getGetCustomername());

        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthRespons response = new JwtAuthRespons();
        response.setToken(token);
        response.setTokenType("Bearer ");
        CustomerDto customer = this.customerService.getCustomerByEmailPassword(request.getGetCustomername(),request.getPassword());
        if(customer != null) {
            response.setData(customer);
        }
        return new ResponseEntity<JwtAuthRespons>(response, HttpStatus.OK);

    }

    private void authenticate(String customername, String password) throws Exception, ApiException {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customername,
                password);
        try {
            this.authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            System.out.println("Invalid Details !!");
            throw new ApiException("Invalid username or password !!");
        }

    }


}
