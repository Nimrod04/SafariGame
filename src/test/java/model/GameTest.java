/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nimro
 */
public class GameTest {
    
    
    @Test
    public void testConstructorAndGetters() {
        Game game = new Game(DifficultyLevel.EASY, "TestSafari");
        assertNotNull(game.getVisitors());
        assertNotNull(game.getGameSpeed());
    }

    @Test
    public void testStartJeep_NullSafe() {
        Game game = new Game(DifficultyLevel.EASY, "TestSafari");
        // Nem dobhat kivételt null esetén
        game.startJeep(null);
    }



    @Test
    public void testUpdate_DoesNotThrow() {
        Game game = new Game(DifficultyLevel.EASY, "TestSafari");
        // Csak azt teszteljük, hogy nem dob kivételt
        game.update();
    }
    
}
