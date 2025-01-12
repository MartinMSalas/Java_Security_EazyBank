package com.eazybytes.config;

import com.eazybytes.model.Authority;
import com.eazybytes.model.Customer;
import com.eazybytes.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@Component
public class EazyBankUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EazyBankUsernamePasswordAuthenticationProvider(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Customer customer = customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));
        //Hibernate.initialize(customer.getAuthorities());
        if(passwordEncoder.matches(password, customer.getPwd())){
            return new UsernamePasswordAuthenticationToken(username, password, getGrantedAuthorities (customer.getAuthorities()) );
        } else {
            throw new BadCredentialsException("Invalid Password !");
        }
    }

//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        Customer customer = customerRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));
//        if(passwordEncoder.matches(password, customer.getPwd())){
//            return new UsernamePasswordAuthenticationToken(username, password, mapRolesToAuthorities(customer.getRole()) );
//        } else {
//            throw new BadCredentialsException("Invalid Password !");
//        }
//    }
//    private List<SimpleGrantedAuthority> mapRolesToAuthorities(String role) {
//        // Assuming roles are a comma-separated string, split and map to authorities
//        return Stream.of(role.split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }


    private List<GrantedAuthority> getGrantedAuthorities(List<Authority> authorities){
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
    }
}
