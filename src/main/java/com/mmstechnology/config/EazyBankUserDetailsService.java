package com.mmstechnology.config;

import com.mmstechnology.model.Customer;
import com.mmstechnology.repository.CustomerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//@Service
public class EazyBankUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public EazyBankUserDetailsService(CustomerRepository customerRepository) {
         this.customerRepository = customerRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));

        return new User(customer.getEmail(), customer.getPwd(), mapRolesToAuthorities(customer.getRole()));

    }

    private List<SimpleGrantedAuthority> mapRolesToAuthorities(String role) {
        // Assuming roles are a comma-separated string, split and map to authorities
        return Stream.of(role.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
