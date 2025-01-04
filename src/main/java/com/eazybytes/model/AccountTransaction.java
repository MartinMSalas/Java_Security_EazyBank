package com.eazybytes.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountTransaction {

    @Id
    private String transactionId;

    @NotNull
    private LocalDateTime transactionDt;

    @NotBlank
    private String transactionSummary;

    @NotBlank
    private String transactionType;

    @NotNull
    private Integer transactionAmt;

    @NotNull
    private Integer closingBalance;

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

    @ManyToOne
    @JoinColumn(name = "account_number", nullable = false)
    private Account account;
}