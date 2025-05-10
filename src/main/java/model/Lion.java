package model;

import static view.GamePanel.TILE_SIZE;

/**
 * A Lion osztály az oroszlánt reprezentálja a játékban.
 * Leszármazottja a Carnivore osztálynak, és tartalmazza az oroszlán specifikus viselkedéseit.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 * <p>
 * Ha nincs explicit konstruktor, a fordító automatikusan létrehozza a public alapértelmezett konstruktort.
 */
public class Lion extends Carnivore {

    /**
     * Alapértelmezett konstruktor a Lion osztályhoz.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     */
    public Lion(){
        super();
    }

    /** Az oroszlán ára. */
    public static final int PRICE = 2000;

    /**
     * Visszaadja az oroszlán árát.
     * Ez a metódus public, hogy más osztályok is lekérhessék az oroszlán árát.
     * @return az oroszlán ára
     */
    @Override
    public int getPrice() {
        return PRICE;
    }

}
