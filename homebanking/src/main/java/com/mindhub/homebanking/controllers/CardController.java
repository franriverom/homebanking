package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.utils.CardUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CardRepository cardRepository;

    @RequestMapping("/clients/current/cards")
    public List<CardDTO> getCards(Authentication authentication) {
        Client client = this.clientRepository.findByEmail(authentication.getName());
        return client.getCards().stream().map(CardDTO::new).collect(toList());
    }


    @PostMapping("/clients/current/cards")
    public ResponseEntity<?> createCard(@RequestParam CardType cardType, @RequestParam CardColor cardColor, Authentication authentication) {
        Client client = this.clientRepository.findByEmail(authentication.getName());
        if (client.getCards().stream().filter(card -> card.getCardType() == cardType).count() >=3) {

            return new ResponseEntity<>("You can't have more cards", HttpStatus.FORBIDDEN);

        }

        String cardNumber = CardUtils.getCardNumber();

        int cvv = CardUtils.getCVV();

        cardRepository.save(new Card(client.getFirstName()+" "+client.getLastName(), cardNumber, cvv, cardType, cardColor, LocalDateTime.now(), LocalDateTime.now().plusYears(5), client));

        return new ResponseEntity<>("Card created",HttpStatus.CREATED);
    }

    @DeleteMapping("/clients/current/cards/{id}")
    public ResponseEntity<?> deleteCard(Authentication authentication, @PathVariable long id) {

        Client client = clientRepository.findByEmail(authentication.getName());
        if (cardRepository.findById(id) == null) {
            return new ResponseEntity<>("This cards doesn't exist", HttpStatus.FORBIDDEN);
        }

        if (!client.getCards().contains(cardRepository.findById(id))) {
            return new ResponseEntity<>("Couldn't find the card in the client's cards", HttpStatus.FORBIDDEN);
        }

        cardRepository.delete(cardRepository.findById(id));

        return new ResponseEntity<>("Card successfully deleted", HttpStatus.ACCEPTED);
    }
};
