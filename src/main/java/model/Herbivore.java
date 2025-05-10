package model;

import java.util.List;

/**
 * A Herbivore absztrakt osztály a növényevő állatok közös viselkedését és tulajdonságait írja le.
 * Leszármazottja az Animal osztálynak. Képes legelni, enni, pihenni, vizet keresni, és csoportban mozogni.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 * <p>
 * Ha nincs explicit konstruktor, a fordító automatikusan létrehozza a public alapértelmezett konstruktort.
 */
public abstract class Herbivore extends Animal {
    private List<Plant> preferredPlants;

    /**
     * Alapértelmezett konstruktor a Herbivore osztályhoz.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     */
    public Herbivore() {
        super();
    }

    /**
     * A növényevő legelésének logikája.
     * Ez a metódus public lehet, hogy más osztályokból is meghívható legyen, például a játék logikájából vagy a grafikus felületről.
     */
    public void graze() {
        // Legelés logika
    }

    /**
     * A növényevő evésének logikája: feltölti az élelem szintet, pihenni kezd, és eltárolja az evés idejét.
     * Ez a metódus public, hogy más osztályok is felülírhassák vagy meghívhassák.
     */
    @Override
    public void eat() {
        foodLevel = 100.0;
        isEating = true;
        lastEatTime = System.currentTimeMillis();
        System.out.println(this.getClass().getSimpleName() + " evett és most pihen.");
    }

    /**
     * Frissíti a növényevő állapotát: kezeli az evést, pihenést, szomjúságot, legelést, csoportos mozgást és öregedést.
     * Ez a metódus public, hogy más osztályok is felülírhassák vagy meghívhassák.
     * @param gs a játék sebessége
     * @param herbivores a növényevők listája
     */
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
        } else if (isInGroup()) {
            Animal leader = group.get(0);
            if (leader != this) {
                int offsetX = (int) (Math.random() * 40 - 20); // eltolás  -20 és 20 között
                int offsetY = (int) (Math.random() * 40 - 20);
                Coordinate targetWithOffset = new Coordinate(
                        leader.targetCoordinate.getPosX() + offsetX,
                        leader.targetCoordinate.getPosY() + offsetY
                );
                moveTo(targetWithOffset, gs);
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
