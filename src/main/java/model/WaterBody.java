package model;

public class WaterBody {
    public static final int PRICE = 1000;
    private Coordinate position;
    private double depth;
    private double size;

    Coordinate coordinate;
    public WaterBody(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
