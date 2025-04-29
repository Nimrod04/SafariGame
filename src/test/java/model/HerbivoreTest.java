/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Felhasznalo
 */
public class HerbivoreTest {
    
    private TestHerbivore herbivore;
    private GameSpeed gameSpeed;
    private List<Animal> herbivores;

    // Test implementation of abstract class
    private class TestHerbivore extends Herbivore {
        @Override
        public int getPrice() {
            return 100;
        }
    }

    @BeforeEach
    void setUp() {
        herbivore = new TestHerbivore();
        gameSpeed = new GameSpeed();
        herbivores = new ArrayList<>();
    }

    @Test
    void testGraze() {
        assertDoesNotThrow(() -> herbivore.graze());
    }

    @Test
    void testEat() {
        herbivore.foodLevel = 50.0;
        herbivore.eat();
        
        assertEquals(100.0, herbivore.foodLevel);
        assertTrue(herbivore.isEating);
        assertNotEquals(0, herbivore.lastEatTime);
    }

    @Test
    void testUpdateWhileEating() {
        herbivore.isEating = true;
        herbivore.lastEatTime = System.currentTimeMillis();
        
        herbivore.update(gameSpeed, herbivores);
        
        assertTrue(herbivore.isEating);
    }
     @Test
    void testUpdateAfterEatingPeriod() {
        herbivore.isEating = true;
        herbivore.lastEatTime = System.currentTimeMillis() - 21000;
        
        herbivore.update(gameSpeed, herbivores);
        
        assertFalse(herbivore.isEating);
        assertEquals(100.0, herbivore.foodLevel);
    }

    @Test
    void testUpdateWhenHungryAndReachedTarget() {
        herbivore.foodLevel = 20.0;
        Coordinate target = new Coordinate(10, 10);
        herbivore.setTargetCoordinate(target);
        herbivore.setActualCoordinate(target);
        
        herbivore.update(gameSpeed, herbivores);
        
        assertTrue(herbivore.isEating);
    }

    @Test
    void testUpdateWhenThirstyAndReachedTarget() {
        herbivore.waterLevel = 20.0;
        Coordinate target = new Coordinate(10, 10);
        herbivore.setTargetCoordinate(target);
        herbivore.setActualCoordinate(target);
        
        herbivore.update(gameSpeed, herbivores);
        
        assertEquals(100.0, herbivore.waterLevel);
    }

    @Test
    void testUpdateWhenInGroup() {
        List<Animal> group = new ArrayList<>();
        TestHerbivore leader = new TestHerbivore();
        leader.setTargetCoordinate(new Coordinate(100, 100));
        group.add(leader);
        group.add(herbivore);
        herbivore.joinGroup(group);
        
        herbivore.update(gameSpeed, herbivores);
        
        assertNotNull(herbivore.targetCoordinate);
    }
    
    @Test
    void testUpdateWithNoTargetCoordinate() {
        herbivore.setTargetCoordinate(null);
        herbivore.update(gameSpeed, herbivores);
        assertNotNull(herbivore.targetCoordinate);
    }

    @Test
    void testUpdateWhenReachedTarget() {
        Coordinate oldTarget = new Coordinate(10, 10);
        herbivore.setTargetCoordinate(oldTarget);
        herbivore.setActualCoordinate(oldTarget);
        
        herbivore.update(gameSpeed, herbivores);
        
        assertNotNull(herbivore.targetCoordinate);
        assertNotEquals(oldTarget, herbivore.targetCoordinate);
    }

    @Test
    void testInheritance() {
        assertTrue(herbivore instanceof Animal);
    }
    
     @Test
    void testUpdateWhenInGroupAsLeader() {
        List<Animal> group = new ArrayList<>();
        group.add(herbivore);
        TestHerbivore follower = new TestHerbivore();
        group.add(follower);
        herbivore.joinGroup(group);
        
        herbivore.update(gameSpeed, herbivores);
        
        assertNotNull(herbivore.targetCoordinate);
    }

    @Test
    void testUpdateWhenLeaderHasNoTarget() {
        List<Animal> group = new ArrayList<>();
        TestHerbivore leader = new TestHerbivore();
        leader.setTargetCoordinate(null);
        group.add(leader);
        group.add(herbivore);
        herbivore.joinGroup(group);
        
        herbivore.update(gameSpeed, herbivores);
        
        assertNotNull(herbivore.targetCoordinate);
    }

    @Test
    void testUpdateWithNullTargetAndNotInGroup() {
        herbivore.setTargetCoordinate(null);
        herbivore.waterLevel = 100.0;
        herbivore.foodLevel = 100.0;
        
        herbivore.update(gameSpeed, herbivores);
        
        assertNotNull(herbivore.targetCoordinate);
    }

    

    @Test
    void testUpdateWhenNotInGroupAndNotHungryAndNotThirsty() {
        herbivore.foodLevel = 100.0;
        herbivore.waterLevel = 100.0;
        herbivore.setTargetCoordinate(new Coordinate(50, 50));
        
        herbivore.update(gameSpeed, herbivores);
        
        assertNotNull(herbivore.targetCoordinate);
    }
    
    
    
}
