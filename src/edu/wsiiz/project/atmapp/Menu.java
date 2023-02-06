package edu.wsiiz.project.atmapp;

import java.util.Scanner;

public class Menu {
    private Scanner input;
    private BankAccount account;

    private ATM atm;

    public Menu(ATM atm) {
        this.input = new Scanner(System.in);
        this.account = atm.getAccount();
        this.atm = atm;
    }

    public void displayOptions() {
        System.out.println("1. Wypłata");
        System.out.println("2. Wpłata");
        System.out.println("3. Sprawdzenie stanu konta");
        System.out.println("4. Zakończenie");
    }

    public void handleOption(int option) {
        switch (option) {
            case 1 -> {
                System.out.print("Podaj kwotę do wypłacenia: ");
                double amount = input.nextDouble();
                atm.withdrawCash(amount);
                System.out.println("Succesfully withdrawed: " + amount + "!");
            }
            case 2 -> {
                System.out.print("Podaj kwotę do wpłacenia: ");
                double amount = input.nextDouble();
                atm.makeDeposit(amount);
            }
            case 3 -> System.out.println("Stan konta: " + account.getBalance());
            case 4 -> System.out.println("Dziękujemy za skorzystanie z usług.");
            default -> System.out.println("Niepoprawna opcja.");
        }
    }
}
