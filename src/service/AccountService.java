package service;

import model.Account;

import java.util.Objects;

public interface AccountService {

    void createAccount(Account account);
    Account getAccountByUserNameAndPassword(String userName, String password);
}
