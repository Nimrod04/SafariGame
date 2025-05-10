package model;

import java.util.Random;

/**
 * Az Entity absztrakt osztály egy általános entitást reprezentál a játékban,
 * amelynek van mozgási sebessége, aktuális és cél koordinátája.
 * Az entitás képes mozogni egy cél koordináta felé, és leszármazottai implementálják a move() metódust.
 */
public abstract class Entity {

    /** Az entitás mozgási sebessége. */
    protected double movementSpeed;

    /** Az entitás aktuális koordinátája. */
    Coordinate actualCoordinate;
    /** Az entitás cél koordinátája. */
    Coordinate targetCoordinate;

    /** Véletlenszám-generátor az entitás számára. */
    Random random = new Random();

    /**
     * Az Entity alapértelmezett konstruktora.
     * Véletlenszerű kezdőpozíciót állít be az entitásnak.
     */
    public Entity(){
        targetCoordinate = null;
        int posX = (int) (Math.random() * 400) ;
        int posY = (int) (Math.random() * 400) ;
        actualCoordinate = new Coordinate(posX,posY);
    }

    /**
     * Az entitást a cél koordináta felé mozgatja a movementSpeed alapján.
     * @param target a cél koordináta
     */
    public void moveTo(Coordinate target) {
        int deltaX = target.getPosX() - actualCoordinate.getPosX();
        int deltaY = target.getPosY() - actualCoordinate.getPosY();

        int stepX = (int) ( Math.signum(deltaX) * Math.min(Math.abs(deltaX), movementSpeed));
        int stepY = (int) ( Math.signum(deltaY) * Math.min(Math.abs(deltaY), movementSpeed));

        actualCoordinate = new Coordinate(actualCoordinate.getPosX() + stepX, actualCoordinate.getPosY() + stepY);
    }

    /**
     * Az entitás mozgását megvalósító absztrakt metódus.
     * Leszármazott osztályok implementálják.
     */
    public abstract void move();

    /**
     * Beállítja az entitás aktuális koordinátáját.
     * @param c az új aktuális koordináta
     */
    public void setActualCoordinate(Coordinate c){
        actualCoordinate = c;
    };

    /**
     * Beállítja az entitás cél koordinátáját.
     * @param c az új cél koordináta
     */
    public void setTargetCoordinate(Coordinate c){
        targetCoordinate = c;
    };
}
