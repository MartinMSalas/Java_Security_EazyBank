package com.eazybytes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorityId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @CreatedDate
    @Column(updatable = false, name= "create_dt")
    private LocalDateTime createDt;
    @LastModifiedDate
    @Column(name= "update_dt")
    private LocalDateTime updateDt;

    @Version
    private Integer version;
}
