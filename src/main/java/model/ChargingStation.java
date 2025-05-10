package model;

import java.awt.*;

/**
 * A ChargingStation osztály egy töltőállomást reprezentál a játékban.
 * A töltőállomásnak van pozíciója, és meghatározott ára.
 */
public class ChargingStation {
    /** A töltőállomás ára. */
    public static final int PRICE = 1000;
    /** A töltőállomás pozíciója. */
    private Coordinate position;

    /**
     * Létrehoz egy új ChargingStation példányt a megadott pozícióval.
     * @param position a töltőállomás pozíciója
     */
    public ChargingStation(Coordinate position) {
        this.position = position;
    }

    /**
     * Visszaadja a töltőállomás pozícióját.
     * @return a töltőállomás pozíciója
     */
    public Coordinate getPosition() {
        return position;
    }

    /**
     * Beállítja a töltőállomás pozícióját.
     * @param position az új pozíció
     */
    public void setPosition(Coordinate position) {
        this.position = position;
    }
}
