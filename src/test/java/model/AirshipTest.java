/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import java.awt.Rectangle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author nimro
 */
public class AirshipTest {

    private Airship airship;
    private GameSpeed gameSpeed;
    private Coordinate initialPosition;

    @BeforeEach
    void setUp() {
        gameSpeed = new GameSpeed();
        initialPosition = new Coordinate(5, 5);
        airship = new Airship(initialPosition, gameSpeed);
    }

    @Test
    void testConstructor() {
        assertEquals(initialPosition, airship.getPosition());
        assertFalse(airship.isSelected());
        assertFalse(airship.isMoving());
        assertTrue(airship.getWaypoints().isEmpty());
    }

    @Test
    void testSetPosition() {
        Coordinate newPosition = new Coordinate(10, 10);
        airship.setPosition(newPosition);
        assertEquals(newPosition, airship.getPosition());

        Rectangle hitbox = airship.getHitbox();
        assertEquals(8, hitbox.x);
        assertEquals(8, hitbox.y);
        assertEquals(5, hitbox.width);
        assertEquals(5, hitbox.height);
    }

    @Test
    void testWaypointOperations() {
        assertTrue(airship.getWaypoints().isEmpty());

        Coordinate waypoint = new Coordinate(6, 6);
        airship.addWaypoint(waypoint);

        assertEquals(1, airship.getWaypoints().size());
        assertTrue(airship.isWaypoint(6, 6));
        assertFalse(airship.isWaypoint(7, 7));

        airship.clearWaypoints();
        assertTrue(airship.getWaypoints().isEmpty());
    }

    @Test
    void testBasicMovement() {
        airship.setMoving(true);
        airship.addWaypoint(new Coordinate(6, 5));

        // First movement
        airship.moveToNextWaypoint();
        assertEquals(6, airship.getPosition().getPosX());
        assertEquals(5, airship.getPosition().getPosY());

        // Should not move due to delay
        airship.moveToNextWaypoint();
        assertEquals(6, airship.getPosition().getPosX());
        assertEquals(5, airship.getPosition().getPosY());
    }

    @Test
    void testMovementWithDelay() throws InterruptedException {
        airship.setMoving(true);
        airship.addWaypoint(new Coordinate(6, 5));

        airship.moveToNextWaypoint();
        Thread.sleep(334); // Wait for move delay

        airship.moveToNextWaypoint();
        assertEquals(6, airship.getPosition().getPosX());
        assertEquals(5, airship.getPosition().getPosY());
    }

    @Test
    void testSelectionState() {
        assertFalse(airship.isSelected());

        airship.setSelected(true);
        assertTrue(airship.isSelected());

        airship.setSelected(false);
        assertFalse(airship.isSelected());
    }

    @Test
    void testHitboxCalculation() {
        Rectangle hitbox = airship.getHitbox();
        assertEquals(3, hitbox.x); // 5 - 2 (HITBOX_RADIUS)
        assertEquals(3, hitbox.y); // 5 - 2 (HITBOX_RADIUS)
        assertEquals(5, hitbox.width); // HITBOX_SIZE
        assertEquals(5, hitbox.height); // HITBOX_SIZE
    }

    @Test
    void testGameSpeedEffect() throws InterruptedException {
        airship.setMoving(true);
        airship.addWaypoint(new Coordinate(7, 5));

        // Normal speed movement
        airship.moveToNextWaypoint();
        assertEquals(6, airship.getPosition().getPosX());

        // Change game speed
        gameSpeed.changeGameSpeed(5);
        Thread.sleep(67); // (333/5 + 1) ms delay

        airship.moveToNextWaypoint();
        assertEquals(7, airship.getPosition().getPosX());
    }

    @Test
    void testNoMovementWithoutWaypoints() {
        airship.setMoving(true);
        Coordinate initialPos = airship.getPosition();

        airship.moveToNextWaypoint();

        assertEquals(initialPos, airship.getPosition());
    }

    @Test
    void testNoMovementWhenNotMoving() {
        airship.setMoving(false);
        airship.addWaypoint(new Coordinate(6, 5));
        Coordinate initialPos = airship.getPosition();

        airship.moveToNextWaypoint();

        assertEquals(initialPos, airship.getPosition());
    }
}
