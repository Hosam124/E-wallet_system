package service;

import model.Account;

public interface AdminService {

    void showAllAccounts();
    void deleteAccount(String userName);
    void inActivateAccount(String userName);
    void activateAccount(String userName);

}
