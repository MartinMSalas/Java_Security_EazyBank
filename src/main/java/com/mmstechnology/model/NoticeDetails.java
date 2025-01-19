package com.mmstechnology.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notice_details")
public class NoticeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "notice_id")
    private int noticeId;

    @Size(max = 200, message = "Notice Summary cannot exceed 200 characters")
    @Column(name="notice_summary")
    @NotNull
    private String noticeSummary;

    @Size(max = 500, message = "Notice details cannot exceed 500 characters")
    @Column(name="notice_details")
    @NotNull
    private String noticeDetails;

    @Column(name="notic_beg_dt")
    @NotNull
    private LocalDate noticBegDt;

    @Column(name="notic_end_dt")
    @NotNull
    private LocalDate  noticEndDt;

    @CreatedDate
    @Column(updatable = false, name= "create_dt")
    private LocalDateTime createDt;
    @LastModifiedDate
    @Column(name= "update_dt")
    private LocalDateTime updateDt;


    @Version
    private Integer version;

}
