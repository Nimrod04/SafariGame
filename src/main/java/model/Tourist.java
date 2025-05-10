package model;

/**
 * A Tourist osztály egy látogatót reprezentál a játékban.
 * Tárolja a látogató elégedettségi szintjét és az elköltött pénz mennyiségét.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 */
public class Tourist {
    /** A látogató elégedettségi szintje. */
    private double satisfactionLevel;

    /** A látogató által elköltött pénz mennyisége. */
    private double moneySpent;

    /**
     * Alapértelmezett konstruktor a Tourist osztályhoz.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     */
    public Tourist() {
        
    }
}
