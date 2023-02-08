package edu.wsiiz.project.atmapp;

import java.sql.*;
import java.util.Scanner;

public class ATMRunner {
    public static Scanner getFromKeyboard() {
        return new Scanner(System.in);
    }
    public static ObjectCreator create() {
        return new ObjectCreator();
    }
    public static void displayATMSOptions() {
        System.out.println("Choose an ATM");
        System.out.println("1. EURONET");
        System.out.println("2. PEKAO SA");
        System.out.println("3. MBANK");
    }
    public static void main(String[] args) {
        boolean pinEnterLimitReached = false;
        int selection, PIN, counter = 1;
        displayATMSOptions();
        selection = getFromKeyboard().nextInt();
        try {
            String atmQuery = String.format("SELECT * FROM atms WHERE id = %2d", selection);
            ATM atm = create().createATM(atmQuery);
            atm.insertCard();
            System.out.println("Provide PIN to your card: ");
            PIN = getFromKeyboard().nextInt();
            String customerQuery = String.format("SELECT customers.name, customers.surname, customers.address, customers.phone_number, customers.account_id FROM customers JOIN bankaccounts b on b.id = customers.account_id WHERE b.PIN = %2d", PIN);
            Customer customer = create().createCustomer(customerQuery);
            while (!atm.checkCard(customer) && counter != 4) {
                System.out.println("PIN is incorrect, enter it again");
                PIN = getFromKeyboard().nextInt();
                customerQuery = String.format("SELECT customers.name, customers.surname, customers.address, customers.phone_number, customers.account_id FROM customers JOIN bankaccounts b on b.id = customers.account_id WHERE b.PIN = %2d", PIN);
                customer = create().createCustomer(customerQuery);
                counter++;
                pinEnterLimitReached = counter == 4;
            }
            if (pinEnterLimitReached) {
                System.out.println("You've used up your PIN entry limit try using your card again later!");
                System.exit(0);
            }
            String accountQuery = String.format("SELECT * FROM bankaccounts WHERE id = %2d", customer.getAccount_id());
            BankAccount account = create().createAccount(accountQuery,customer);
            atm.setAccount(account);
            Menu menu = new Menu(atm);
            selection = 0;
            menu.welcomeMessage();
            while (selection != 4) {
                menu.displayOptions();
                selection = getFromKeyboard().nextInt();
                menu.handleOption(selection);
            }
            atm.removeCard();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
