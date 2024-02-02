package com.practise.mapping.service;

import java.util.List;

import com.practise.mapping.model.Account;

public interface AccountService {

	public List<Account> showAccounts();
	public Account showAccount(long accountId);
	public String saveAccount(Account account);
	public String deleteAccount(long accountId);
	
}
