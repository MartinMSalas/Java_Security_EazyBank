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
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanNumber;

    @NotBlank
    private String loanType;

    @NotNull
    private LocalDateTime startDt;

    @NotNull
    private Integer totalLoan;

    @NotNull
    private Integer amountPaid;

    @NotNull
    private Integer outstandingAmount;

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
}
