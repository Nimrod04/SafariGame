package model;

/**
 * A Bush osztály egy bokrot reprezentál a játékban, amely egy adott koordinátán helyezkedik el.
 * Leszármazottja a Plant osztálynak.
 */
public class Bush extends Plant {

    /** A bokor koordinátája a pályán. */
    Coordinate coordinate;

    /**
     * Létrehoz egy új Bush példányt a megadott koordinátán.
     * @param coordinate a bokor helye
     */
    public Bush(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * A bokor növekedését szimulálja (pl. bogyó termelés logikája).
     */
    public void grow() {
        // Bogyó termelés logikája
    }
}
