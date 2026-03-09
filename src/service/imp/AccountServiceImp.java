package service.imp;

import model.Account;
import model.EWalletSystem;
import service.AccountService;

import java.time.LocalDate;
import java.time.LocalTime;
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

        addTransactionHistory(account, "signup");
        
    }

    @Override
    public Account getAccountByUserNameAndPassword(String userName, String password) {
        List<Account> accounts = eWalletSystem.getAccounts();
        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getUserName().equals(userName) && acc.getPassword().equals(password))
                .findAny();
        if (optionalAccount.isPresent()){
            addTransactionHistory(optionalAccount.get(),"login");
            return optionalAccount.get();
        }
        else {
            System.out.println("Wrong user name or password");
            return null;
        }
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
        addTransactionHistory(account, "Deposit " + amount);
    }

    @Override
    public void deductBalance(Account account, double amount) {
        List<Account> accounts = eWalletSystem.getAccounts();

        accounts.stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()))
                .findFirst()
                .ifPresent(acc -> {
                    acc.setBalance(acc.getBalance() - amount);
                    System.out.println("Withdrawal successful! New balance: " + acc.getBalance());
                });

        addTransactionHistory(account,"Withdraw " +amount);
    }

    @Override
    public void transferMoney(String senderUsername, String receiverUsername, double transferAmount) {
        List<Account> accounts = eWalletSystem.getAccounts();

        accounts.stream()
                .filter(acc -> acc.getUserName().equals(senderUsername))
                .findFirst()
                .ifPresent(sender -> accounts.stream()
                        .filter(acc -> acc.getUserName().equals(receiverUsername))
                        .findFirst()
                        .ifPresent(receiver -> {

                            sender.setBalance(sender.getBalance() - transferAmount);
                            receiver.setBalance(receiver.getBalance() + transferAmount);

                            System.out.println("Transfer successful!");
                            System.out.println("Your new balance: " + sender.getBalance());
                        }));

        Optional<Account> senderAccount = accounts.stream()
                                        .filter(acc -> acc.getUserName().equals(senderUsername))
                                                .findFirst();
        addTransactionHistory(senderAccount.get(),senderUsername + " transfer "+transferAmount+" to "+receiverUsername );
    }

    @Override
    public void updatePassword(Account account, String newPassword) {
        List<Account> accounts = eWalletSystem.getAccounts();

        accounts.stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()))
                .findFirst()
                .ifPresent(acc -> acc.setPassword(newPassword));

        System.out.println("Password updated successfully.");
    }

    @Override
    public void addTransactionHistory(Account account, String message) {
        String transaction = message + " on " + LocalDate.now() + " at " + LocalTime.now();
        List<Account> accounts = eWalletSystem.getAccounts();

        accounts.stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()))
                .findFirst()
                .ifPresent(acc -> acc.getTransactionHistory().add(transaction));
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
