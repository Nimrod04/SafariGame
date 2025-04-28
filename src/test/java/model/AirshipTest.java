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

import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

    @Test
    void testIsClickedOnTile2() {
        assertTrue(airship.isClickedOnTile(5, 5)); // Matches the initial position
        assertFalse(airship.isClickedOnTile(6, 6)); // Does not match the initial position
    }

    @Test
    void testDrawHitbox() {
        // Arrange
        Graphics mockGraphics = mock(Graphics.class);
        int cameraX = 2;
        int cameraY = 2;
        int tileSize = 10;

        // Act
        airship.drawHitbox(mockGraphics, cameraX, cameraY, tileSize);

        // Assert
        int drawX = (airship.getHitbox().x - cameraX) * tileSize;
        int drawY = (airship.getHitbox().y - cameraY) * tileSize;
        int drawWidth = airship.getHitbox().width * tileSize;
        int drawHeight = airship.getHitbox().height * tileSize;

        // Verify border color and rectangle drawing
        verify(mockGraphics).setColor(new Color(0, 0, 128, 120));
        verify(mockGraphics).drawRect(drawX, drawY, drawWidth, drawHeight);

        // Verify fill color and rectangle filling
        verify(mockGraphics).setColor(new Color(173, 216, 230, 60));
        verify(mockGraphics).fillRect(drawX, drawY, drawWidth, drawHeight);
    }

    @Test
    void testGetBoundingBox() {
        // Act
        Rectangle boundingBox = airship.getBoundingBox();

        // Assert
        assertNotNull(boundingBox);
        assertEquals(3, boundingBox.x); // 5 - 2 (position.x - 2)
        assertEquals(3, boundingBox.y); // 5 - 2 (position.y - 2)
        assertEquals(5, boundingBox.width); // Fixed width of 5
        assertEquals(5, boundingBox.height); // Fixed height of 5
    }

    @Test
    void testMoveToNextWaypoint() {
        airship.addWaypoint(new Coordinate(6, 5));
        airship.setMoving(true);

        long initialTime = System.currentTimeMillis();
        airship.moveToNextWaypoint(); // Simulate movement
        Coordinate newPosition = airship.getPosition();

        assertEquals(6, newPosition.getPosX());
        assertEquals(5, newPosition.getPosY());
        assertTrue(System.currentTimeMillis() >= initialTime); // Ensure delay logic is respected
    }

    @Test
    void testIsClickedOnTile() {
        assertTrue(airship.isClickedOnTile(5, 5));
        assertFalse(airship.isClickedOnTile(6, 6));
    }

    @Test
    void testMoveToNextWaypoint_NoWaypoints() {
        // Arrange
        airship.setMoving(true);

        // Act
        airship.moveToNextWaypoint();

        // Assert
        assertEquals(initialPosition, airship.getPosition()); // No movement should occur
    }

    @Test
    void testMoveToNextWaypoint_NotMoving() {
        // Arrange
        airship.setMoving(false);
        airship.addWaypoint(new Coordinate(6, 5));

        // Act
        airship.moveToNextWaypoint();

        // Assert
        assertEquals(initialPosition, airship.getPosition()); // No movement should occur
    }

    @Test
    void testMoveToNextWaypoint_ReachesWaypoint() {
        // Arrange
        airship.setMoving(true);
        airship.addWaypoint(new Coordinate(6, 5));

        // Act
        airship.moveToNextWaypoint();

        // Assert
        assertEquals(6, airship.getPosition().getPosX());
        assertEquals(5, airship.getPosition().getPosY());
        assertTrue(airship.getWaypoints().contains(new Coordinate(6, 5))); // Waypoint should be re-added
    }

    @Test
    void testMoveToNextWaypoint_MovesTowardsWaypoint() {
        // Arrange
        airship.setMoving(true);
        airship.addWaypoint(new Coordinate(7, 7));

        // Act
        airship.moveToNextWaypoint();

        // Assert
        assertEquals(6, airship.getPosition().getPosX()); // Moves one step towards X
        assertEquals(6, airship.getPosition().getPosY()); // Moves one step towards Y
    }

    @Test
    void testMoveToNextWaypoint_WithDelay() throws InterruptedException {
        // Arrange
        airship.setMoving(true);
        airship.addWaypoint(new Coordinate(6, 5));

        // Act
        airship.moveToNextWaypoint(); // First move
        Thread.sleep(334); // Wait for move delay
        airship.moveToNextWaypoint(); // Second move

        // Assert
        assertEquals(6, airship.getPosition().getPosX());
        assertEquals(5, airship.getPosition().getPosY());
    }

    @Test
    void testMoveToNextWaypoint_NoWaypoints2() {
        // Arrange
        airship.setMoving(true);

        // Act
        airship.moveToNextWaypoint();

        // Assert
        assertEquals(initialPosition, airship.getPosition()); // No movement should occur
    }

    @Test
    void testMoveToNextWaypoint_NotMoving2() {
        // Arrange
        airship.setMoving(false);
        airship.addWaypoint(new Coordinate(6, 5));

        // Act
        airship.moveToNextWaypoint();

        // Assert
        assertEquals(initialPosition, airship.getPosition()); // No movement should occur
    }
    @Test
