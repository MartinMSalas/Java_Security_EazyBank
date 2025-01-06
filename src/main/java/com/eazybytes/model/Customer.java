package com.eazybytes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "customer_id")
    private Integer customerId;

    @NotBlank
    private String name;

    @Email(message = "Invalid email format")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(name= "mobile_number")
    private String mobileNumber;

    @NotBlank
    private String pwd;

    @NotBlank
    private String role;

    @CreatedDate
    @Column(updatable = false, name= "create_dt")
    private LocalDateTime createDt;
    @LastModifiedDate
    @Column(name= "update_dt")
    private LocalDateTime updateDt;

    @Version
    private Integer version;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loan> loans = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountTransaction> accountTransactions = new ArrayList<>();
}
