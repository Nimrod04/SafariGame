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
public class DifficultyLevelTest {
    
    @Test
    void testEnumValues() {
        // Test that all enum values exist
        DifficultyLevel[] levels = DifficultyLevel.values();
        
        assertEquals(3, levels.length);
        assertEquals(DifficultyLevel.EASY, levels[0]);
        assertEquals(DifficultyLevel.MEDIUM, levels[1]);
        assertEquals(DifficultyLevel.HARD, levels[2]);
    }

    @Test
    void testEnumValueOf() {
        // Test valueOf method for each difficulty level
        assertEquals(DifficultyLevel.EASY, DifficultyLevel.valueOf("EASY"));
        assertEquals(DifficultyLevel.MEDIUM, DifficultyLevel.valueOf("MEDIUM"));
        assertEquals(DifficultyLevel.HARD, DifficultyLevel.valueOf("HARD"));
    }
    
}
