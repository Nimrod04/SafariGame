package model;

public class Tree extends Plant {
    public static final int PRICE = 800;
    private double shadeArea;

    Coordinate coordinate;

    public Tree(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public void grow() {

    }
}
