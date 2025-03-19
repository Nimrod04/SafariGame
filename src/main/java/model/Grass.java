package model;

public class Grass extends Plant {
    private double growthRate;

    Coordinate coordinate;

    public Grass(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void grow() {
        // Növekedés logikája
    }
}
