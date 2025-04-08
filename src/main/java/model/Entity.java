package model;

import java.util.Random;

public abstract class Entity {

    protected double movementSpeed;

    Coordinate actualCoordinate;
    Coordinate targetCoordinate;

    Random random = new Random();

    public Entity(){
        targetCoordinate = null;
        int posX = (int) (Math.random() * 400) ;
        int posY = (int) (Math.random() * 400) ;
        actualCoordinate = new Coordinate(posX,posY);
    }

    public abstract void moveTo(Coordinate target);

    public abstract void move();

    public void setActualCoordinate(Coordinate c){
        actualCoordinate = c;
    };
    public void setTargetCoordinate(Coordinate c){
        targetCoordinate = c;
    };
}
