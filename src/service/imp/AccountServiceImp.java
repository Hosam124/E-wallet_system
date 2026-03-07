package service.imp;

import model.Account;
import model.EWalletSystem;
import service.AccountService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AccountServiceImp implements AccountService {

    private EWalletSystem eWalletSystem;

    @Override
    public void createAccount(Account account) {
        Objects.requireNonNull(account,"You can not pass a null to createAccount method");

        List<Account> accounts = eWalletSystem.getAccounts();
        accounts.add(account);
        eWalletSystem.setAccounts(accounts);
        
    }

    void getAccounts(){
        for (Account account : eWalletSystem.getAccounts()){
            System.out.println("--------------Account Info--------------");
            System.out.println(account.getUserName());
            System.out.println(account.getPassword());
            System.out.println(account.getAge());
            System.out.println(account.getBalance());
            System.out.println(account.getPhoneNumber());
            System.out.println("---------------------------------------");
        }
    }
    
}
