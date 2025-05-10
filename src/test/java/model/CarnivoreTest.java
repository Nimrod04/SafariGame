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
public class CarnivoreTest {
    
    private TestCarnivore carnivore;
    private List<Animal> herbivores;
    private GameSpeed gameSpeed;

    private class TestCarnivore extends Carnivore {
        @Override
        public int getPrice() {
            return 100;
        }
    }

    private class TestHerbivore extends Animal {
        public TestHerbivore(int x, int y) {
            super();
            setActualCoordinate(new Coordinate(x, y));
        }

        @Override
        public int getPrice() {
            return 50;
        }

        @Override
        public void eat() {
            foodLevel = 100.0;
        }

        @Override
        public void update(GameSpeed gs, List<Animal> herbivores) {
            if (targetCoordinate == null) {
                generateRandomTarget();
            }
            moveTo(targetCoordinate, gs);
            gettingOld(gs);
        }
    }


    @BeforeEach
    void setUp() {
        carnivore = new TestCarnivore();
        herbivores = new ArrayList<>();
        gameSpeed = new GameSpeed();
    }

    @Test
    void testEat() {
        carnivore.eat();
        assertEquals(100.0, carnivore.foodLevel);
        assertTrue(carnivore.isEating);
        assertNotEquals(0, carnivore.lastEatTime);
    }

    @Test
    void testNap() {
        assertFalse(carnivore.nap());
    }

    @Test
    void testHuntWithNoHerbivores() {
        carnivore.hunt(new ArrayList<>(), gameSpeed);
        assertNotNull(carnivore.targetCoordinate);
        assertNull(carnivore.closestHerbivore);
        assertEquals(Double.MAX_VALUE, carnivore.closestDistance);
    }
    
    @Test
    void testHuntWithNullHerbivoresList() {
        carnivore.hunt(null, gameSpeed);
        assertNotNull(carnivore.targetCoordinate);
        assertNull(carnivore.closestHerbivore);
    }

    @Test
    void testHuntWithDeadHerbivores() {
        TestHerbivore deadHerbivore = new TestHerbivore(100, 100);
        deadHerbivore.isAlive = false;
        herbivores.add(deadHerbivore);
        
        carnivore.hunt(herbivores, gameSpeed);
        
        assertNull(carnivore.closestHerbivore);
        assertEquals(Double.MAX_VALUE, carnivore.closestDistance);
    }

    @Test
    void testHuntAndCatchHerbivore() {
        TestHerbivore prey = new TestHerbivore(100, 100);
        herbivores.add(prey);
        carnivore.setActualCoordinate(new Coordinate(100, 100));
        
        carnivore.hunt(herbivores, gameSpeed);
        
        assertFalse(prey.isAlive);
        assertTrue(carnivore.isEating);
        assertEquals(100.0, carnivore.foodLevel);
    }

    @Test
    void testUpdateWhileEating() {
        carnivore.isEating = true;
        carnivore.lastEatTime = System.currentTimeMillis();
        
        carnivore.update(gameSpeed, herbivores);
        
        assertTrue(carnivore.isEating);
    }

    @Test
    void testUpdateAfterEatingPeriod() {
        carnivore.isEating = true;
        carnivore.lastEatTime = System.currentTimeMillis() - 21000;
        
        carnivore.update(gameSpeed, herbivores);
        
        assertFalse(carnivore.isEating);
        assertNull(carnivore.closestHerbivore);
        assertEquals(Double.MAX_VALUE, carnivore.closestDistance);
    }
@Test
    void testUpdateWhenHungry() {
        carnivore.foodLevel = 20.0;
        TestHerbivore prey = new TestHerbivore(150, 150);
        herbivores.add(prey);
        
        carnivore.update(gameSpeed, herbivores);
        
        assertEquals(prey.getCoordinate(), carnivore.targetCoordinate);
    }

    @Test
    void testUpdateWhenThirstyAndReachedTarget() {
        carnivore.waterLevel = 20.0;
        carnivore.setTargetCoordinate(new Coordinate(10, 10));
        carnivore.setActualCoordinate(new Coordinate(10, 10));
        
        carnivore.update(gameSpeed, herbivores);
        
        assertEquals(99.95, carnivore.waterLevel);
    }

    @Test
    void testUpdateWhenInGroup() {
        List<Animal> group = new ArrayList<>();
        TestCarnivore leader = new TestCarnivore();
        leader.setTargetCoordinate(new Coordinate(100, 100));
        group.add(leader);
        group.add(carnivore);
        carnivore.joinGroup(group);
        
        carnivore.update(gameSpeed, herbivores);
        
        assertNotNull(carnivore.actualCoordinate);
    }

    @Test
    void testUpdateWithNoTargetCoordinate() {
        carnivore.setTargetCoordinate(null);
        carnivore.waterLevel = 100.0;
        carnivore.foodLevel = 100.0;
        
        carnivore.update(gameSpeed, herbivores);
        
        assertNotNull(carnivore.targetCoordinate);
    }
    
    @Test
    void testUpdateWhenThirsty() {
        carnivore.waterLevel = 20.0;
        carnivore.foodLevel = 100.0;
        
        carnivore.update(gameSpeed, herbivores);
        
        assertNotNull(carnivore.targetCoordinate);
    }

    

    @Test
    void testUpdateWhenHungryAndThirsty() {
        carnivore.foodLevel = 20.0;
        carnivore.waterLevel = 20.0;
        carnivore.isEating = true;
        
        carnivore.update(gameSpeed, herbivores);
        
        assertFalse(carnivore.isEating);
    }
    
}
