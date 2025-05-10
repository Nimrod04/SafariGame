package model;

import java.awt.Color;
import java.awt.Graphics;

import static view.GamePanel.TILE_SIZE;

/**
 * A Cheetah osztály a gepárdot reprezentálja a játékban.
 * Leszármazottja a Carnivore osztálynak, és tartalmazza a gepárd specifikus viselkedéseit.
 */
public class Cheetah extends Carnivore {

    /**
     * Létrehoz egy új Cheetah példányt.
     * Inicializálja a legközelebbi növényevőre és távolságra vonatkozó mezőket.
     */
    public Cheetah(){
        super();
        closestHerbivore = null;
        closestDistance = Double.MAX_VALUE;
    }

    /** A gepárd ára. */
    public static final int PRICE = 2000;

    /**
     * Visszaadja a gepárd árát.
     * @return a gepárd ára
     */
    @Override
    public int getPrice() {
        return PRICE;
    }
    
}
