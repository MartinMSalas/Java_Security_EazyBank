package com.eazybytes.controller;

import com.eazybytes.model.NoticeDetails;
import com.eazybytes.repository.NoticeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class NoticesController {

    private final NoticeDetailsRepository noticeDetailsRepository;

    @Autowired
    public NoticesController(NoticeDetailsRepository noticeDetailsRepository) {
        this.noticeDetailsRepository = noticeDetailsRepository;
    }


    @GetMapping("/notices")
    public ResponseEntity<List<NoticeDetails>> getNotices () {
        List<NoticeDetails> notices = noticeDetailsRepository.findAllActiveNotices();


        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .body(notices);
    }

}  
