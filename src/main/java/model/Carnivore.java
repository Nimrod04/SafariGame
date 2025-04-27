package model;

import java.util.List;

import static view.GamePanel.TILE_SIZE;

public abstract class Carnivore extends Animal {
    private List<String> preySpecies;

    public Animal closestHerbivore = null;
    public double closestDistance = Double.MAX_VALUE;

    @Override
    public void eat() {
        foodLevel = 100.0;
        System.out.println("Ragadozó evett------------------------------------");
    }

    @Override
    public boolean nap() {
        return false;
    }

    public void hunt(List<Animal> herbivores, GameSpeed gs) {
        if (herbivores == null || herbivores.isEmpty()) {
            generateRandomTarget(); // Ha nincs növényevő, mozogjon véletlenszerűen
            return;
        }

        for (Animal herbivore : herbivores) {
            if (!herbivore.isAlive)
                continue; // Csak az élő növényevők számítanak
            double distance = this.distanceTo(herbivore);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestHerbivore = herbivore;
            }
        }

        if (closestHerbivore == null) {
            generateRandomTarget();
            return;
        }

        targetCoordinate = closestHerbivore.getCoordinate();
        moveTo(targetCoordinate, gs);

        if (hasReachedTarget()) {
            eatHerbivore(closestHerbivore);
        }
    }

    private void eatHerbivore(Animal herbivore) {
        herbivore.isAlive = false; // A növényevő meghal
        this.foodLevel = 100.0; // A ragadozó éhségszintje feltöltődik
        System.out.println(
                this.getClass().getSimpleName() + " megette a(z) " + herbivore.getClass().getSimpleName() + "-t.");
    }

    public void moveTo(GameSpeed gs, List<Animal> herbivores) {
        waterLevel = 100.0;
        if (isHungry()) {
            System.out.println("ÉHEEES-----");
            hunt(herbivores, gs);
            System.out.println("Cél: " + targetCoordinate.getPosY() + " " + targetCoordinate.getPosY());
        } else if (isThirsty()) {
            findWater();
        } else if (isInGroup()) {
            // Csoportkövetési logika
            Animal leader = group.get(0);
            if (leader != this) {
                int offsetX = (int) (Math.random() * 40 - 20);
                int offsetY = (int) (Math.random() * 40 - 20);
                Coordinate targetWithOffset = new Coordinate(
                    leader.actualCoordinate.getPosX() + offsetX,
                    leader.actualCoordinate.getPosY() + offsetY
                );
                moveTo(targetWithOffset, gs);
                return;
            }
        }
        if (targetCoordinate == null || hasReachedTarget()) {
            generateRandomTarget();
        }
        moveTo(targetCoordinate, gs);
    }

}
