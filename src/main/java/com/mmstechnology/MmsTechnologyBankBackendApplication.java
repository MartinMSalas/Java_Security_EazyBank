package com.mmstechnology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
//@EnableWebSecurity(debug = true)
@EnableMethodSecurity(prePostEnabled = true)
public class MmsTechnologyBankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MmsTechnologyBankBackendApplication.class, args);
    }

}

