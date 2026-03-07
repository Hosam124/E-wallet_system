package service.imp;

import model.Account;
import service.AppService;

import java.util.Objects;
import java.util.Scanner;

public class AppServiceImp implements AppService {

    private AccountServiceImp accountServiceImp = new AccountServiceImp();
    private final Scanner scanner = new Scanner(System.in);


    @Override
    public void startProgram() {
        System.out.println("-------------------Welcome To E-wallet-------------------");

        int numberOfAttempts = 0;
        while (true){
            System.out.println("1) Login        2) Signup       3) Exit");
            System.out.println("pls enter your choice............");
            int choice = scanner.nextInt();
            boolean isExit = false;
            switch (choice){
                case 1:
                    System.out.println("login");
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
        System.out.println("Enter your name : ");
        String userName = scanner.next();
        System.out.println("Enter your password:");
        String password = scanner.next();
        System.out.println("Enter your age:");
        double age = scanner.nextDouble();
        System.out.println("Enter your phone number:");
        String phoneNumber = scanner.next();

        Account account = new Account(userName,password,age,phoneNumber);

        accountServiceImp.createAccount(account);


    }
}
