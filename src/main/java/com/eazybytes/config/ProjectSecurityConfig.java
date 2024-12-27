package com.eazybytes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        /*http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());*/
        /*http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());*/
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/notices", "/contact", "/error").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("1234")
                .authorities("admin")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("1234")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin,user);

    }
    */

    /*@Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails admin = User.withUsername("admin")
                .password("1234")
                .authorities("admin")
                .build();

        UserDetails user = User.withUsername("user")
                .password("1234")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin,user);

    }
    */

/*
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
    */


    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
