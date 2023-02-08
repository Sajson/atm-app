package edu.wsiiz.project.atmapp;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private final Scanner input;
    private final BankAccount account;

    private final ATM atm;

    public Menu(ATM atm) {
        this.input = new Scanner(System.in);
        this.account = atm.getAccount();
        this.atm = atm;
    }
    public void welcomeMessage() {
        System.out.println("Welcome to your account: " + account.getCustomerName() + "!");
    }

    public void displayOptions() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Checking your account balance");
        System.out.println("4. Exit");
    }

    public void handleOption(int option) throws SQLException {
        switch (option) {
            case 1 -> {
                System.out.print("Specify the amount to withdraw: ");
                double amount = input.nextDouble();
                atm.withdrawCash(amount);
            }
            case 2 -> {
                System.out.print("Specify the amount to be deposited: ");
                double amount = input.nextDouble();
                atm.makeDeposit(amount);
            }
            case 3 -> System.out.println("Account balance: " + account.getBalance());
            case 4 -> System.out.println("Thank you for using the services!");
            default -> System.out.println("Invalid option.");
        }
    }
}
