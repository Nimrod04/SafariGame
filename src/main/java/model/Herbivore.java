package model;

import java.util.List;

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
    public void update(GameSpeed gs, List<Animal> herbivores) {
        if (isEating) {
            // Ellenőrizzük, hogy eltelt-e 20 másodperc
            if (System.currentTimeMillis() - lastEatTime >= 20000) {
                isEating = false; // Pihenés vége
                System.out.println(this.getClass().getSimpleName() + " újra elindul.");
                foodLevel = 100.0;
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
        }else if (isInGroup()) {
            Animal leader = group.get(0);
            if (leader != this) {
                int offsetX = (int) (Math.random() * 40 - 20); //eltolás  -20 és 20 között
                int offsetY = (int) (Math.random() * 40 - 20);
                Coordinate targetWithOffset = new Coordinate(
                        leader.targetCoordinate.getPosX() + offsetX,
                        leader.targetCoordinate.getPosY() + offsetY
                );
                moveTo(targetWithOffset,gs);
                return;
            }
        }

        if (targetCoordinate == null || hasReachedTarget()) {
            generateRandomTarget();
        }
        moveTo(targetCoordinate, gs);
        gettingOld(gs);
    }

}
