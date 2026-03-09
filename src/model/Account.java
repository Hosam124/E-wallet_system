package model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String userName;
    private String password;
    private double age;
    private double balance;
    private String phoneNumber;
    private List<String> transactionHistory;
    private boolean isAdmin;
    private boolean isActive;

    public Account() {
    }

    public Account(String userName, String password, double age, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
        this.isAdmin = false;
        this.isActive = true;
    }

    public Account(String userName, String password, double age, String phoneNumber, boolean isAdmin) {
        this(userName,password,age,phoneNumber);
        this.isAdmin = isAdmin;
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<String> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
