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
    public void moveTo(GameSpeed gs) {

        if (isInGroup()) {
            Animal leader = group.get(0);
            if (leader != this) {
                int offsetX = (int) (Math.random() * 40 - 20); // -10 és 10 között
                int offsetY = (int) (Math.random() * 40 - 20);
                Coordinate targetWithOffset = new Coordinate(
                        leader.targetCoordinate.getPosX() + offsetX,
                        leader.targetCoordinate.getPosY() + offsetY
                );
                moveTo(targetWithOffset,gs);
                return;
            }
        }

        // Ha nincs célkoordináta, vagy elérte a célját, generáljon újat
        if (targetCoordinate == null || hasReachedTarget()) {
            generateRandomTarget();
        }
        moveTo(targetCoordinate,gs);
    }

    @Override
    public boolean hasReachedTarget() {
        int dx = targetCoordinate.getPosX() - actualCoordinate.getPosX();
        int dy = targetCoordinate.getPosY() - actualCoordinate.getPosY();
        return Math.sqrt(dx * dx + dy * dy) < 5;
    }

    @Override
    public void generateRandomTarget() {
        int randomX = (int) (Math.random() * 40 * TILE_SIZE); // Adjust map size as needed
        int randomY = (int) (Math.random() * 20 * TILE_SIZE);
        targetCoordinate = new Coordinate(randomX, randomY);
    }

    @Override
    public void moveTo(Coordinate target, GameSpeed gs) {
        int speedInPx = (int) (gs.getMulti() * 1); // A játék sebességéhez igazítva
        //int speedInPx = (int) (baseSpeed * Game.getGameSpeed().getMulti()); // Sebesség a játék sebességéhez igazítva

        int deltaX = target.getPosX() - actualCoordinate.getPosX();
        int deltaY = target.getPosY() - actualCoordinate.getPosY();

        int stepX = (int) Math.signum(deltaX); // -1, 0 vagy 1
        int stepY = (int) Math.signum(deltaY); // -1, 0 vagy 1

        int nextX = actualCoordinate.getPosX() + stepX * speedInPx;
        int nextY = actualCoordinate.getPosY() + stepY * speedInPx;

        if (Math.abs(deltaX) <= Math.abs(stepX * speedInPx)) {
            nextX = target.getPosX();
        }
        if (Math.abs(deltaY) <= Math.abs(stepY * speedInPx)) {
            nextY = target.getPosY();
        }

        actualCoordinate = new Coordinate(nextX, nextY);

        int actTileX = nextX/TILE_SIZE;
        int actTileY = nextY/TILE_SIZE ;
        addVisitedLocation(actTileX, actTileY);
        //System.out.println(this.getClass().getSimpleName() + " aktuális pozíciója: (" + actTileX + ", " + actTileY + ")");
    }

    @Override
    public boolean hasVisited(int x, int y) {
        for (int[] location : visitedLocations) {
            if (location[0] == x && location[1] == y) {
                return true; // Már járt itt
            }
        }
        return false; // Még nem járt itt
    }

}
