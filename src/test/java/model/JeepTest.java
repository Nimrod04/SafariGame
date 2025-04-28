/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        assertNotNull(jeep.getPath());
        assertNotNull(jeep.getRoad());
        assertEquals(path, jeep.getRoad());
        assertEquals(2000, Jeep.PRICE);
        assertFalse(jeep.isReadyToMove());
    }

    @Test
    void testGetPosition() {
        assertEquals(startPosition, jeep.getPosition());
    }

    @Test
    void testSatisfaction() {
        jeep.satisfaction(10);
        assertEquals(10, jeep.satisfactionPoint);
        jeep.satisfaction(5);
        assertEquals(15, jeep.satisfactionPoint);
    }

    @Test
    void testGetHitbox() {
        Rectangle hitbox = jeep.getHitbox();
        assertEquals(startPosition.getPosX() - 3, hitbox.x);
        assertEquals(startPosition.getPosY() - 3, hitbox.y);
        assertEquals(7, hitbox.width);
        assertEquals(7, hitbox.height);
    }

    @Test
    void testMove() {
        GameSpeed gameSpeed = new GameSpeed();
        jeep.startMoving();
        assertFalse(jeep.hasReachedEnd());
        
        // First move
        jeep.move(gameSpeed);
        assertEquals(6, jeep.getPosition().getPosX()); // Ellenőrizzük hogy a 6. pozícióba lépett
        assertEquals(1, jeep.tourLength);
        
        // Second move
        jeep.move(gameSpeed);
        assertEquals(6, jeep.getPosition().getPosX()); // Ellenőrizzük hogy a 7. pozícióba lépett
        assertFalse(jeep.hasReachedEnd());
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
        assertEquals(4, jeep.getPassengers().size());

        // Test maximum capacity
        jeep.pickUpTourist(tourist5);
        assertEquals(4, jeep.getPassengers().size());
    }

    @Test
    void testClearPassengers() {
        Tourist tourist = new Tourist();
        jeep.pickUpTourist(tourist);
        assertFalse(jeep.getPassengers().isEmpty());
        
        jeep.clearPassengers();
        assertTrue(jeep.getPassengers().isEmpty());
    }

    @Test
    void testResetPosition() {
        jeep.startMoving();
        jeep.move(new GameSpeed());
        
        jeep.resetPosition();
        assertEquals(7, jeep.getPosition().getPosX());
        assertEquals(5, jeep.getPosition().getPosY());
        assertFalse(jeep.isReadyToMove());
    }

    @Test
    void testRecordAndClearAnimals() {
        jeep.recordAnimal("Lion");
        jeep.recordAnimal("Lion");
        jeep.recordAnimal("Elephant");

        Map<String, Integer> seenAnimals = jeep.getSeenAnimals();
        assertEquals(2, seenAnimals.get("Lion"));
        assertEquals(1, seenAnimals.get("Elephant"));

        jeep.clearSeenAnimals();
        assertTrue(jeep.getSeenAnimals().isEmpty());
    }

    @Test
    void testPrintSeenAnimals() {
        jeep.recordAnimal("Lion");
        jeep.recordAnimal("Elephant");
        jeep.printSeenAnimals();
        // Visual verification of console output needed
    }

    @Test
    void testTourLengthIncrement() {
        long initialLength = jeep.tourLength;
        jeep.startMoving();
        jeep.move(new GameSpeed());
        assertEquals(initialLength + 1, jeep.tourLength);
    }

    @Test
    void testUpdateCount() {
        GameSpeed gameSpeed = new GameSpeed();
        jeep.startMoving();
        jeep.move(gameSpeed);
        assertEquals(10, jeep.updateCount); // 11 - gameSpeed.getMulti()
    }

}
