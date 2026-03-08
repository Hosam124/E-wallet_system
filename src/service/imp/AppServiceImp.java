package service.imp;

import model.Account;
import model.EWalletSystem;
import service.AppService;

import java.util.Objects;
import java.util.Scanner;

public class AppServiceImp implements AppService {

    private final AccountServiceImp accountServiceImp ;
    private final ValidationServiceImp validationServiceImp ;
    private final Scanner scanner = new Scanner(System.in);

    public AppServiceImp(EWalletSystem eWalletSystem) {
        validationServiceImp = new ValidationServiceImp(eWalletSystem);
        accountServiceImp = new AccountServiceImp(eWalletSystem);
    }

    @Override
    public void startProgram() {
        System.out.println("-------------------Welcome To E-wallet-------------------");

        int numberOfAttempts = 0;
        while (true){
            System.out.println("1) Login        2) Signup       3) Exit");
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

    @Override
    public void signup() {
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

    @Override
    public void login() {
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



    }

}
