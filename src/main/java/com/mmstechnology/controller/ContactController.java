package com.mmstechnology.controller;

import com.mmstechnology.model.ContactMessages;
import com.mmstechnology.repository.ContactMessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ContactController {

    private final ContactMessagesRepository contactMessagesRepository;

    @Autowired
    public ContactController(ContactMessagesRepository contactMessagesRepository) {
        this.contactMessagesRepository = contactMessagesRepository;
    }

    @PostMapping("/contact")
    public ContactMessages saveContactInquiryDetails(@RequestBody ContactMessages contactMessages) {
        contactMessages.setContactId(getServiceReqNumber());
        return contactMessagesRepository.save(contactMessages);
    }

    private String getServiceReqNumber() {
        Random random = new Random();
        int randNumber = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + randNumber;
    }
}