package com.mmstechnology.model;
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
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "loan_number")
    private Integer loanNumber;

    @NotBlank
    @Column(name= "loan_type")
    private String loanType;

    @NotNull
    @Column(name= "start_dt")
    private LocalDateTime startDt;

    @NotNull
    @Column(name= "total_loan")
    private Integer totalLoan;

    @NotNull
    @Column(name= "amount_paid")
    private Integer amountPaid;

    @NotNull
    @Column(name= "outstanding_amount")
    private Integer outstandingAmount;

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
}
