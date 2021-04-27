package com.sofisticat.management.services;

import com.sofisticat.management.dao.AccountRepository;
import com.sofisticat.management.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account saveAccount(Account account) {
        account.setEnabled(true);
        account.setRole("USER");
        return accountRepository.save(account);
    }

}
