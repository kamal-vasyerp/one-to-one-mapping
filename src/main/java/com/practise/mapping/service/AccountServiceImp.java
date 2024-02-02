package com.practise.mapping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practise.mapping.model.Account;
import com.practise.mapping.repository.AccountRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AccountServiceImp implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<Account> showAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public Account showAccount(long accountId) {
		Account account = new Account();
		account = accountRepository.findById(accountId).orElseThrow(()-> new EntityNotFoundException("Account Does not Exist"));
		return account;
	}

	@Override
	public String saveAccount(Account account) {
		accountRepository.save(account);
		return "Account Saved";
	}

	@Override
	public String deleteAccount(long accountId) {
		Account account = new Account();
		accountRepository.delete(account);
		return "Account Deleted";
	}
	
	
	
}
