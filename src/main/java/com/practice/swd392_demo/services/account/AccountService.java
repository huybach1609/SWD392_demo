package com.practice.swd392_demo.services.account;

import com.practice.swd392_demo.enums.AccountRole;
import com.practice.swd392_demo.exceptions.EmailDuplicationException;
import com.practice.swd392_demo.models.Account;
import com.practice.swd392_demo.repository.account.IAccountRepository;
import org.modelmapper.internal.bytebuddy.utility.RandomString;

public class AccountService implements IAccountService{
    public IAccountRepository _accountRepository;
    public AccountService(IAccountRepository accountRepository){

        _accountRepository = accountRepository;
    }

    @Override
    public Account addAccount(String email, String passWord, String activationKey, int userId) throws EmailDuplicationException {
        if(_accountRepository.searchByEmail(email) != null){
            throw new EmailDuplicationException();
        }
        Account createdAccount = new Account();
        createdAccount.setEmail(email);
        createdAccount.setPassword(passWord);
        createdAccount.setActivationKey(activationKey);
        createdAccount.setRoleId(AccountRole.USER.ordinal() + 1);
        createdAccount.setUserId(userId);
        return _accountRepository.add(createdAccount);
    }

    @Override
    public boolean removeAccount(Account account) {
        return _accountRepository.remove(account.getId());
    }
}
