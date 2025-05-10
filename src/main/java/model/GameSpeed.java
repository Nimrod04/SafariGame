package model;

/**
 * A GameSpeed osztály a játék időkezelését és sebességét kezeli.
 * Lehetővé teszi az idő gyorsítását, lassítását, az eltelt idő lekérdezését és formázását.
 */
public class GameSpeed {
    private long startTime;
    private long elapsedTime;
    private int multi;

    /**
     * Létrehoz egy új GameSpeed példányt alapértelmezett sebességgel.
     */
    public GameSpeed() {
        this.multi = 1;
        this.startTime = System.currentTimeMillis();
        this.elapsedTime = 0;
        
    }

    /**
     * Visszaadja az aktuális sebességszorzót.
     * @return a sebességszorzó értéke
     */
    public int getMulti() {
        return this.multi;
    }

    /**
     * Megváltoztatja a játék sebességét, és frissíti az eltelt időt.
     * @param newMulti az új sebességszorzó
     */
    public void changeGameSpeed(int newMulti) {
        // Frissítjük az eltelt időt az aktuális sebesség alapján
        elapsedTime += (System.currentTimeMillis() - startTime) * multi;
        startTime = System.currentTimeMillis(); // Új időzítés kezdete
        this.multi = newMulti; // Új sebességszorzó beállítása
    }

    /**
     * Elindítja az időzítőt.
     */
    public void startTimer() {
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Leállítja az időzítőt, és frissíti az eltelt időt.
     */
    public void stopTimer() {
        this.elapsedTime += (System.currentTimeMillis() - startTime) * multi;
    }

    /**
     * Visszaadja az eltelt időt másodpercben, figyelembe véve a sebességszorzót.
     * @return az eltelt idő másodpercben
     */
    public long getElapsedTimeInSeconds() {
        // Az eltelt idő kiszámítása az aktuális sebesség alapján
        return (elapsedTime + (System.currentTimeMillis() - startTime) * multi) / 1000;
    }

    /**
     * Visszaadja az eltelt időt mm:ss formátumban.
     * @return az eltelt idő formázott szövege
     */
    public String getFormattedTime() {
        long seconds = getElapsedTimeInSeconds();
        long minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}