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
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "card_id")
    private Integer cardId;

    @NotBlank
    @Column(name= "card_number")
    private String cardNumber;

    @NotBlank
    @Column(name= "card_type")
    private String cardType;

    @NotNull
    @Column(name= "total_limit")
    private Integer totalLimit;

    @NotNull
    @Column(name= "amount_used")
    private Integer amountUsed;

    @NotNull
    @Column(name= "available_amount")
    private Integer availableAmount;

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