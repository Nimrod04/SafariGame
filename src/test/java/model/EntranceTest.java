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
public class EntranceTest {
    
    @Test
    void testConstructor() {
        // Arrange
        Coordinate expectedCoordinate = new Coordinate(5, 10);

        // Act
        Entrance entrance = new Entrance(expectedCoordinate);

        // Assert
        assertEquals(expectedCoordinate, entrance.coordinate);
    }

    @Test
    void testConstructorWithNullCoordinate() {
        // Act & Assert
        assertDoesNotThrow(() -> new Entrance(null));
    }
    
}
