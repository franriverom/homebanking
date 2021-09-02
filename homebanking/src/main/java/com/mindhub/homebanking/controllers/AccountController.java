package com.mindhub.homebanking.controllers;
import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;

import java.time.LocalDateTime;
import java.util.List;


import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @RequestMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(toList());
    }

    @GetMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id) {
        return this.accountRepository.findById(id).map(AccountDTO::new).orElse(null);
    };
    @PostMapping("/clients/current/accounts")
    public ResponseEntity<?> createAccount(Authentication authentication){
        Client client = this.clientRepository.findByEmail(authentication.getName());
        if (client.getAccounts().size() >= 3) {

            return new ResponseEntity<>("You can't create more accounts", HttpStatus.FORBIDDEN);

        }
        accountRepository.save(new Account("VIN"+getRandomNumber(1000, 9999), LocalDateTime.now(), 0.00, client));
        return new ResponseEntity<>("Account created", HttpStatus.CREATED);
    };
    ;
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @DeleteMapping("/clients/current/accounts")
    public ResponseEntity<?> deleteAccount(Authentication authentication, @RequestParam String number){

        Client client = clientRepository.findByEmail(authentication.getName());

        Account account = accountRepository.findByNumber(number);


        Transaction transactions = transactionRepository.findByAccount(account);
        if(transactions != null){
            transactionRepository.delete(transactions);
        }
        accountRepository.delete(account);

        return new ResponseEntity<>("Account and transactions successfully deleted", HttpStatus.ACCEPTED);
    }
};
