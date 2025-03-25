package model;

import java.util.List;

public abstract class Animal {
    protected String species;

    protected int lifetime; //konstruktorban random értéket adni neki
    protected int age;

    protected double foodLevel;
    protected int maxFood;
    protected double waterLevel;
    protected int maxWater;

    protected List<Animal> group;

    public abstract void findWater();
    public abstract void findFood();
    public abstract void eat();
    public abstract void drink();
    public abstract void nap();  // esetleg true/false értékkel





}
