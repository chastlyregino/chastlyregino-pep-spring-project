package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account addAccount(Account account) {
        Optional<Account> optionalAccount = accountRepository.findByUsername(account.getUsername());
        // 
        if (!account.getUsername().isEmpty()
            && account.getPassword().length() >= 4
            && !optionalAccount.isPresent()) {
                return accountRepository.save(account);
        }
        
        return null;
    }

    public Account loginAccount(Account account) {
        Optional<Account> optionalAccount = accountRepository.findByUsername(account.getUsername());

        if (optionalAccount.isPresent()){
            Account accountRetrived = optionalAccount.get();

            if (accountRetrived.getPassword().equals(account.getPassword())
            && accountRetrived.getUsername().equals(account.getUsername())
            && accountRetrived.getAccountId() != null) {
                return accountRetrived;
            }
        }

        return null;
    }

    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}
