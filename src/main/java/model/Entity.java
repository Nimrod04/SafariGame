package model;

public abstract class Entity {
    protected Coordinate position;
    protected double movementSpeed;

    public abstract void moveTo(Coordinate target);

    public abstract void move();
}
