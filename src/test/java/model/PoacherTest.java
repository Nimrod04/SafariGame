/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author nimro
 */
public class PoacherTest {
    
    private Poacher poacher;
    private Animal mockAnimal;

    @BeforeEach
    void setUp() {
        poacher = new Poacher();
        mockAnimal = mock(Animal.class);
    }



    @Test
    void testHuntAnimal() {
        // Ensure method doesn't throw exception
        assertDoesNotThrow(() -> poacher.huntAnimal(mockAnimal));
    }

    @Test
    void testMoveTo() {
        Coordinate target = new Coordinate(5, 5);
        assertDoesNotThrow(() -> poacher.moveTo(target));
    }

    @Test
    void testMove() {
        assertDoesNotThrow(() -> poacher.move());
    }
    
}
