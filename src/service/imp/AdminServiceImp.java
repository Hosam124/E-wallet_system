package service.imp;

import model.Account;
import model.EWalletSystem;
import service.AdminService;

import java.util.List;

public class AdminServiceImp implements AdminService {

    private final EWalletSystem eWalletSystem;

    public AdminServiceImp(EWalletSystem eWalletSystem) {
        this.eWalletSystem = eWalletSystem;
    }

    @Override
    public void showAllAccounts() {
        eWalletSystem.getAccounts()
                .forEach(AdminServiceImp::printAccount);
    }

    @Override
    public void deleteAccount(String userName) {
        List<Account> accounts = eWalletSystem.getAccounts();

        List<Account> updatedAccounts = accounts.stream()
                .filter(acc -> !acc.getUserName().equals(userName))
                .toList();
        eWalletSystem.setAccounts(updatedAccounts);

        System.out.println("Account deleted successfully.");
    }

    static void printAccount(Account account) {
        System.out.println("--------------Account Info--------------");
        System.out.println("User Name:      " + account.getUserName());
        System.out.println("Password:       ******");
        System.out.println("Age:            " + account.getAge());
        System.out.println("Balance:        " + account.getBalance());
        System.out.println("Phone Number:   " + account.getPhoneNumber());
        System.out.println("---------------------------------------");
    }
}
