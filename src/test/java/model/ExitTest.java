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
public class ExitTest {
    
    @Test
    void testConstructor() {
        // Arrange
        Coordinate expectedCoordinate = new Coordinate(5, 10);

        // Act
        Exit exit = new Exit(expectedCoordinate);

        // Assert
        assertEquals(expectedCoordinate, exit.coordinate);
    }

    @Test
    void testConstructorWithNullCoordinate() {
        // Act & Assert
        assertDoesNotThrow(() -> new Exit(null));
    }
}
