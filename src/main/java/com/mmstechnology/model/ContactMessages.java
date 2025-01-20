package com.mmstechnology.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ContactMessages {


    @Id
    @Column(name = "contact_id")
    private String contactMessagesId;

    @Column(name = "contact_name")
    @Size(max=50,message="name length must not exceed 50 char")
    private String contactMessagesName;

    @Column(name = "contact_email")
    @Email
    @Size(max=100,message="email length must not exceed 100 char")
    private String contactMessagesEmail;


    @Size(max=500,message="subject length must not exceed 50 char")
    private String subject;

    private String message;

    @CreatedDate
    @Column(updatable = false, name= "create_dt")
    private LocalDateTime createDt;
    @LastModifiedDate
    @Column(name= "update_dt")
    private LocalDateTime updateDt;

    @Version
    private Integer version;
}
