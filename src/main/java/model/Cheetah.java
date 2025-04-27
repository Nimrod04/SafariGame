package model;

import java.awt.Color;
import java.awt.Graphics;

import static view.GamePanel.TILE_SIZE;

public class Cheetah extends Carnivore {

    public Cheetah(){
        super();
        closestHerbivore = null;
        closestDistance = Double.MAX_VALUE;
    }

    public static final int PRICE = 2000;
    public void sprint() {
        // Gepárd gyors mozgás
    }
    
}
