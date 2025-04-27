package model;

import java.util.ArrayList;

import static view.GamePanel.TILE_SIZE;

public class Elephant extends Herbivore {



    public static final int PRICE = 1000;

    public void useTrunk() {
        // Elefánt ormány használata
    }

    @Override
    public int getPrice() {
        return PRICE;
    }
}
