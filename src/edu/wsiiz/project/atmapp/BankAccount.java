package edu.wsiiz.project.atmapp;

public class BankAccount {
    private double balance;
    private String accountNumber;
    private Customer customer;
    private double PIN;

    public BankAccount(double balance, String accountNumber, Customer customer, double PIN) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.PIN = PIN;
    }

    public double getPIN() {
        return PIN;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customer.getName();
    }
}
