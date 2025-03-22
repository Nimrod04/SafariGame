package model;

import java.util.List;

public class Path {
    private Coordinate start;
    private Coordinate end;
    private List<Coordinate> waypoints;
    String imagePath;
    Coordinate coordinate;
    public Path(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setImagePath(String path){
        this.imagePath = path;
    }

    public String getImagePath(){
        return imagePath;
    }

}
