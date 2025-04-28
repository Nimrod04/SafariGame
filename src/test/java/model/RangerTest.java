/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author nimro
 */
public class RangerTest {
    
    private Ranger ranger;
    private Coordinate position;

    @BeforeEach
    void setUp() {
        position = new Coordinate(10, 10);
        ranger = new Ranger(position);
    }

    @Test
    void testConstructor() {
        assertEquals(position, ranger.getPosition());
        assertEquals(5000, Ranger.PRICE);
        
        Rectangle hitbox = ranger.getHitbox();
        assertNotNull(hitbox);
        assertEquals(6, hitbox.x);  // 10 - HITBOX_RADIUS
        assertEquals(6, hitbox.y);  // 10 - HITBOX_RADIUS
        assertEquals(9, hitbox.width);  // HITBOX_SIZE
        assertEquals(9, hitbox.height); // HITBOX_SIZE
    }

    @Test
    void testSetPosition() {
        Coordinate newPosition = new Coordinate(15, 15);
        ranger.setPosition(newPosition);
        
        assertEquals(newPosition, ranger.getPosition());
        
        Rectangle hitbox = ranger.getHitbox();
        assertEquals(11, hitbox.x);  // 15 - HITBOX_RADIUS
        assertEquals(11, hitbox.y);  // 15 - HITBOX_RADIUS
        assertEquals(9, hitbox.width);  // HITBOX_SIZE
        assertEquals(9, hitbox.height); // HITBOX_SIZE
    }

    @Test
    void testDrawHitbox() {
        // Create mock Graphics object
        Graphics mockGraphics = mock(Graphics.class);
        
        // Test parameters
        int cameraX = 2;
        int cameraY = 2;
        int tileSize = 32;
        
        // Calculate expected values
        int expectedDrawX = (ranger.getHitbox().x - cameraX) * tileSize;
        int expectedDrawY = (ranger.getHitbox().y - cameraY) * tileSize;
        int expectedDrawWidth = ranger.getHitbox().width * tileSize;
        int expectedDrawHeight = ranger.getHitbox().height * tileSize;
        
        // Call the method
        ranger.drawHitbox(mockGraphics, cameraX, cameraY, tileSize);
        
        // Verify the border drawing
        verify(mockGraphics).setColor(new Color(0, 0, 128, 120));
        verify(mockGraphics).drawRect(expectedDrawX, expectedDrawY, expectedDrawWidth, expectedDrawHeight);
        
        // Verify the fill drawing
        verify(mockGraphics).setColor(new Color(161, 251, 166, 60));
        verify(mockGraphics).fillRect(expectedDrawX, expectedDrawY, expectedDrawWidth, expectedDrawHeight);
    }
    
}
