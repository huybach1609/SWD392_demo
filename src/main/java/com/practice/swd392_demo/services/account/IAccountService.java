package com.practice.swd392_demo.services.account;

import com.practice.swd392_demo.models.Account;

public interface IAccountService {
    Account addAccount(String email, String passWord, String activationKey);
}
