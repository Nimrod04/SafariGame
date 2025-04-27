package model;

import java.util.List;

import static view.GamePanel.TILE_SIZE;

public abstract class Herbivore extends Animal {
    private List<Plant> preferredPlants;

    public void graze() {
        // Legelés logika
    }

    @Override
    public void eat() {
        foodLevel = 100.0;
        isEating = true;
        lastEatTime = System.currentTimeMillis();
        System.out.println(this.getClass().getSimpleName() + " evett és most pihen.");
    }

    @Override
    public void moveTo(GameSpeed gs, List<Animal> herbivores) {
        if (isEating) {
            // Ellenőrizzük, hogy eltelt-e 20 másodperc
            if (System.currentTimeMillis() - lastEatTime >= 20000) {
                isEating = false; // Pihenés vége
                System.out.println(this.getClass().getSimpleName() + " újra elindul.");
            } else {
                return; // Az állat nem mozog, amíg pihen
            }
        }

        // Normál mozgási logika
        if (isHungry() && targetCoordinate != null && hasReachedTarget()) {
            eat();
        } else if (isThirsty() && targetCoordinate != null && hasReachedTarget()) {
            drink();
            System.out.println(this.getClass().getSimpleName() + " ivott és most pihen.");
        } else if (isThirsty()) {
            findWater();
        } else if (targetCoordinate == null || hasReachedTarget()) {
            generateRandomTarget();
        }
        moveTo(targetCoordinate, gs);
    }

}
