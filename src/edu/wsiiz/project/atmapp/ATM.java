package edu.wsiiz.project.atmapp;

import java.util.Scanner;

public class ATM extends CardReader {
    private double cashInMachine;
    private double availableBalance;
    private BankAccount account;
    private final CardReader cardReader = new CardReader();

    public ATM(double cashInMachine) {
        this.cashInMachine = cashInMachine;
    }

    public boolean insertCard(BankAccount account, double PIN) {
        this.account = account;
        if (cardReader.checkPin(account, PIN)) {
            this.availableBalance = account.getBalance();
            System.out.println("Good PIN! Welcome to your account " + account.getCustomerName() + "!");
            return true;
        } else {
            System.out.println("Wrong PIN!");
            return false;
        }
    }

    public double checkBalance() {
        return availableBalance;
    }

    public double withdrawCash(double amount) {
        if (amount > availableBalance) {
            return 0;
        } else if (amount > cashInMachine) {
            return 0;
        } else {
            account.withdraw(amount);
            availableBalance -= amount;
            cashInMachine -= amount;
            return amount;
        }
    }

    public void makeDeposit(double amount) {
        account.deposit(amount);
        availableBalance += amount;
        cashInMachine += amount;
    }

    public BankAccount getAccount() {
        return this.account;
    }
}