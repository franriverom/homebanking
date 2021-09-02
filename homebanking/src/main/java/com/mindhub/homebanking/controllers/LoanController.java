package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.LoanApplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ClientLoanRepository clientLoanRepository;

    @RequestMapping("/loans")
    public List<LoanDTO> getLoans() {
        return loanRepository.findAll().stream().map(LoanDTO::new).collect(toList()); }


    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> loans (@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication){
        Loan loan = this.loanRepository.findByName(loanApplicationDTO.getName());
        Account account = this.accountRepository.findByNumber(loanApplicationDTO.getToAccountNumber());
        Client client = this.clientRepository.findByEmail(authentication.getName());

        if (loanApplicationDTO.getToAccountNumber().isEmpty() || loanApplicationDTO.getName().isEmpty() || loanApplicationDTO.getAmount() == 0 || loanApplicationDTO.getPayments() == 0){
            return new ResponseEntity<>("Missing Data", HttpStatus.FORBIDDEN);
        }
        if (loan == null){
            return new ResponseEntity<>("Loan doesn't exist", HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getAmount() > loan.getMaxAmount()){
            return new ResponseEntity<>("The amount exceeds the limit to apply", HttpStatus.FORBIDDEN);
        }
        if (!loan.getPayments().contains(loanApplicationDTO.getPayments())){
            return new ResponseEntity<>("Number of payments invalid", HttpStatus.FORBIDDEN);
        }
        if (account == null){
            return new ResponseEntity<>("Account doesn't exist", HttpStatus.FORBIDDEN);
        }
        if (!client.getAccounts().contains(account)){
            return new ResponseEntity<>("The account doesn't belong to this client", HttpStatus.FORBIDDEN);
        }
        clientLoanRepository.save(new ClientLoan(loanApplicationDTO.getAmount(), loanApplicationDTO.getPayments(), client,loan, account.getNumber()));
        transactionRepository.save(new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), loan.getName() + " " + "loan approved", LocalDateTime.now(),account));
        account.setBalance(account.getBalance() + loanApplicationDTO.getAmount());
        accountRepository.save(account);
        return new ResponseEntity<>("Account balance updated", HttpStatus.CREATED);
    }
}
