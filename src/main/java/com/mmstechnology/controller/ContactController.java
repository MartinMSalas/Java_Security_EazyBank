package com.mmstechnology.controller;

import com.mmstechnology.model.ContactMessages;
import com.mmstechnology.repository.ContactMessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class ContactController {

    private final ContactMessagesRepository contactMessagesRepository;

    @Autowired
    public ContactController(ContactMessagesRepository contactMessagesRepository) {
        this.contactMessagesRepository = contactMessagesRepository;
    }

    @PostMapping("/contact")
    @PreFilter("filterObject.contactMessagesName != 'Test'")
    public List<ContactMessages> saveContactInquiryDetails(@RequestBody List<ContactMessages> contactMessages) {

        ContactMessages contactMessage = contactMessages.get(0);

        contactMessage.setContactMessagesId(getServiceReqNumber());

        contactMessagesRepository.save(contactMessage);
        List<ContactMessages> contactMessagesList = new ArrayList<>(List.of(contactMessage));

        return contactMessagesList;
    }

    private String getServiceReqNumber() {
        Random random = new Random();
        int randNumber = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + randNumber;
    }
}