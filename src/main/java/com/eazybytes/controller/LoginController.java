package com.eazybytes.controller;

import com.eazybytes.model.Customer;
import com.eazybytes.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Validated Customer customer){
        Customer savedCustomer = null;
        ResponseEntity response = null;
        try {
            String hashPassword = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPassword);

            savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getCustomerId()>0){
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        }
        catch (Exception ex){
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());

        }
        return response;

    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication){
        Customer customer = customerRepository.findByEmail(authentication.getName())
                .orElseThrow(()->new UsernameNotFoundException("The user with email: "+authentication.getName()+" was not found"));

        return customer;

    }
}
