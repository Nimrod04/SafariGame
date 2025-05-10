package model;

import java.util.ArrayList;

import static view.GamePanel.TILE_SIZE;

/**
 * Az Elephant osztály az elefántot reprezentálja a játékban.
 * Leszármazottja a Herbivore osztálynak, és tartalmazza az elefánt specifikus viselkedéseit.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 * <p>
 * Ha nincs explicit konstruktor, a fordító automatikusan létrehozza a public alapértelmezett konstruktort.
 */
public class Elephant extends Herbivore {

    /** Az elefánt ára. */
    public static final int PRICE = 1000;

    /**
     * Alapértelmezett konstruktor az Elephant osztályhoz.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     */
    public Elephant() {
        super();
    }

    /**
     * Az elefánt ormányának használatát szimuláló metódus.
     * Ez a metódus public lehet, hogy más osztályokból is meghívható legyen, például a játék logikájából vagy a grafikus felületről.
     */
    public void useTrunk() {
        // Elefánt ormány használata
    }

    /**
     * Visszaadja az elefánt árát.
     * Ez a metódus public, hogy más osztályok is lekérhessék az elefánt árát.
     * @return az elefánt ára
     */
    @Override
    public int getPrice() {
        return PRICE;
    }
}
