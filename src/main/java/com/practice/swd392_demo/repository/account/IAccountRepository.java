package com.practice.swd392_demo.repository.account;

import com.practice.swd392_demo.models.Account;
import com.practice.swd392_demo.repository.IRepository;

public interface IAccountRepository extends IRepository<Account, Integer> {
    Account searchByEmail(String email);
}
