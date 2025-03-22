package model;

import java.util.List;

public class Path {
    private Coordinate start;
    private Coordinate end;
    private List<Coordinate> waypoints;
    private String imagePath;

    Coordinate coordinate;
    public Path(Coordinate coordinate,  boolean l) {
        this.coordinate = coordinate;
    }

    void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return this.imagePath;
    }


}
