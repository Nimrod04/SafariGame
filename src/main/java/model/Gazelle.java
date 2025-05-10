package model;

import static view.GamePanel.TILE_SIZE;

/**
 * A Gazelle osztály a gazellát reprezentálja a játékban.
 * Leszármazottja a Herbivore osztálynak, és tartalmazza a gazella specifikus viselkedéseit.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 * <p>
 * Ha nincs explicit konstruktor, a fordító automatikusan létrehozza a public alapértelmezett konstruktort.
 */
public class Gazelle extends Herbivore {

    /** A gazella ára. */
    public static final int PRICE = 1000;

    /**
     * Alapértelmezett konstruktor a Gazelle osztályhoz.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     */
    public Gazelle() {
        super();
    }

    /**
     * A gazella menekülését szimuláló metódus.
     * Ez a metódus public lehet, hogy más osztályokból is meghívható legyen, például a játék logikájából vagy a grafikus felületről.
     */
    public void flee() {
        // Gazella menekülés
    }

    /**
     * Visszaadja a gazella árát.
     * Ez a metódus public, hogy más osztályok is lekérhessék a gazella árát.
     * @return a gazella ára
     */
    @Override
    public int getPrice() {
        return PRICE;
    }
}
