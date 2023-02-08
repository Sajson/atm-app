package edu.wsiiz.project.atmapp;

import java.sql.*;
import java.util.Scanner;

public class ATMRunner {
    // method to get input from keyboard
    public static Scanner getFromKeyboard() {
        return new Scanner(System.in);
    }
    // creating a method of the ObjectCreator class, which creates objects for us based on data retrieved from the database
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
        // if the user provide pin more than 4 times, should not be able to continue trying to provide a pin
        boolean pinEnterLimitReached = false;
        int selection, PIN, counter = 1;
        displayATMSOptions();
        selection = getFromKeyboard().nextInt();
        try {
            // creating a query that selects the right ATM for us
            String atmQuery = String.format("SELECT * FROM atms WHERE id = %2d", selection);
            ATM atm = create().createATM(atmQuery);
            // after creating our ATM, we need to insert our card
            atm.insertCard();
            System.out.println("Provide PIN to your card: ");
            PIN = getFromKeyboard().nextInt();
            // creating a query that selects the right customer and later right account
            String customerQuery = String.format("SELECT customers.name, customers.surname, customers.address, customers.phone_number, customers.account_id FROM customers JOIN bankaccounts b on b.id = customers.account_id WHERE b.PIN = %2d", PIN);
            Customer customer = create().createCustomer(customerQuery);
            // if the customer was not found in the database, it means that the PIN was not entered correctly, user have 4 tries
            while (!atm.checkCard(customer) && counter != 4) {
                System.out.println("PIN is incorrect, enter it again");
                PIN = getFromKeyboard().nextInt();
                customerQuery = String.format("SELECT customers.name, customers.surname, customers.address, customers.phone_number, customers.account_id FROM customers JOIN bankaccounts b on b.id = customers.account_id WHERE b.PIN = %2d", PIN);
                customer = create().createCustomer(customerQuery);
                counter++;
                // abbreviated notation if, counter == 4 returns true or false depending on the state
                pinEnterLimitReached = counter == 4;
            }
            // if the user provide pin more than 4 times, should not be able to continue trying to provide a pin
            if (pinEnterLimitReached) {
                System.out.println("You've used up your PIN entry limit try using your card again later!");
                atm.removeCard();
                // The user gets his card back and the ATM ends his work
                System.exit(0);
            }
            // each user has only one account
            String accountQuery = String.format("SELECT * FROM bankaccounts WHERE id = %2d", customer.getAccount_id());
            BankAccount account = create().createAccount(accountQuery,customer);
            // apply user account to atm to starts operations
            atm.setAccount(account);
            // generate menu
            Menu menu = new Menu(atm);
            selection = 0;
            // warm welcome for our user
            menu.welcomeMessage();
            // until the user selects option 4 (means exit) he can perform operations on his account
            while (selection != 4) {
                menu.displayOptions();
                selection = getFromKeyboard().nextInt();
                menu.handleOption(selection);
            }
            // The user gets his card back and the ATM ends his work
            atm.removeCard();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
