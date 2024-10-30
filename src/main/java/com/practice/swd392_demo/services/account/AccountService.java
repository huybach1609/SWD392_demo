package com.practice.swd392_demo.services.account;

import com.practice.swd392_demo.models.Account;
import com.practice.swd392_demo.repository.account.IAccountRepository;

public class AccountService implements IAccountService{
    public IAccountRepository _accountRepository;
    public AccountService(IAccountRepository accountRepository){
        _accountRepository = accountRepository;
    }

    @Override
    public Account addAccount(String email, String passWord, String activationKey) {
        return null;
    }
}
