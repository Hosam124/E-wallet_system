package model;

import java.util.ArrayList;
import java.util.List;

public class EWalletSystem {
    private final String name = "E-wallet";
    private  List<Account> accounts = new ArrayList<>();

    public EWalletSystem() {
        this.accounts.add(new Account("IAM","IAM123",24,"01018287319",true));
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
