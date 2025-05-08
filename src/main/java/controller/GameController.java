package controller;

import model.GameMap;

public class GameController {
    private GameMap gameMap;
    private SurveillanceController surveillanceController;

    public GameController() {
        surveillanceController = new SurveillanceController(gameMap);
    }
}
