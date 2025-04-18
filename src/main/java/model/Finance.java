package model;

public class Finance {

    private double currentBalance;

    public Finance() {
        this.currentBalance = 100000;
    }

    public void increase(double amount) {
        currentBalance += amount;
    }

    public void decrease(double amount) {
        currentBalance -= amount;
    }

    public double getBalance() {
        return currentBalance;
    }
}
