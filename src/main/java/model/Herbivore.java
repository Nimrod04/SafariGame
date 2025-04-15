package model;

import java.util.List;

import static view.GamePanel.TILE_SIZE;

public class Herbivore extends Animal {
    private List<Plant> preferredPlants;

    public void graze() {
        // Legelés logika
    }

    @Override
    public void eat() {
    }

    @Override
    public void drink() {
    }

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
    public void moveTo() {
        System.out.println("x: "+ this.actualCoordinate.getPosX() + "  Y: " +this.actualCoordinate.getPosX());

        if (targetCoordinate == null || hasReachedTarget()) {
            System.out.println("--------------------------------------------------------");
            generateRandomTarget();

        }
        System.out.println("x: "+ this.targetCoordinate.getPosX() + "  Y: " +this.targetCoordinate.getPosX());
        moveTo(targetCoordinate);
    }

    @Override
    public boolean hasReachedTarget() {
        return actualCoordinate.getPosX() == targetCoordinate.getPosX() && actualCoordinate.getPosY() == targetCoordinate.getPosY();
    }

    @Override
    public void generateRandomTarget() {
        int randomX = (int) (Math.random() * 40 * TILE_SIZE); // Adjust map size as needed
        int randomY = (int) (Math.random() * 20 * TILE_SIZE);
        targetCoordinate = new Coordinate(randomX, randomY);
    }

    @Override
    public void moveTo(Coordinate target) {
        int deltaX = target.getPosX() - actualCoordinate.getPosX();
        int deltaY = target.getPosY() - actualCoordinate.getPosY();

        // Egy lépés X és Y irányban
        int stepX = (int) Math.signum(deltaX); // -1, 0 vagy 1
        int stepY = (int) Math.signum(deltaY); // -1, 0 vagy 1

        // Frissítjük az aktuális koordinátát
        actualCoordinate = new Coordinate(
                actualCoordinate.getPosX() + stepX,
                actualCoordinate.getPosY() + stepY
        );
    }

}
