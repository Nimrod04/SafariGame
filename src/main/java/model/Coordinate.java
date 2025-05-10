package model;

/**
 * A Coordinate osztály egy kétdimenziós koordinátát reprezentál a játékban.
 * Tárolja az x és y értékeket, valamint biztosít getter/setter metódusokat,
 * továbbá felülírja az equals és hashCode metódusokat az összehasonlításhoz.
 */
public class Coordinate {
    private int x, y;

    /**
     * Létrehoz egy új Coordinate példányt a megadott x és y értékekkel.
     * @param x az x koordináta
     * @param y az y koordináta
     */
    public Coordinate(int x, int y) { 
        this.x = x; 
        this.y = y; 
    }

    /**
     * Visszaadja az x koordinátát.
     * @return az x koordináta
     */
    public int getPosX(){
        return x;
    }

    /**
     * Visszaadja az y koordinátát.
     * @return az y koordináta
     */
    public int getPosY(){
        return y;
    }

    /**
     * Beállítja az x koordinátát.
     * @param x az új x koordináta
     */
    public void setPosX(int x) {
        this.x = x;
    }

    /**
     * Beállítja az y koordinátát.
     * @param y az új y koordináta
     */
    public void setPosY(int y) {
        this.y = y;
    }

    /**
     * Két Coordinate objektum egyenlőségét vizsgálja.
     * @param obj az összehasonlítandó objektum
     * @return true, ha a két koordináta megegyezik, különben false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinate that = (Coordinate) obj;
        return x == that.x && y == that.y;
    }

    /**
     * Visszaadja a koordináta hash kódját.
     * @return a hash kód
     */
    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
