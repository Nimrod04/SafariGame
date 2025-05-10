package model;

/**
 * A Plant absztrakt osztály a növényeket reprezentálja a játékban.
 * Leszármazott osztályok valósítják meg a konkrét növényfajtákat (pl. Grass).
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 */
public abstract class Plant {
    /** A növény pozíciója a pályán. */
    protected Coordinate position;
    /** A növény ára. */
    protected double price;

    /**
     * Alapértelmezett konstruktor a Plant osztályhoz.
     * Ez a konstruktor public vagy protected lehet, hogy a leszármazott osztályok példányosíthassák a Plant-et.
     * Ha nincs explicit konstruktor, a fordító automatikusan létrehozza az alapértelmezett konstruktort.
     */
    public Plant() {
        // Alapértelmezett konstruktor
    }

    /**
     * A növény növekedését szimuláló absztrakt metódus.
     * Ez a metódus public, hogy a leszármazott osztályok implementálhassák és más osztályokból is meghívható legyen.
     */
    public abstract void grow();
}
