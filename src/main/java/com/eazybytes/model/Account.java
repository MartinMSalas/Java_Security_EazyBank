package com.eazybytes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_number;
    @ManyToOne
    private int customerId;
    private String accountType;
    private String branchAddress;

    private LocalDate createDt;
}
