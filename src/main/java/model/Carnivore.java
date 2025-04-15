package model;

import java.util.List;

import static view.GamePanel.TILE_SIZE;

public class Carnivore extends Animal {
    private List<String> preySpecies;

    public void hunt() {
        // Vadászat logika
    }
    @Override
    public void eat(){}
    @Override
    public void drink(){}

    @Override
    public void findWater() {

    }

    @Override
    public void findFood() {

    }

    @Override
    public void nap() {

    }

    @Override
    public void moveTo(){
        if (targetCoordinate == null || hasReachedTarget()) {
            generateRandomTarget();
        }
        moveTo(targetCoordinate);
    }

    @Override
    public boolean hasReachedTarget() {
        return actualCoordinate.getPosX() == targetCoordinate.getPosX() &&
                actualCoordinate.getPosY() == targetCoordinate.getPosY();
    }

    @Override
    public void generateRandomTarget() {
        int randomX = (int) (Math.random() * 40 * TILE_SIZE); // Adjust map size as needed
        int randomY = (int) (Math.random() * 20 * TILE_SIZE);
        targetCoordinate = new Coordinate(randomX, randomY);
    }

    @Override
    public void moveTo(Coordinate target){

    };

}
