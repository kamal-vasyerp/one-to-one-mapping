package com.practise.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practise.mapping.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
