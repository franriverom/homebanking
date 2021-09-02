package com.mindhub.homebanking;
import com.mindhub.homebanking.controllers.ClientController;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mapping.model.Property;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
		System.out.println("Bienvenido a HomeBanking");

  }

	@Bean
	public CommandLineRunner initData(ClientRepository repository, AccountRepository repositoryAccount, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {
		return (args) -> {
			/*Client client1 = repository.save(new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("melba123")));
			Client client2 = repository.save(new Client("Charly", "Garcia", "sui-generis@gmail.com", passwordEncoder.encode("sui123")));

			Account account1 = repositoryAccount.save(new Account("VIN001",LocalDateTime.now(), 5000.00, client1));
			Account account2 = repositoryAccount.save(new Account("VIN002", LocalDateTime.now().plusDays(1), 7500.00, client1));
			Account account3 = repositoryAccount.save(new Account("VIN003", LocalDateTime.now().plusDays(3), 20000.00, client2));

			Transaction transaction1 = transactionRepository.save(new Transaction(TransactionType.CREDIT, 15000, "Bonus", LocalDateTime.now(), account1));
			Transaction transaction2 = transactionRepository.save(new Transaction(TransactionType.DEBIT, -1500, "Gas Bill", LocalDateTime.now(), account3));
			Transaction transaction3 = transactionRepository.save(new Transaction(TransactionType.DEBIT, -2550, "Walmart shop", LocalDateTime.now(), account1));


			Loan loan1 = loanRepository.save(new Loan("Mortgage", 500000.00, 20.00, List.of(12, 24, 36, 48, 60)));
			Loan loan2 = loanRepository.save(new Loan("Personal", 100000.00, 20.00, List.of(6, 12, 24)));
			Loan loan3 = loanRepository.save(new Loan("Automotive", 300000.00, 20.00, List.of(6, 12, 24, 36)));

			ClientLoan clientLoan1 = clientLoanRepository.save(new ClientLoan(400000.00, 60, client1, loan1, "VIN001"));
			ClientLoan clientLoan2 = clientLoanRepository.save(new ClientLoan(50000.00, 12, client1, loan2, "VIN001"));
			ClientLoan clientLoan3 = clientLoanRepository.save(new ClientLoan(100000.00, 24, client2, loan2, "VIN003"));
			ClientLoan clientLoan4 = clientLoanRepository.save(new ClientLoan(200000.00, 36, client2, loan3, "VIN003"));

			Card card1 = cardRepository.save(new Card(client1.getFirstName()+" "+client1.getLastName(), "4517-6601-6300-7580", 477, CardType.DEBIT, CardColor.GOLD, LocalDateTime.now(), LocalDateTime.now().plusYears(5), client1 ));
			Card card2 = cardRepository.save(new Card(client1.getFirstName()+" "+client1.getLastName(), "4509-7901-9042-6892", 801, CardType.CREDIT, CardColor.TITANIUM, LocalDateTime.now(), LocalDateTime.now().plusYears(5), client1));
			Card card3 = cardRepository.save(new Card(client2.getFirstName()+" "+client2.getLastName(), "4509-7501-7182-9382", 433, CardType.CREDIT, CardColor.SILVER, LocalDateTime.now(), LocalDateTime.now().plusYears(5), client2));*/
		};
	}
}


