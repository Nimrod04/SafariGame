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
public class PathTest {
    
    @Test
    void testConstructor() {
        // Arrange
        Coordinate coordinate = new Coordinate(5, 10);
        
        // Act
        Path path = new Path(coordinate, true);
        
        // Assert
        assertEquals(coordinate, path.coordinate);
    }

    @Test
    void testSetAndGetImagePath() {
        // Arrange
        Path path = new Path(new Coordinate(5, 10), true);
        String expectedPath = "images/path.png";
        
        // Act
        path.setImagePath(expectedPath);
        
        // Assert
        assertEquals(expectedPath, path.getImagePath());
    }

    @Test
    void testGetImagePathWhenNull() {
        // Arrange
        Path path = new Path(new Coordinate(5, 10), true);
        
        // Act & Assert
        assertNull(path.getImagePath());
    }
    
}
