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
        System.out.println("eat------------------------------------");
    }

    @Override
    public boolean nap() {
        return false;
    }

    @Override
    public void moveTo(GameSpeed gs) {
        if(isHungry() && targetCoordinate != null && hasReachedTarget()){
            nap = false;
            eat();
            if (!isThirsty()){
                nap = true;
            }
            System.out.println("növényevő ivott--------------------------------------" + waterLevel);
        }else if (/*isHungry()*/false){
            nap = false;
            findFood();
        }else if(isThirsty() && targetCoordinate != null && hasReachedTarget()){
            nap = false;
            drink();
            if (!isHungry()){
                nap = true;
            }
            System.out.println("növényevő ivott--------------------------------------" + waterLevel);
        }
        else if(isThirsty()){
            nap = false;
            findWater();
        }else if(nap){
            napTime++;
            if (napTime == 10000){
                nap = false;
            }
        }
        else if (isInGroup()) {
            Animal leader = group.get(0);
            if (leader != this) {
                int offsetX = (int) (Math.random() * 40 - 20); //eltolás  -20 és 20 között
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
        moveTo(targetCoordinate,gs);

    }

}
