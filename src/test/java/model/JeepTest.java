/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author nimro
 */
public class JeepTest {

    private Jeep jeep;
    private Coordinate startPosition;
    private List<Coordinate> path;

    @BeforeEach
    void setUp() {
        startPosition = new Coordinate(5, 5);
        path = new ArrayList<>();
        path.add(new Coordinate(6, 5));
        path.add(new Coordinate(7, 5));
        jeep = new Jeep(startPosition, path);
    }

    @Test
    void testConstructor() {
        assertEquals(startPosition, jeep.getPosition());
        assertEquals(startPosition, jeep.getStartPosition());
        assertEquals(path, jeep.getPath());
        assertNotNull(jeep.getHitbox());
        assertFalse(jeep.isReadyToMove());
        assertTrue(jeep.getPassengers().isEmpty());
    }

    @Test
    void testGetPosition() {
        assertEquals(startPosition, jeep.getPosition());
    }

    @Test
    void testSatisfaction() {
        jeep.satisfaction(10);
        assertEquals(10, jeep.satisfactionPoint);
    }

    @Test
    void testGetHitbox() {
        Rectangle hitbox = jeep.getHitbox();
        assertNotNull(hitbox);
        assertEquals(2, hitbox.x); // 5 - 3
        assertEquals(2, hitbox.y); // 5 - 3
        assertEquals(7, hitbox.width);
        assertEquals(7, hitbox.height);
    }

    @Test
    void testMove() {
        GameSpeed gameSpeed = new GameSpeed(); // Speed multiplier = 1
        jeep.startMoving();
        jeep.move(gameSpeed);

        assertEquals(new Coordinate(6, 5), jeep.getPosition());
        assertEquals(1, jeep.tourLength);
        assertEquals(10, jeep.updateCount); // 11 - gameSpeed.getMulti()
    }

    @Test
    void testHasReachedEnd() {
        GameSpeed gs = new GameSpeed();
        
        assertFalse(jeep.hasReachedEnd());
        jeep.move(gs);
        jeep.move(gs);
        assertFalse(jeep.hasReachedEnd());
    }

    @Test
    void testStartMoving() {
        jeep.startMoving();
        assertTrue(jeep.isReadyToMove());
    }

    @Test
    void testPickUpTourist() {
        Tourist tourist1 = new Tourist();
        Tourist tourist2 = new Tourist();
        Tourist tourist3 = new Tourist();
        Tourist tourist4 = new Tourist();
        Tourist tourist5 = new Tourist();

        jeep.pickUpTourist(tourist1);
        jeep.pickUpTourist(tourist2);
        jeep.pickUpTourist(tourist3);
        jeep.pickUpTourist(tourist4);
        jeep.pickUpTourist(tourist5); // Should not be added

        assertEquals(4, jeep.getPassengers().size());
    }

    @Test
    void testClearPassengers() {
        Tourist tourist = new Tourist();
        jeep.pickUpTourist(tourist);
        jeep.clearPassengers();
        assertTrue(jeep.getPassengers().isEmpty());
    }

    @Test
    void testResetPosition() {
        jeep.move(new GameSpeed());
        jeep.resetPosition();

        assertEquals(startPosition, startPosition);
        assertFalse(jeep.isReadyToMove());
    }

    @Test
    void testRecordAnimal() {
        jeep.recordAnimal("Lion");
        jeep.recordAnimal("Lion");
        jeep.recordAnimal("Elephant");

        assertEquals(2, jeep.getSeenAnimals().get("Lion"));
        assertEquals(1, jeep.getSeenAnimals().get("Elephant"));
    }

    @Test
    void testClearSeenAnimals() {
        jeep.recordAnimal("Lion");
        jeep.clearSeenAnimals();
        assertTrue(jeep.getSeenAnimals().isEmpty());
    }

}
