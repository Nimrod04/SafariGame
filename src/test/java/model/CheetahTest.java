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
public class CheetahTest {
    
     private Cheetah cheetah;

    @BeforeEach
    void setUp() {
        cheetah = new Cheetah();
    }

    @Test
    void testConstructor() {
        assertNotNull(cheetah);
        assertNull(cheetah.closestHerbivore);
        assertEquals(Double.MAX_VALUE, cheetah.closestDistance);
    }

    @Test
    void testGetPrice() {
        assertEquals(2000, cheetah.getPrice());
        assertEquals(Cheetah.PRICE, cheetah.getPrice());
    }

    @Test
    void testSprint() {
        // Mivel a sprint() metódus üres, csak azt teszteljük, hogy nem dob kivételt
        assertDoesNotThrow(() -> cheetah.sprint());
    }

    @Test
    void testInheritance() {
        assertTrue(cheetah instanceof Carnivore);
        assertTrue(cheetah instanceof Animal);
    }

    @Test
    void testPriceConstant() {
        assertEquals(2000, Cheetah.PRICE);
    }
    
}
