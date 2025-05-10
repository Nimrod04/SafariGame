package model;

/**
 * A Finance osztály a játék pénzügyi egyenlegét kezeli.
 * Lehetővé teszi az egyenleg növelését, csökkentését és lekérdezését.
 */
public class Finance {

    /** Az aktuális pénzügyi egyenleg. */
    private double currentBalance;

    /**
     * Létrehoz egy új Finance példányt alapértelmezett egyenleggel.
     */
    public Finance() {
        this.currentBalance = 100000;
    }

    /**
     * Növeli az egyenleget a megadott összeggel.
     * @param amount a növelés összege
     */
    public void increase(double amount) {
        currentBalance += amount;
    }

    /**
     * Csökkenti az egyenleget a megadott összeggel.
     * @param amount a csökkentés összege
     */
    public void decrease(double amount) {
        currentBalance -= amount;
    }

    /**
     * Visszaadja az aktuális egyenleget.
     * @return az aktuális egyenleg
     */
    public double getBalance() {
        return currentBalance;
    }
}
