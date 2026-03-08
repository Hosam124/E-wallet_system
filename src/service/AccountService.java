package service;

import model.Account;

import java.util.Objects;

public interface AccountService {

    void createAccount(Account account);
    Account getAccountByUserNameAndPassword(String userName, String password);
    void increaseBalance(Account account, double amount);
    void deductBalance(Account account, double amount);
    void transferMoney(String senderUsername , String receiverUsername , double transferAmount);
    void updatePassword(Account account , String newPassword);
}
