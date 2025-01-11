package com.eazybytes.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "account_transaction")
public class AccountTransaction {

    @Id
    @Column(name = "transaction_id")
    private String transactionId;

    @NotNull
    @Column(name = "transaction_dt")
    private LocalDateTime transactionDt;

    @NotBlank
    @Column(name = "transaction_summary")
    private String transactionSummary;

    @NotBlank
    @Column(name = "transaction_type")
    private String transactionType;

    @NotNull
    @Column(name = "transaction_amt")
    private Integer transactionAmt;

    @NotNull
    @Column(name = "closing_balance")
    private Integer closingBalance;

    @CreatedDate
    @Column(updatable = false, name= "create_dt")
    private LocalDateTime createDt;
    @LastModifiedDate
    @Column(name= "update_dt")
    private LocalDateTime updateDt;

    @Version
    private Integer version;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_number", nullable = false)
    private Account account;
}