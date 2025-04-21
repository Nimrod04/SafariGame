/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author nimro
 */
public class CameraTest {

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
        assertNotNull(hitbox);
        assertEquals(3, hitbox.x); // 5 - 2 (HITBOX_RADIUS)
        assertEquals(3, hitbox.y); // 5 - 2 (HITBOX_RADIUS)
        assertEquals(5, hitbox.width); // HITBOX_SIZE (2 * 2 + 1)
        assertEquals(5, hitbox.height); // HITBOX_SIZE (2 * 2 + 1)
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

}
