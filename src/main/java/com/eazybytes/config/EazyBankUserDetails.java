package com.eazybytes.config;

import com.eazybytes.model.Customer;
import com.eazybytes.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EazyBankUserDetails implements UserDetailsService {


    private final CustomerRepository customerRepository;

    @Autowired
    public EazyBankUserDetails(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customerOptional = customerRepository.findByEmail(username);

        if(customerOptional.isEmpty()){
            throw new UsernameNotFoundException("User details not found for the user: "+username);
        }
        Customer customer = customerOptional.get();
        String userName = customer.getEmail();
        String password = customer.getPwd();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(customer.getRole()));
        return new User(userName, password, authorities);
    }
}
