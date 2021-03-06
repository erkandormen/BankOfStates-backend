package com.bank.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.model.Account;
import com.bank.model.Recipient;
import com.bank.model.Transaction;
import com.bank.model.User;
import com.bank.repository.AccountRepo;
import com.bank.repository.RecipientRepo;
import com.bank.request.TransactionRequest;
import com.bank.request.TransferRequest;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import com.bank.util.TransactionType;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	private RecipientRepo recipientRepo;

	@Autowired
	private TransactionService transactionService;

	@Override
	public Account createAccount() {
		Account account = new Account();
		account.setAccountBalance(new BigDecimal(0.0));
		account.setAccountNumber(getAccountNumber());
		accountRepo.save(account);
		return accountRepo.findByAccountNumber(account.getAccountNumber());
	}

	@Override
	public void deposit(TransactionRequest request, User user) {
		Account account = user.getAccount();
		Double amount = request.getAmount();
		account.setAccountBalance(
				account.getAccountBalance().add(new BigDecimal(amount)));
		accountRepo.save(account);
		Date date = new Date();
		Transaction transaction = new Transaction(date, request.getComment(),
				TransactionType.DEPOSIT.toString(), amount,
				account.getAccountBalance(), false, account);
		transactionService.saveTransaction(transaction);
	}

	@Override
	public void withdraw(TransactionRequest request, User user) {
		Account account = user.getAccount();
		Double amount = request.getAmount();
		account.setAccountBalance(
				account.getAccountBalance().subtract(new BigDecimal(amount)));
		accountRepo.save(account);
		Date date = new Date();
		Transaction transaction = new Transaction(date, request.getComment(),
				TransactionType.WITHDRAW.toString(), amount,
				account.getAccountBalance(), false, account);
		transactionService.saveTransaction(transaction);
	}

	@Override
	public void saveRecipient(Recipient recipient) {
		recipientRepo.save(recipient);
	}

	private Long getAccountNumber() {
		long smallest = 1000_0000_0000_0000L;
		long biggest = 9999_9999_9999_9999L;
		long random = ThreadLocalRandom.current().nextLong(smallest, biggest);
		return random;
	}

	@Override
	public void transfer(TransferRequest request, User user) {
		log.info("Transfer got triggered");
		Account account = user.getAccount();
		Double amount = request.getAmount();
		account.setAccountBalance(
				account.getAccountBalance().subtract(new BigDecimal(amount)));
		accountRepo.save(account);
		Date date = new Date();
		Transaction transaction = new Transaction(date,
				"Transferred to " + request.getRecipientName(),
				TransactionType.TRANSFER.toString(), amount,
				account.getAccountBalance(), true, account);
		transactionService.saveTransaction(transaction);
	}
}