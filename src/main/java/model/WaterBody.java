package model;

/**
 * A WaterBody osztály egy vízfelületet reprezentál a játékban.
 * Tárolja a vízfelület pozícióját, mélységét, méretét és koordinátáját.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 */
public class WaterBody {
    /**
     * A vízfelület ára.
     * Ez a mező public static final, így az osztályból közvetlenül elérhető, konstans érték.
     */
    public static final int PRICE = 1000;

    private Coordinate position;
    private double depth;
    private double size;

    Coordinate coordinate;

    /**
     * Létrehoz egy új WaterBody példányt a megadott koordinátával.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     * @param coordinate a vízfelület pozíciója
     */
    public WaterBody(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
