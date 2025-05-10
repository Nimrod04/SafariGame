package model;

import java.util.List;

/**
 * A Carnivore absztrakt osztály a ragadozó állatok közös viselkedését és tulajdonságait írja le.
 * Leszármazottja az Animal osztálynak. Képes vadászni, enni, pihenni, és csoportban mozogni.
 */
public abstract class Carnivore extends Animal {
    /** A ragadozó által elfogyasztható fajok listája. */
    private List<String> preySpecies;

    /**
     * A legközelebbi növényevő példány.
     * Ez a mező public, hogy más osztályok is közvetlenül elérhessék a ragadozó által követett növényevőt, például a játék logikája vagy a grafikus felület.
     */
    public Animal closestHerbivore = null;

    /**
     * A legközelebbi növényevő távolsága.
     * Ez a mező public, hogy más osztályok is közvetlenül elérhessék a ragadozó és a célpont közötti távolságot, például a játék logikája vagy a grafikus felület.
     */
    public double closestDistance = Double.MAX_VALUE;

    /**
     * Alapértelmezett konstruktor a Carnivore osztályhoz.
     * Ez a konstruktor public vagy protected lehet, hogy a leszármazott osztályok példányosíthassák a Carnivore-t.
     * Ha nincs explicit konstruktor, a fordító automatikusan létrehozza az alapértelmezett konstruktort.
     */
    public Carnivore() {
        super();
    }

    /**
     * A ragadozó evésének logikája: feltölti az élelem szintet, pihenni kezd, és eltárolja az evés idejét.
     */
    @Override
    public void eat() {
        foodLevel = 100.0;
        isEating = true;
        lastEatTime = System.currentTimeMillis();
        System.out.println(this.getClass().getSimpleName() + " evett és most pihen.");
    }

    /**
     * Frissíti a ragadozó állapotát: kezeli az evést, pihenést, szomjúságot, vadászatot, csoportos mozgást és öregedést.
     * @param gs a játék sebessége
     * @param herbivores a növényevők listája
     */
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

    /**
     * A ragadozó megeszi a növényevőt: a növényevő meghal, a ragadozó eszik.
     * @param herbivore a megevett növényevő példány
     */
    private void eatHerbivore(Animal herbivore) {
        herbivore.isAlive = false; // A növényevő meghal
        this.eat(); // A ragadozó evés logikája
        System.out.println(this.getClass().getSimpleName() + " megette a(z) " + herbivore.getClass().getSimpleName() + "-t.");
    }

    /**
     * A ragadozó nem alszik (alapértelmezett implementáció).
     * Ez a metódus public, mert a leszármazott osztályok felülírhatják, illetve kívülről is meghívható, ha szükséges.
     * @return mindig false
     */
    @Override
    public boolean nap() {
        return false;
    }

    /**
     * A ragadozó vadászatának logikája: megkeresi a legközelebbi élő növényevőt, követi, és ha eléri, megeszi.
     * Ha nincs növényevő, véletlenszerűen mozog.
     * Ez a metódus public, hogy más osztályokból is meghívható legyen, például a játék logikájából.
     * @param herbivores a növényevők listája
     * @param gs a játék sebessége
     */
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
