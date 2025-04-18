package model;

import java.awt.*;

public class ChargingStation {
    public static final int PRICE = 1000;
    private Coordinate position;

    public ChargingStation(Coordinate position) {
        this.position = position;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }
}
