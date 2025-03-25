package model;

public abstract class Entity {

    protected double movementSpeed;

    Coordinate actualCoordinate;
    Coordinate targetCoordinate;

    public abstract void moveTo(Coordinate target);

    public abstract void move();

    public void setActualCoordinate(Coordinate c){
        actualCoordinate = c;
    };
    public void setTargetCoordinate(Coordinate c){
        targetCoordinate = c;
    };
}
