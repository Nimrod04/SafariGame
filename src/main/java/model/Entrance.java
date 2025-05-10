package model;

/**
 * Az Entrance osztály a játék bejáratát reprezentálja.
 * Minden bejárat rendelkezik egy koordinátával, amely meghatározza a helyét a pályán.
 */
public class Entrance {
    /** A bejárat koordinátája. */
    Coordinate coordinate;

    /**
     * Létrehoz egy új Entrance példányt a megadott koordinátán.
     * @param coordinate a bejárat helye
     */
    public Entrance(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