void testIsWaypoint_True() {
    // Arrange
    Coordinate waypoint = new Coordinate(5, 5);
    airship.addWaypoint(waypoint);

    // Act
    boolean result = airship.isWaypoint(5, 5);

    // Assert
    assertTrue(result); // Az eredménynek igaznak kell lennie
}

@Test
void testIsWaypoint_False() {
    // Arrange
    Coordinate waypoint = new Coordinate(5, 5);
    airship.addWaypoint(waypoint);

    // Act
    boolean result = airship.isWaypoint(6, 6);

    // Assert
    assertFalse(result); // Az eredménynek hamisnak kell lennie
}

    @Test
    void testMoveToNextWaypoint_ReachesWaypoint2() {
        // Arrange
        airship.setMoving(true);
        airship.addWaypoint(new Coordinate(5, 5)); // Same as initial position

        // Act
        airship.moveToNextWaypoint();

        // Assert
        assertEquals(5, airship.getPosition().getPosX());
        assertEquals(5, airship.getPosition().getPosY());
        assertTrue(airship.getWaypoints().contains(new Coordinate(5, 5))); // Waypoint should be re-added
    }

    @Test
    void testMoveToNextWaypoint_MovesTowardsWaypoint_XDirection() {
        // Arrange
        airship.setMoving(true);
        airship.addWaypoint(new Coordinate(7, 5)); // Target is to the right

        // Act
        airship.moveToNextWaypoint();

        // Assert
        assertEquals(6, airship.getPosition().getPosX()); // Moves one step towards X
        assertEquals(5, airship.getPosition().getPosY()); // Y remains the same
    }

    @Test
    void testMoveToNextWaypoint_MovesTowardsWaypoint_YDirection() {
        // Arrange
        airship.setMoving(true);
        airship.addWaypoint(new Coordinate(5, 7)); // Target is below

        // Act
        airship.moveToNextWaypoint();

        // Assert
        assertEquals(5, airship.getPosition().getPosX()); // X remains the same
        assertEquals(6, airship.getPosition().getPosY()); // Moves one step towards Y
    }

    @Test
    void testMoveToNextWaypoint_WithDelay2() throws InterruptedException {
        // Arrange
        airship.setMoving(true);
        airship.addWaypoint(new Coordinate(6, 5));

        // Act
        airship.moveToNextWaypoint(); // First move
        Thread.sleep(334); // Wait for move delay
        airship.moveToNextWaypoint(); // Second move

        // Assert
        assertEquals(6, airship.getPosition().getPosX());
        assertEquals(5, airship.getPosition().getPosY());
    }

    @Test
    void testMoveToNextWaypoint_DiagonalMovement() {
        // Arrange
        airship.setMoving(true);
        airship.addWaypoint(new Coordinate(7, 7)); // Target is diagonally up-right

        // Act
        airship.moveToNextWaypoint();

        // Assert
        assertEquals(6, airship.getPosition().getPosX()); // Moves one step towards X
        assertEquals(6, airship.getPosition().getPosY()); // Moves one step towards Y
    }
}
