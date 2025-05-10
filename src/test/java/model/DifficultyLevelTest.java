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
    public void testEasyDifficultyValues() {
        assertEquals(5, DifficultyLevel.EASY.getReqHerb());
        assertEquals(5, DifficultyLevel.EASY.getReqCarn());
        assertEquals(50000, DifficultyLevel.EASY.getReqMoney());
        assertEquals(25, DifficultyLevel.EASY.getReqVisitor());
        assertEquals(1, DifficultyLevel.EASY.getReqJeep());
    }

    @Test
    public void testMediumDifficultyValues() {
        assertEquals(10, DifficultyLevel.MEDIUM.getReqHerb());
        assertEquals(10, DifficultyLevel.MEDIUM.getReqCarn());
        assertEquals(150000, DifficultyLevel.MEDIUM.getReqMoney());
        assertEquals(100, DifficultyLevel.MEDIUM.getReqVisitor());
        assertEquals(2, DifficultyLevel.MEDIUM.getReqJeep());
    }

    @Test
    public void testHardDifficultyValues() {
        assertEquals(25, DifficultyLevel.HARD.getReqHerb());
        assertEquals(25, DifficultyLevel.HARD.getReqCarn());
        assertEquals(300000, DifficultyLevel.HARD.getReqMoney());
        assertEquals(500, DifficultyLevel.HARD.getReqVisitor());
        assertEquals(3, DifficultyLevel.HARD.getReqJeep());
    }

    @Test
    public void testEnumValues() {
        DifficultyLevel[] levels = DifficultyLevel.values();
        assertEquals(3, levels.length);
        assertEquals(DifficultyLevel.EASY, levels[0]);
        assertEquals(DifficultyLevel.MEDIUM, levels[1]);
        assertEquals(DifficultyLevel.HARD, levels[2]);
    }
    
}
