package model;

/**
 * A Tile osztály a játék térképének egy csempéjét reprezentálja.
 * Minden csempe rendelkezik egy típussal, amely meghatározza, hogy milyen objektum vagy tereptárgy található rajta.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 */
public class Tile {

    /**
     * Beállítja a csempe típusát.
     * Ez a metódus public, hogy más osztályok is módosíthassák a csempe típusát, például a pályagenerálás vagy játék logika során.
     * @param type az új csempe típus
     */
    public void setType(TileType type) {
        this.type = type;
    }

    /**
     * A TileType enum a csempe típusait sorolja fel.
     * Az enum public, hogy más osztályok is használhassák a csempe típusokat.
     */
    public enum TileType {
        /**
         * Fű típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        GRASS,
        /**
         * Víz típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        WATER,
        /**
         * Út típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        ROAD,
        /**
         * Fa típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        TREE,
        /**
         * Bokor típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        BUSH,
        /**
         * Föld típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        DIRT,
        /**
         * Homok típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        SAND,
        /**
         * Gazella típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        GAZELLE,
        /**
         * Elefánt típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        ELEPHANT,
        /**
         * Oroszlán típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        LION,
        /**
         * Gepárd típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        CHEETAH,
        /**
         * Kapu típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        GATE,
        /**
         * Kamera típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        CAMERA,
        /**
         * Töltőállomás típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        CHARGINGSTATION,
        /**
         * Drón típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        DRONE,
        /**
         * Léghajó típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        AIRSHIP,
        /**
         * Parkőr típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        RANGER,
        /**
         * Jeep típusú csempe.
         * Az enum érték public, így más osztályokból is használható.
         */
        JEEP
    }

    private TileType type;

    /**
     * Létrehoz egy új Tile példányt a megadott típussal.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     * @param type a csempe típusa
     */
    public Tile(TileType type) {
        this.type = type;
    }

    /**
     * Visszaadja a csempe típusát.
     * Ez a metódus public, hogy más osztályok is lekérhessék a csempe típusát.
     * @return a csempe típusa
     */
    public TileType getType() {
        return type;
    }
}

