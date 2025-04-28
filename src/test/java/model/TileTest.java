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
public class TileTest {
    
    @Test
    void testConstructor() {
        // Arrange & Act
        Tile tile = new Tile(Tile.TileType.GRASS);
        
        // Assert
        assertEquals(Tile.TileType.GRASS, tile.getType());
    }

    @Test
    void testSetAndGetType() {
        // Arrange
        Tile tile = new Tile(Tile.TileType.GRASS);
        
        // Act
        tile.setType(Tile.TileType.WATER);
        
        // Assert
        assertEquals(Tile.TileType.WATER, tile.getType());
    }

    @Test
    void testAllTileTypes() {
        // Test all enum values
        for (Tile.TileType type : Tile.TileType.values()) {
            // Arrange & Act
            Tile tile = new Tile(type);
            
            // Assert
            assertEquals(type, tile.getType());
        }
    }
    
}
