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
        
    }

    @Override
    public Account getAccountByUserNameAndPassword(String userName, String password) {
        List<Account> accounts = eWalletSystem.getAccounts();
        Optional<Account> optionalAccount = accounts.stream()
                .filter(acc -> acc.getUserName().equals(userName) && acc.getPassword().equals(password))
                .findAny();
        return optionalAccount.orElse(null);
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
