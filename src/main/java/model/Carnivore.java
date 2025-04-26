package model;

import java.util.List;

import static view.GamePanel.TILE_SIZE;

public abstract class Carnivore extends Animal {
    private List<String> preySpecies;



    public void hunt() {
        // Vadászat logika
    }

    @Override
    public void eat() {
    }



    @Override
    public boolean nap() {
        return false;
    }



}
