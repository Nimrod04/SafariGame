/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Felhasznalo
 */
public class LionTest {
    
     private Lion lion;

    @BeforeEach
    void setUp() {
        lion = new Lion();
    }

    @Test
    void testConstructor() {
        assertNotNull(lion);
        assertNull(lion.closestHerbivore);
        assertEquals(Double.MAX_VALUE, lion.closestDistance);
    }

    @Test
    void testGetPrice() {
        assertEquals(2000, lion.getPrice());
        assertEquals(Lion.PRICE, lion.getPrice());
    }

    @Test
    void testInheritance() {
        assertTrue(lion instanceof Carnivore);
        assertTrue(lion instanceof Animal);
    }

    @Test
    void testPriceConstant() {
        assertEquals(2000, Lion.PRICE);
    }
    
}
