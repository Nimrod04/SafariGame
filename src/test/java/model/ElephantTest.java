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
public class ElephantTest {
    
     private Elephant elephant;

    @BeforeEach
    void setUp() {
        elephant = new Elephant();
    }

    @Test
    void testConstructor() {
        assertNotNull(elephant);
    }

    @Test
    void testGetPrice() {
        assertEquals(1000, elephant.getPrice());
        assertEquals(Elephant.PRICE, elephant.getPrice());
    }

    @Test
    void testUseTrunk() {
        // Mivel a useTrunk() metódus üres, csak azt teszteljük, hogy nem dob kivételt
        assertDoesNotThrow(() -> elephant.useTrunk());
    }

    @Test
    void testInheritance() {
        assertTrue(elephant instanceof Herbivore);
        assertTrue(elephant instanceof Animal);
    }

    @Test
    void testPriceConstant() {
        assertEquals(1000, Elephant.PRICE);
    }
    
}
