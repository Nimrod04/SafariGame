package model;

public class Bush extends Plant {
    private double berryProductionRate;

    Coordinate coordinate;

    public Bush(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void grow() {
        // Bogyó termelés logikája
    }
}
