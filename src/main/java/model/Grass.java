package model;

/**
 * A Grass osztály a játékban egy füvet reprezentál, amely egy adott koordinátán helyezkedik el.
 * Leszármazottja a Plant osztálynak, és tartalmazza a fű növekedési logikáját.
 */
public class Grass extends Plant {
    /** A fű ára. */
    public static final int PRICE = 500;
    /** A fű növekedési rátája. */
    private double growthRate;

    /** A fű koordinátája a pályán. */
    Coordinate coordinate;

    /**
     * Létrehoz egy új Grass példányt a megadott koordinátán.
     * @param coordinate a fű helye
     */
    public Grass(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * A fű növekedését szimuláló metódus.
     */
    public void grow() {
        // Növekedés logikája
    }
}
