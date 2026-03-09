package service.imp;

import model.Account;
import model.EWalletSystem;
import service.AppService;

import java.util.Objects;
import java.util.Scanner;

public class AppServiceImp implements AppService {

    private final AccountServiceImp accountServiceImp ;
    private final ValidationServiceImp validationServiceImp ;
    private final AdminServiceImp adminServiceImp;
    private final Scanner scanner = new Scanner(System.in);

    public AppServiceImp(EWalletSystem eWalletSystem) {
        validationServiceImp = new ValidationServiceImp(eWalletSystem);
        accountServiceImp = new AccountServiceImp(eWalletSystem);
        adminServiceImp = new AdminServiceImp(eWalletSystem);
    }

    @Override
    public void startProgram() {
        System.out.println("-------------------Welcome To E-wallet-------------------");

        int numberOfAttempts = 0;
        while (true){
            System.out.println("1) Login\n2) Signup\n3) Exit");
            System.out.println("pls enter your choice............");
            int choice = Integer.parseInt(scanner.nextLine());
            boolean isExit = false;
            switch (choice){
                case 1:
                    login();
                    break;
                case 2:
                    signup();
                    break;
                case 3:
                    System.out.println("Have a nice day:)...");
                    isExit= true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    numberOfAttempts++;
            }
            if (isExit) break;

            if (numberOfAttempts>4){
                throw new IllegalArgumentException("Many times of invalid choose pls contact with admin :(.......");
            }
        }

    }


    private void signup() {
        String userName;
        do {
            System.out.println("Enter your name : ");
            userName = scanner.nextLine().trim();
        }while (!validationServiceImp.isUserNameValid(userName));

        String password;
        do {
            System.out.println("Enter your password:");
            password = scanner.nextLine().trim();
        }while (!validationServiceImp.isPasswordValid(password));

        double age;
        do {
            System.out.println("Enter your age:");
            age = Double.parseDouble(scanner.nextLine());
        }while (!validationServiceImp.isAgeValid(age));

        String phoneNumber;
        do {
            System.out.println("Enter your phone number:");
            phoneNumber = scanner.nextLine().trim();
        }while (!validationServiceImp.isPhoneNumberValid(phoneNumber));

        Account account = new Account(userName,password,age,phoneNumber);

        accountServiceImp.createAccount(account);


    }


    private void login() {
        String userName , password;
        Account loginAccount;
        int numberOfAttempts = 0;
        do {
            System.out.println("Enter your name : ");
            userName = scanner.nextLine().trim();
            System.out.println("Enter your password:");
            password = scanner.nextLine().trim();
            loginAccount = accountServiceImp.getAccountByUserNameAndPassword(userName,password);
            numberOfAttempts++;
            if (numberOfAttempts>4) {
                throw new IllegalArgumentException("Many times of invalid user name or password pls try later :(.......");
            }
        }while (loginAccount == null);
        if (loginAccount.isAdmin()){
            showAdminPanel(loginAccount);
        }
        else {
            showUserMainMenu(loginAccount);
        }

    }


    private void showUserMainMenu(Account account) {
        System.out.println("Welcome " + account.getUserName());
        System.out.println();
        int numberOfAttempts = 0;
        while (true){
            System.out.println("1) Deposit\n2) Withdraw\n3) Transfer\n4) Show account details\n5) Change password\n6) Show transaction history\n7) Logout");
            System.out.println("pls enter your choice............");
            int choice = Integer.parseInt(scanner.nextLine());
            boolean isExit = false;
            switch (choice){
                case 1:
                    deposit(account);
                    break;
                case 2:
                    withdraw(account);
                    break;
                case 3:
                    transfer(account);
                    break;
                case 4:
                    showAccountDetails(account);
                    break;
                case 5:
                    changePassword(account);
                    break;
                case 6:
                    showTransactionHistory(account);
                    break;
                case 7:
                    System.out.println("Have a nice day:)...");
                    isExit= true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    numberOfAttempts++;
            }
            if (isExit) break;

            if (numberOfAttempts>4){
                throw new IllegalArgumentException("Many times of invalid choose pls contact with admin :(.......");
            }

        }
    }

    public void showAdminPanel(Account account){
        System.out.println("---------------------Welcome To Admin Menu---------------------");
        int numberOfAttempts = 0;
        while (true){
            System.out.println("1) Show all accounts\n2) Delete account\n3) Inactivate account\n4) Logout");
            System.out.println("pls enter your choice............");
            int choice = Integer.parseInt(scanner.nextLine());
            boolean isExit = false;
            switch (choice){
                case 1:
                    adminServiceImp.showAllAccounts();
                    break;
                case 2:
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Have a nice day:)...");
                    isExit= true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    numberOfAttempts++;
            }
            if (isExit) break;

            if (numberOfAttempts>4){
                throw new IllegalArgumentException("Many times of invalid choose pls try later :(.......");
            }

        }
    }


    private void deposit(Account account) {
        double depositAmount;
        do {
            System.out.println("Enter deposit amount: ");
            depositAmount = Double.parseDouble(scanner.nextLine().trim());
        }while (!validationServiceImp.isAmountValid(depositAmount));

        if (!validationServiceImp.isAccountExit(account.getUserName())){
            System.out.println("This account is not exist.");
            return;
        }

        accountServiceImp.increaseBalance(account,depositAmount);


    }


    private void withdraw (Account account){
        double withdrawAmount;
        do {
            System.out.println("Enter withdraw amount: ");
            withdrawAmount = Double.parseDouble(scanner.nextLine().trim());
        }while (!validationServiceImp.isAmountValid(withdrawAmount));

        if (!validationServiceImp.isAccountExit(account.getUserName())){
            System.out.println("This account is not exist.");
            return;
        }
        if (validationServiceImp.isBalanceEnough(withdrawAmount,account)){
            accountServiceImp.deductBalance(account,withdrawAmount);
        }
    }


    private void transfer(Account account){
        System.out.println("Enter destination user name: ");
        String destinationUserName = scanner.nextLine().trim();
        if (!validationServiceImp.isAccountExit(destinationUserName)){
            System.out.println("This destination account does not exist.");
            return;
        }
        if (!validationServiceImp.isAccountExit(account.getUserName())){
            System.out.println("This account is not exist.");
            return;
        }
        if (destinationUserName.equals(account.getUserName())){
            System.out.println("You can not transfer to yourself.");
            return;
        }

        double transferAmount;
        do {
            System.out.println("Enter transfer amount: ");
            transferAmount = Double.parseDouble(scanner.nextLine().trim());
        }while (!validationServiceImp.isAmountValid(transferAmount));

        if (validationServiceImp.isBalanceEnough(transferAmount,account)){
            accountServiceImp.transferMoney(account.getUserName(),destinationUserName,transferAmount);
        }
    }


    private void changePassword(Account account){
        String oldPassword;
        do {
            System.out.println("Enter your old password: ");
            oldPassword = scanner.nextLine().trim();
        }while (!validationServiceImp.isCorrectPassword(account,oldPassword));

        String newPassword;
        do {
            System.out.println("Enter your new password: ");
            newPassword = scanner.nextLine().trim();
            if (oldPassword.equals(newPassword)) {
                System.out.println("You can not use you old password as a new password");
            }
        }while (!validationServiceImp.isPasswordValid(newPassword) || oldPassword.equals(newPassword));

        accountServiceImp.updatePassword(account,newPassword);
    }

    private void showAccountDetails(Account account){
        AdminServiceImp.printAccount(account);
    }

    private void  showTransactionHistory(Account account){
        for (String transaction : account.getTransactionHistory()){
            System.out.println(transaction);
        }
    }

}
