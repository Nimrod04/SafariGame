/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author nimro
 */
public class GrassTest {
    
    private Grass grass;
    private Coordinate coordinate;

    @BeforeEach
    void setUp() {
        coordinate = new Coordinate(5, 10);
        grass = new Grass(coordinate);
    }

    @Test
    void testConstructor() {
        // Test constructor and coordinate initialization
        assertEquals(coordinate, grass.coordinate);
        assertEquals(500, Grass.PRICE);
    }


    @Test
    void testGrow() {
        // Test that grow method doesn't throw exception
        assertDoesNotThrow(() -> grass.grow());
    }
    
}
