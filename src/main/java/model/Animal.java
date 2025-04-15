package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static view.GamePanel.*;

public abstract class Animal {
    protected String species;

    protected int lifetime; //konstruktorban random értéket adni neki
    protected int age;

    Coordinate actualCoordinate;
    Coordinate targetCoordinate;


    protected double foodLevel;
    protected int maxFood;
    protected double waterLevel;
    protected int maxWater;

    ArrayList<Integer[]> food;
    ArrayList<Integer[]> drink;
    protected List<Animal> group;
    Random random = new Random();

    public Animal(){
        this.lifetime = (int) (Math.random() * 7) + 5;
        age = 0;
        targetCoordinate = null;
        int posX = (int) (Math.random() *40*TILE_SIZE) ;
        int posY = (int) (Math.random() * 20*TILE_SIZE) ;
        actualCoordinate = new Coordinate(posX,posY);
    }
    public Animal(int x, int y){
        this.lifetime = (int) (Math.random() * 7) + 5;
        age = 0;
        targetCoordinate = null;
        actualCoordinate = new Coordinate(x,y);
    }

    public void findWater(){
        if(!drink.isEmpty()){
            targetCoordinate = new Coordinate(drink.getLast()[0],drink.getLast()[1]);
        }
    };
    public void findFood(){
        if(!food.isEmpty()){
            targetCoordinate = new Coordinate(food.getLast()[0],food.getLast()[1]);
        }
    };

    private void addFood(){
        //ha van a környéken víz vagy étel

    }


    public void setActualCoordinate(Coordinate c){
        actualCoordinate = c;
    };
    public void setTargetCoordinate(Coordinate c){
        targetCoordinate = c;
    };
    public Coordinate getCoordinate(){
        return actualCoordinate;
    };


    protected List<Animal> getGroup() {
        return group;
    };

    public abstract void eat();
    public abstract void drink();
    public abstract void nap();  // esetleg true/false értékkel

    public abstract void moveTo();

    public abstract boolean hasReachedTarget();

    public abstract void generateRandomTarget();

    public abstract void moveTo(Coordinate target);
}
