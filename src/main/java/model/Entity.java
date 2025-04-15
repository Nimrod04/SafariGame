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


    public void moveTo(Coordinate target) {
        int deltaX = target.getPosX() - actualCoordinate.getPosX();
        int deltaY = target.getPosY() - actualCoordinate.getPosY();

        int stepX = (int) ( Math.signum(deltaX) * Math.min(Math.abs(deltaX), movementSpeed));
        int stepY = (int) ( Math.signum(deltaY) * Math.min(Math.abs(deltaY), movementSpeed));

        actualCoordinate = new Coordinate(actualCoordinate.getPosX() + stepX, actualCoordinate.getPosY() + stepY);
    }

    public abstract void move();

    public void setActualCoordinate(Coordinate c){
        actualCoordinate = c;
    };
    public void setTargetCoordinate(Coordinate c){
        targetCoordinate = c;
    };
}
