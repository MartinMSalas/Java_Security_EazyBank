package com.eazybytes.controller;

import com.eazybytes.model.ContactMessages;
import com.eazybytes.repository.ContactMessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ContactMessages saveContactInquiryDetails (@RequestParam ContactMessages contactMessages) {
        contactMessages.setContactId(getServiceReqNumber());

        return contactMessagesRepository.save(contactMessages);
    }

    private String getServiceReqNumber(){
        Random random = new Random();
        int randNumber = random.nextInt(999999999-9999)+9999;
        return "SR" + randNumber;
    }

}
