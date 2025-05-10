package model;

import java.util.List;

/**
 * A Path osztály egy útvonalat reprezentál a játékban.
 * Tartalmazza az útvonal kezdő- és végpontját, köztes pontjait, valamint egy képfájlt és koordinátát.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 */
public class Path {
    private Coordinate start;
    private Coordinate end;
    private List<Coordinate> waypoints;
    String imagePath;
    Coordinate coordinate;

    /**
     * Létrehoz egy új Path példányt a megadott koordinátával.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     * @param coordinate az útvonal koordinátája
     * @param l logikai paraméter (jelentése az implementációtól függ)
     */
    public Path(Coordinate coordinate, boolean l) {
        this.coordinate = coordinate;
    }

    /**
     * Beállítja az útvonalhoz tartozó képfájl elérési útját.
     * Ez a metódus public, hogy más osztályok is beállíthassák az útvonal képét.
     * @param path a képfájl elérési útja
     */
    public void setImagePath(String path){
        this.imagePath = path;
    }

    /**
     * Visszaadja az útvonalhoz tartozó képfájl elérési útját.
     * Ez a metódus public, hogy más osztályok is lekérhessék az útvonal képét.
     * @return a képfájl elérési útja
     */
    public String getImagePath(){
        return imagePath;
    }

}
