package model;

public class GameSpeed {
    private long startTime;
    private long elapsedTime;
    private int multi;

    public GameSpeed() {
        this.multi = 1;
        this.startTime = System.currentTimeMillis();
        this.elapsedTime = 0;
    }

    public void changeGameSpeed(int newMulti) {
        // Frissítjük az eltelt időt az aktuális sebesség alapján
        elapsedTime += (System.currentTimeMillis() - startTime) * multi;
        startTime = System.currentTimeMillis(); // Új időzítés kezdete
        this.multi = newMulti; // Új sebességszorzó beállítása
    }

    public void startTimer() {
        this.startTime = System.currentTimeMillis();
    }

    public void stopTimer() {
        this.elapsedTime += (System.currentTimeMillis() - startTime) * multi;
    }

    public long getElapsedTimeInSeconds() {
        // Az eltelt idő kiszámítása az aktuális sebesség alapján
        return (elapsedTime + (System.currentTimeMillis() - startTime) * multi) / 1000;
    }

    public String getFormattedTime() {
        long seconds = getElapsedTimeInSeconds();
        long minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}