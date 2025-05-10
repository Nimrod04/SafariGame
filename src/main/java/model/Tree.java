package model;

/**
 * A Tree osztály egy fát reprezentál a játékban.
 * Leszármazottja a Plant osztálynak, és tartalmazza a fa specifikus tulajdonságait, például az árnyék területét.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 */
public class Tree extends Plant {
    /**
     * A fa ára.
     * Ez a mező public static final, így az osztályból közvetlenül elérhető, konstans érték.
     */
    public static final int PRICE = 800;

    private double shadeArea;

    Coordinate coordinate;

    /**
     * Létrehoz egy új Tree példányt a megadott koordinátával.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     * @param coordinate a fa pozíciója
     */
    public Tree(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * A fa növekedését szimuláló metódus.
     * Ez a metódus public, hogy más osztályokból is meghívható legyen, például a játék logikájából.
     */
    @Override
    public void grow() {

    }
}
