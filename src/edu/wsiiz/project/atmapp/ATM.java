package edu.wsiiz.project.atmapp;

import java.sql.SQLException;
import java.util.Locale;

public class ATM extends CardReader {
    private final int id;
    private double cashInMachine;
    private double availableBalance;
    private BankAccount account;
    public static QueryExecutor query() {
        return new QueryExecutor();
    }
    public ATM(int id, double cashInMachine) {
        this.id = id;
        this.cashInMachine = cashInMachine;
    }

    public boolean checkCard(Customer c) {
        return c != null;
    }
    public void setAccount(BankAccount bankAccount) {
        this.account = bankAccount;
        this.availableBalance = bankAccount.getBalance();
    }

    public int getId() {
        return this.id;
    }

    public void withdrawCash(double amount) throws SQLException {
        if (amount > availableBalance) {
            System.out.println("You can't withdraw: " + amount + "! The missing funds to be paid out are: " + (amount - availableBalance) + "!");
        } else if (amount > cashInMachine) {
            System.out.println("You can't withdraw: " + amount + "! This ATM doesn't have that much cash!");
        } else {
            account.withdraw(amount);
            availableBalance -= amount;
            cashInMachine -= amount;
            String updateATMQuery = String.format((Locale) null,"UPDATE `atms` SET `cashInMachine` = %f WHERE id =%2d", cashInMachine, getId());
            String updateAccountQuery = String.format((Locale) null,"UPDATE `bankaccounts` SET `balance` = %f WHERE id =%2d", availableBalance, account.getId());
            query().executeUpdate(updateATMQuery);
            query().executeUpdate(updateAccountQuery);
            System.out.println("Successfully withdrawed: " + amount + "!");
        }
    }
    public void makeDeposit(double amount) throws SQLException {
        account.deposit(amount);
        availableBalance += amount;
        cashInMachine += amount;
        String updateATMQuery = String.format((Locale) null,"UPDATE `atms` SET `cashInMachine` = %f WHERE id =%2d", cashInMachine, getId());
        String updateAccountQuery = String.format((Locale) null,"UPDATE `bankaccounts` SET `balance` = %f WHERE id =%2d", availableBalance, account.getId());
        query().executeUpdate(updateATMQuery);
        query().executeUpdate(updateAccountQuery);
        System.out.println("Successfully deposited: " + amount + "!");
    }
    public BankAccount getAccount() {
        return this.account;
    }
}