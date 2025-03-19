package model;

import java.util.List;

public abstract class Animal {
    protected String species;
    protected double hungerLevel;
    protected double thirstLevel;
    protected List<Animal> group;

    public abstract void eat();
    public abstract void drink();
}
