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
public class GazelleTest {
    
   private Gazelle gazelle;

    @BeforeEach
    void setUp() {
        gazelle = new Gazelle();
    }

    @Test
    void testConstructor() {
        assertNotNull(gazelle);
    }

    @Test
    void testGetPrice() {
        assertEquals(1000, gazelle.getPrice());
        assertEquals(Gazelle.PRICE, gazelle.getPrice());
    }

    @Test
    void testFlee() {
        
        assertDoesNotThrow(() -> gazelle.flee());
    }

    @Test
    void testInheritance() {
        assertTrue(gazelle instanceof Herbivore);
        assertTrue(gazelle instanceof Animal);
    }

    @Test
    void testPriceConstant() {
        assertEquals(1000, Gazelle.PRICE);
    }
    
}
