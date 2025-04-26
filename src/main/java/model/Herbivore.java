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
        foodLevel = 100.0;
    }

    @Override
    public boolean nap() {
        return false;
    }

    @Override
    public void moveTo(GameSpeed gs) {
        if (/*isHungry()*/false){
            findFood();
        }else if(isThirsty() && targetCoordinate != null && hasReachedTarget()){
            drink();
            if (!isHungry()){

            }
            System.out.println("növényevő ivott--------------------------------------" + waterLevel);
        }
        else if(isThirsty()){
            findWater();
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
