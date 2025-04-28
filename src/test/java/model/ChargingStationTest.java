/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author nimro
 */
public class ChargingStationTest {
    
    private Camera camera;
    private Coordinate initialPosition;

    @BeforeEach
    void setUp() {
        initialPosition = new Coordinate(5, 5);
        camera = new Camera(initialPosition);
    }

    @Test
    void testConstructor() {
        assertEquals(initialPosition, camera.getPosition());
        Rectangle hitbox = camera.getHitbox();
        assertEquals(3, hitbox.x); // 5 - 2 (HITBOX_RADIUS)
        assertEquals(3, hitbox.y); // 5 - 2 (HITBOX_RADIUS)
        assertEquals(5, hitbox.width); // HITBOX_SIZE
        assertEquals(5, hitbox.height); // HITBOX_SIZE
    }

    @Test
    void testSetPosition() {
        Coordinate newPosition = new Coordinate(10, 10);
        camera.setPosition(newPosition);
        assertEquals(newPosition, camera.getPosition());

        Rectangle hitbox = camera.getHitbox();
        assertEquals(8, hitbox.x); // 10 - 2 (HITBOX_RADIUS)
        assertEquals(8, hitbox.y); // 10 - 2 (HITBOX_RADIUS)
        assertEquals(5, hitbox.width); // HITBOX_SIZE
        assertEquals(5, hitbox.height); // HITBOX_SIZE
    }
    @Test
    void testSetPosition2() {
        // Arrange
        Coordinate initialPosition = new Coordinate(5, 10);
        ChargingStation chargingStation = new ChargingStation(initialPosition);

        Coordinate newPosition = new Coordinate(15, 20);

        // Act
        chargingStation.setPosition(newPosition);

        // Assert
        assertEquals(newPosition, chargingStation.getPosition());
    }

    @Test
    void testDrawHitbox() {
        // Ellenőrizzük, hogy a drawHitbox metódus nem dob hibát
        Graphics g = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).getGraphics();
        camera.drawHitbox(g, 0, 0, 10);
    }
    
}
