package model;

public class Grass extends Plant {
    public static final int PRICE = 500;
    private double growthRate;

    Coordinate coordinate;

    public Grass(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void grow() {
        // Növekedés logikája
    }
}
