package service;

import model.Account;

public interface ValidationService {

    boolean isUserNameValid(String userName);
    boolean isPasswordValid(String password);
    boolean isAgeValid(double age);
    boolean isPhoneNumberValid(String phoneNumber);
    boolean isAmountValid(double amount);
    boolean isAccountExit(String userName);
    boolean isBalanceEnough(double amount, Account account);
    boolean isCorrectPassword(Account account , String password);

}
