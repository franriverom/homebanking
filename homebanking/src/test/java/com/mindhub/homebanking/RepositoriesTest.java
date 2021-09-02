package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;


    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

    public class RepositoriesTest {

        @Autowired
        LoanRepository loanRepository;

        @Autowired
        ClientRepository clientRepository;

        @Autowired
        AccountRepository accountRepository;

        @Autowired
        CardRepository cardRepository;

        @Autowired
        TransactionRepository transactionRepository;

        @Test
        public void validateClientAttributes(){
            List<Client> clients = clientRepository.findAll();
            assertThat(clients, hasItem(hasProperty("id", is(not(empty())))));
            assertThat(clients, hasItem(hasProperty("firstName", is(not(empty())))));
            assertThat(clients, hasItem(hasProperty("lastName", is(not(empty())))));
            assertThat(clients, hasItem(hasProperty("email", is(not(empty())))));
            assertThat(clients, hasItem(hasProperty("password", is(not(empty())))));
            assertThat(clients, hasItem(hasProperty("accounts", is(not(empty())))));
            assertThat(clients, hasItem(hasProperty("cards", is(not(empty())))));
        }

        @Test
        public void validateAccountAttributes(){
            List<Account> accounts = accountRepository.findAll();
            assertThat(accounts, hasItem(hasProperty("id", is(not(empty())))));
            assertThat(accounts, hasItem(hasProperty("number", is(not(empty())))));
            assertThat(accounts, hasItem(hasProperty("creationDate", is(not(empty())))));
            assertThat(accounts, hasItem(hasProperty("balance", is(not(empty())))));
            assertThat(accounts, hasItem(hasProperty("transactions", is(not(empty())))));
        }

        @Test
        public void validateCardAttributes(){
            List<Card> cards = cardRepository.findAll();
            assertThat(cards, hasItem(hasProperty("id", is(not(empty())))));
            assertThat(cards, hasItem(hasProperty("cardHolder", is(not(empty())))));
            assertThat(cards, hasItem(hasProperty("number", is(not(empty())))));
            assertThat(cards, hasItem(hasProperty("cvv", is(not(empty())))));
            assertThat(cards, hasItem(hasProperty("thruDate", is(not(empty())))));
        }

        @Test
        public void existLoans(){
            List<Loan> loans = loanRepository.findAll();
            assertThat(loans,is(not(empty())));

        }

        @Test
        public void validateNameOfLoans(){
                List<Loan> loans = loanRepository.findAll();
                assertThat(loans, hasItem(hasProperty("name", is("Mortgage"))));
                assertThat(loans, hasItem(hasProperty("name", is("Personal"))));
                assertThat(loans, hasItem(hasProperty("name", is("Automotive"))));
            }

        @Test
        public void existTransaction(){
            List<Transaction> transactions = transactionRepository.findAll();
            assertThat(transactions,is(not(empty())));
        }

        }





