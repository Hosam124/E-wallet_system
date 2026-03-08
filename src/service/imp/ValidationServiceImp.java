package service.imp;

import model.Account;
import model.EWalletSystem;
import service.ValidationService;

import java.util.List;
import java.util.Optional;

public class ValidationServiceImp implements ValidationService {

    private final EWalletSystem eWalletSystem ;

    public ValidationServiceImp(EWalletSystem eWalletSystem) {
        this.eWalletSystem = eWalletSystem;
    }

    @Override
    public boolean isUserNameValid(String userName) {
        if (userName.length()<3){
            System.out.println("The user name should contain at least 3 characters.");
            return false;
        }
        if (Character.isLowerCase(userName.charAt(0))){
            System.out.println("The first character in user name should be capital.");
            return false;
        }
        if (isUserNameExist(userName)){
            System.out.println("This user name is already used try another one.");
            return false;
        }

        return true;

    }

    @Override
    public boolean isPasswordValid(String password) {
        if (password.length()<8){
            System.out.println("The password should contain at least 8 digits");
            return false;
        }
        if (!isComplexPassword(password)){
            System.out.println("The password should contain numbers uppercase characters and lowercase characters and special characters.");
            return false;
        }
        return true;
    }

    @Override
    public boolean isAgeValid(double age) {
        if(age<18){
            System.out.println("The age should be 18 or more.");
            return false;
        }

        return true;
    }

    @Override
    public boolean isPhoneNumberValid(String phoneNumber) {
        if (!isEgyptPhone(phoneNumber)){
            System.out.println("The phone number should contain only from digits and on Egyptian format.");
            return false;
        }
        if (isPhoneNumberExist(phoneNumber)){
            System.out.println("This phone number has been used before.");
            return false;
        }

        return true;
    }
    private boolean isPhoneNumberExist(String phoneNumber){
        List<Account> accounts = eWalletSystem.getAccounts();
        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getPhoneNumber().equals(phoneNumber))
                .findAny();
        return optionalAccount.isPresent();
    }

    private boolean isUserNameExist(String userName){
        List<Account> accounts = eWalletSystem.getAccounts();
        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getUserName().equals(userName))
                .findAny();
        return optionalAccount.isPresent();
    }
    private boolean isComplexPassword(String password) {

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }
            else if (Character.isLowerCase(c)) {
                hasLower = true;
            }
            else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            else {
                hasSpecial = true;
            }
        }

        return hasUpper && hasLower && hasDigit && hasSpecial;
    }

    private boolean isEgyptPhone(String phone) {
        return phone.matches("^01[0125][0-9]{8}$");
    }
}
