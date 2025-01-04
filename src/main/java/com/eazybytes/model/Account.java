package com.eazybytes.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    @Column(nullable = false)
    private Long accountNumber;

    @NotBlank
    private String accountType;

    @NotBlank
    private String branchAddress;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDt;
    @LastModifiedDate
    private LocalDateTime updateDt;

    @Version
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountTransaction> accountTransactions = new ArrayList<>();
}