package model;

import static view.GamePanel.TILE_SIZE;

public class Lion extends Carnivore {

    public Lion(){
        super();
        closestHerbivore = null;
        closestDistance = Double.MAX_VALUE;
    }

    public static final int PRICE = 2000;




}
