package service;

import model.Account;

public interface AppService {
    void startProgram();
    void signup();
    void login();
    void showUserMainMenu(Account account);
}
