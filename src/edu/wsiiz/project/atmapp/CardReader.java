package edu.wsiiz.project.atmapp;

public class CardReader {
    private BankAccount account;

    public CardReader() {
        this.account = null;
    }
    public boolean checkPin(BankAccount account ,double PIN) {
        if (account.getPIN() == PIN) {
            this.account = account;
            return true;
        } else {
            return false;
        }
    }

    public void removeCard() {
        System.out.println("Card has been removed!");
        this.account = null;
    }
}
