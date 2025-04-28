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
public class CoordinateTest {
    
    @Test
    void testConstructorAndGetters() {
        // Arrange & Act
        Coordinate coordinate = new Coordinate(5, 10);

        // Assert
        assertEquals(5, coordinate.getPosX());
        assertEquals(10, coordinate.getPosY());
    }

    @Test
    void testSetters() {
        // Arrange
        Coordinate coordinate = new Coordinate(0, 0);

        // Act
        coordinate.setPosX(15);
        coordinate.setPosY(25);

        // Assert
        assertEquals(15, coordinate.getPosX());
        assertEquals(25, coordinate.getPosY());
    }

    @Test
    void testEquals() {
        // Arrange
        Coordinate coord1 = new Coordinate(5, 10);
        Coordinate coord2 = new Coordinate(5, 10);
        Coordinate coord3 = new Coordinate(6, 10);
        
        // Assert
        assertTrue(coord1.equals(coord1)); // Same object
        assertTrue(coord1.equals(coord2)); // Equal coordinates
        assertFalse(coord1.equals(coord3)); // Different x
        assertFalse(coord1.equals(null)); // Null comparison
        assertFalse(coord1.equals("Not a Coordinate")); // Different type
    }

    @Test
    void testHashCode() {
        // Arrange
        Coordinate coord1 = new Coordinate(5, 10);
        Coordinate coord2 = new Coordinate(5, 10);
        Coordinate coord3 = new Coordinate(6, 10);

        // Assert
        assertEquals(coord1.hashCode(), coord2.hashCode()); // Equal coordinates should have equal hash codes
        assertNotEquals(coord1.hashCode(), coord3.hashCode()); // Different coordinates should have different hash codes
        assertEquals(31 * 5 + 10, coord1.hashCode()); // Verify hash code calculation
    }
    
}
