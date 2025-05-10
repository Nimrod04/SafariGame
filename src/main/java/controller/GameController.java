package controller;

import model.GameMap;

/**
 * A GameController osztály a játék fő vezérlője.
 * Felelős a játék fő logikájának irányításáért, a GameMap és a SurveillanceController példányok kezeléséért.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 */
public class GameController {
    private GameMap gameMap;
    private SurveillanceController surveillanceController;

    /**
     * Alapértelmezett konstruktor a GameController osztályhoz.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     */
    public GameController() {
        surveillanceController = new SurveillanceController(gameMap);
    }
}
