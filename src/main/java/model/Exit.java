package model;

/**
 * Az Exit osztály a játék kijáratát reprezentálja.
 * Minden kijárat rendelkezik egy koordinátával, amely meghatározza a helyét a pályán.
 */
public class Exit {
    /** A kijárat koordinátája. */
    Coordinate coordinate;

    /**
     * Létrehoz egy új Exit példányt a megadott koordinátán.
     * @param coordinate a kijárat helye
     */
    public Exit(Coordinate coordinate){
        this.coordinate = coordinate;
    }
}
