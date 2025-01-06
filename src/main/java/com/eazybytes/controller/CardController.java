package com.eazybytes.controller;

import com.eazybytes.model.Card;
import com.eazybytes.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {

    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    @GetMapping("/myCards")
    public List<Card> getCardsDetails (@RequestParam int id) {
        List<Card> cards = cardRepository.findByCustomerCustomerId(id);

        return cards;
    }

}
