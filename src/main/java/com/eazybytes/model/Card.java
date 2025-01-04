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
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @NotBlank
    private String cardNumber;

    @NotBlank
    private String cardType;

    @NotNull
    private Integer totalLimit;

    @NotNull
    private Integer amountUsed;

    @NotNull
    private Integer availableAmount;
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