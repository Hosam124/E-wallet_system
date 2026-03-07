package service.imp;

import service.AccountService;

import java.util.Scanner;

public class AccountServiceImp implements AccountService {
    @Override
    public void startProgram() {
        System.out.println("-------------------Welcome To E-wallet-------------------");
        Scanner scanner = new Scanner(System.in);
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
                    System.out.println("signup");
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
}
