package service.imp;

import model.Account;
import model.EWalletSystem;
import service.AdminService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                .collect(Collectors.toCollection(ArrayList::new));
        eWalletSystem.setAccounts(updatedAccounts);

        System.out.println("Account deleted successfully.");
    }

    @Override
    public void inActivateAccount(String userName) {
        if (!checkActivity(userName)){
            System.out.println("This account is already inactivated.");
            return;
        }
        changeActivity(userName,false);
        System.out.println("The account inactivated successfully.");

    }

    @Override
    public void activateAccount(String userName) {
        if (checkActivity(userName)){
            System.out.println("This account is already activated.");
            return;
        }
        changeActivity(userName,true);
        System.out.println("The account inactivated successfully.");
    }

    private boolean checkActivity(String userName){
        List<Account> accounts = eWalletSystem.getAccounts();

        Account account = accounts.stream()
                .filter(acc -> acc.getUserName().equals(userName))
                .findFirst()
                .orElse(null);

        assert account != null;
        return account.isActive();
    }

    private void changeActivity(String userName,boolean isActivity){
        eWalletSystem.getAccounts()
                .stream()
                .filter(acc -> acc.getUserName().equals(userName))
                .findFirst()
                .ifPresent(acc -> acc.setActive(isActivity));
    }

    static void printAccount(Account account) {
        System.out.println("--------------Account Info--------------");
        System.out.println("User Name:      " + account.getUserName());
        System.out.println("Password:       ******");
        System.out.println("Age:            " + account.getAge());
        System.out.println("Balance:        " + account.getBalance());
        System.out.println("Phone Number:   " + account.getPhoneNumber());
        System.out.println("Is Active       " + account.isActive());
        System.out.println("---------------------------------------");
    }
}
