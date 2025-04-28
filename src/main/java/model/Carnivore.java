package model;

import java.util.List;

public abstract class Carnivore extends Animal {
    private List<String> preySpecies;

    public Animal closestHerbivore = null;
    public double closestDistance = Double.MAX_VALUE;

    @Override
    public void eat() {
        foodLevel = 100.0;
        isEating = true;
        lastEatTime = System.currentTimeMillis();
        System.out.println(this.getClass().getSimpleName() + " evett és most pihen.");
    }

    @Override
    public void update(GameSpeed gs, List<Animal> herbivores) {
        if (isThirsty() || isHungry()){
            isEating = false;
        }
        if (isEating) {
            // Ellenőrizzük, hogy eltelt-e 20 másodperc
            if (System.currentTimeMillis() - lastEatTime >= 20000) {
                isEating = false; // Pihenés vége
                System.out.println(this.getClass().getSimpleName() + " újra elindul.");
                closestHerbivore = null;
                closestDistance = Double.MAX_VALUE;
                foodLevel = 100.0;
            } else {
                return; // Az állat nem mozog, amíg pihen
            }
        }
        if (isThirsty() && hasReachedTarget()){
            drink();
        }

        // Normál mozgási logika
        if (isHungry()) {
            hunt(herbivores, gs); // Vadászat logika
        } else if (isThirsty()) {
            findWater(); // Vízkeresés logika
        } else if (isInGroup()) {
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
            generateRandomTarget(); // Véletlenszerű cél generálása
        }
        moveTo(targetCoordinate, gs); // Mozgás a cél felé

        gettingOld(gs);
    }

    private void eatHerbivore(Animal herbivore) {
        herbivore.isAlive = false; // A növényevő meghal
        this.eat(); // A ragadozó evés logikája
        System.out.println(this.getClass().getSimpleName() + " megette a(z) " + herbivore.getClass().getSimpleName() + "-t.");
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
        closestHerbivore = null;
        closestDistance = Double.MAX_VALUE;
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

}
