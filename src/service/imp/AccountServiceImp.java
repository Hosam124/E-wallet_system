package service.imp;

import model.Account;
import model.EWalletSystem;
import service.AccountService;

import javax.sql.rowset.serial.SerialStruct;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AccountServiceImp implements AccountService {

    private final EWalletSystem eWalletSystem;

    public AccountServiceImp(EWalletSystem eWalletSystem) {
        this.eWalletSystem = eWalletSystem;
    }

    @Override
    public void createAccount(Account account) {
        Objects.requireNonNull(account,"You can not pass a null to createAccount method");

        List<Account> accounts = eWalletSystem.getAccounts();
        accounts.add(account);
        eWalletSystem.setAccounts(accounts);
        System.out.println("Account created successfully.....:)");
        
    }

    @Override
    public Account getAccountByUserNameAndPassword(String userName, String password) {
        List<Account> accounts = eWalletSystem.getAccounts();
        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getUserName().equals(userName) && acc.getPassword().equals(password))
                .findAny();
        if (optionalAccount.isPresent()) return optionalAccount.get();
        else {
            System.out.println("Wrong user name or password");
            return null;
        }
    }

    @Override
    public boolean isAccountExit(String userName) {
        List<Account> accounts = eWalletSystem.getAccounts();
        return accounts.stream()
                .anyMatch(acc -> acc.getUserName().equals(userName));

    }

    @Override
    public void increaseBalance(Account account, double amount) {
        List<Account> accounts = eWalletSystem.getAccounts();

        accounts.stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()))
                .findFirst()
                .ifPresent(acc -> {
                    acc.setBalance(acc.getBalance() + amount);
                    System.out.println("You deposited successfully! New balance: " + acc.getBalance());
                });
    }


    public void getAccounts(){
        for (Account account : eWalletSystem.getAccounts()){
            System.out.println("--------------Account Info--------------");
            System.out.println("User Name:      "+account.getUserName());
            System.out.println("Password:       "+account.getPassword());
            System.out.println("Age:            "+account.getAge());
            System.out.println("Balance:        "+account.getBalance());
            System.out.println("Phone Number:   "+account.getPhoneNumber());
            System.out.println("---------------------------------------");
        }
    }
    
}
