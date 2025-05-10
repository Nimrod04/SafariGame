package controller;

import model.GameMap;

/**
 * A SurveillanceController osztály a játékban a megfigyelő rendszerek (pl. kamerák, drónok) vezérléséért felelős.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 */
public class SurveillanceController {
    private GameMap gameMap;

    /**
     * Létrehoz egy új SurveillanceController példányt a megadott GameMap-pel.
     * Ez a konstruktor public vagy csomag szintű lehet, hogy a vezérlő példányosítható legyen a megfelelő helyeken.
     * @param gameMap a játék térképe
     */
    SurveillanceController(GameMap gameMap) {
        this.gameMap = gameMap;
    }
}
