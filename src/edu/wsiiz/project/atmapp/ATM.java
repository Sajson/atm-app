package edu.wsiiz.project.atmapp;

import java.sql.SQLException;
import java.util.Locale;

public class ATM extends CardReader {
    private final int id;
    private double cashInMachine;
    private double availableBalance;
    private BankAccount account;
    // method for query Executing
    public static QueryExecutor query() {
        return new QueryExecutor();
    }
    public ATM(int id, double cashInMachine) {
        this.id = id;
        this.cashInMachine = cashInMachine;
    }
    // if client has been created return true (means our PIN code is correct)
    public boolean checkCard(Customer c) {
        return c != null;
    }
    // method that sets the account field of this class to use in the rest of the methods
    public void setAccount(BankAccount bankAccount) {
        this.account = bankAccount;
        this.availableBalance = bankAccount.getBalance();
    }

    public int getId() {
        return this.id;
    }

    public void withdrawCash(double amount) throws SQLException {
        // if the withdrawal amount is greater than the account balance, display the appropriate message
        if (amount > availableBalance) {
            System.out.println("You can't withdraw: " + amount + "! The missing funds to be paid out are: " + (amount - availableBalance) + "!");
        }
        // if the withdrawal amount is greater than the ATM balance, display the appropriate message
        else if (amount > cashInMachine) {
            System.out.println("You can't withdraw: " + amount + "! This ATM doesn't have that much cash!");
        } else {
            account.withdraw(amount);
            availableBalance -= amount;
            cashInMachine -= amount;
            // Create queries to update database
            // Using Locale argument to String.format with a value of null to get the double value in the correct SQL format
            String updateATMQuery = String.format((Locale) null,"UPDATE `atms` SET `cashInMachine` = %f WHERE id =%2d", cashInMachine, getId());
            String updateAccountQuery = String.format((Locale) null,"UPDATE `bankaccounts` SET `balance` = %f WHERE id =%2d", availableBalance, account.getId());
            // Run queries
            query().executeUpdate(updateATMQuery);
            query().executeUpdate(updateAccountQuery);
            System.out.println("Successfully withdrawed: " + amount + "!");
        }
    }
    public void makeDeposit(double amount) throws SQLException {
        account.deposit(amount);
        availableBalance += amount;
        cashInMachine += amount;
        // Create queries to update database
        // Using Locale argument to String.format with a value of null to get the double value in the correct SQL format
        String updateATMQuery = String.format((Locale) null,"UPDATE `atms` SET `cashInMachine` = %f WHERE id =%2d", cashInMachine, getId());
        String updateAccountQuery = String.format((Locale) null,"UPDATE `bankaccounts` SET `balance` = %f WHERE id =%2d", availableBalance, account.getId());
        // Run queries
        query().executeUpdate(updateATMQuery);
        query().executeUpdate(updateAccountQuery);
        System.out.println("Successfully deposited: " + amount + "!");
    }
    // method used in menu to get balance of actual account
    public BankAccount getAccount() {
        return this.account;
    }
}