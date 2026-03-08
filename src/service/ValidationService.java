package service;

public interface ValidationService {

    boolean isUserNameValid(String userName);
    boolean isPasswordValid(String password);
    boolean isAgeValid(double age);
    boolean isPhoneNumberValid(String phoneNumber);
    boolean isAmountValid(double amount);
}
