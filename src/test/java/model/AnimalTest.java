/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import java.awt.Rectangle;
/**
 *
 * @author User
 */
public class AnimalTest {
    
    private TestAnimal animal;
    private GameSpeed gameSpeed;

    // Test implementation of abstract class
    private class TestAnimal extends Animal {
        @Override
        public void eat() {}

        @Override
        public void update(GameSpeed gs, List<Animal> herbivores) {}

        @Override
        public int getPrice() {
            return 100;
        }
    }

    @BeforeEach
    void setUp() {
        animal = new TestAnimal();
        gameSpeed = new GameSpeed();
    }

    @Test
    void testConstructor() {
        assertNotNull(animal.actualCoordinate);
        assertEquals(100.0, animal.getFoodLevel());
        assertEquals(100.0, animal.getWaterLevel());
        assertTrue(animal.isAlive);
        assertFalse(animal.nap);
        assertEquals(0, animal.napTime);
    }

    @Test
    void testCalculateHitbox() {
        Rectangle hitbox = animal.calculateHitbox();
        assertNotNull(hitbox);
        assertTrue(hitbox.width > 0);
        assertTrue(hitbox.height > 0);
    }

    @Test
    void testFindWater() {
        animal.drink.add(new int[]{5, 5});
        animal.findWater();
        assertNotNull(animal.targetCoordinate);
    }

    @Test
    void testFindWaterEmpty() {
        animal.findWater();
        assertNull(animal.targetCoordinate);
    }

    @Test
    void testFindFood() {
        animal.food.add(new int[]{5, 5});
        animal.findFood();
        assertNotNull(animal.targetCoordinate);
    }

    @Test
    void testFindFoodEmpty() {
        animal.findFood();
        assertNull(animal.targetCoordinate);
    }
    @Test
    void testDistanceTo() {
        TestAnimal other = new TestAnimal();
        other.setActualCoordinate(new Coordinate(100, 100));
        animal.setActualCoordinate(new Coordinate(0, 0));
        double distance = animal.distanceTo(other);
        assertEquals(141.4, distance, 0.1);
    }

    @Test
    void testDecreaseHunger() {
        animal.decreaseHunger(gameSpeed.getMulti());
        assertTrue(animal.getFoodLevel() < 100.0);
    }

    @Test
    void testDecreaseHungerDeath() {
        animal.foodLevel = 0.04;
        animal.decreaseHunger(gameSpeed.getMulti());
        assertFalse(animal.isAlive);
    }

    @Test
    void testDecreaseThirst() {
        animal.decreaseThirst(gameSpeed.getMulti());
        assertTrue(animal.getWaterLevel() < 100.0);
    }

    @Test
    void testDecreaseThirstDeath() {
        animal.waterLevel = 0.04;
        animal.decreaseThirst(gameSpeed.getMulti());
        assertFalse(animal.isAlive);
    }
    @Test
    void testAddVisitedWater() {
        Tile waterTile = new Tile(Tile.TileType.WATER);
        animal.addVisitedWater(waterTile, 1, 1);
        assertEquals(1, animal.drink.size());
    }

    @Test
    void testAddVisitedWaterDuplicate() {
        Tile waterTile = new Tile(Tile.TileType.WATER);
        animal.addVisitedWater(waterTile, 1, 1);
        animal.addVisitedWater(waterTile, 1, 1);
        assertEquals(1, animal.drink.size());
    }

    @Test
    void testAddVisitedFood() {
        Tile grassTile = new Tile(Tile.TileType.GRASS);
        animal.addVisitedFood(grassTile, 1, 1);
        assertEquals(1, animal.food.size());
    }

    @Test
    void testAddVisitedFoodDuplicate() {
        Tile grassTile = new Tile(Tile.TileType.GRASS);
        animal.addVisitedFood(grassTile, 1, 1);
        animal.addVisitedFood(grassTile, 1, 1);
        assertEquals(1, animal.food.size());
    }

    @Test
    void testDrink() {
        animal.waterLevel = 50.0;
        animal.drink();
        assertEquals(100.0, animal.waterLevel);
    }

    @Test
    void testNap() {
        assertFalse(animal.nap());
    }

    @Test
    void testGettingOld() {
        animal.gettingOld(gameSpeed);
        assertTrue(animal.age > 0);
    }

    @Test
    void testGettingOldDeath() {
        animal.age = animal.lifetime;
        animal.gettingOld(gameSpeed);
        assertFalse(animal.isAlive);
    }

    @Test
    void testMoveTo() {
        Coordinate target = new Coordinate(100, 100);
        animal.setActualCoordinate(new Coordinate(0, 0));
        animal.moveTo(target, gameSpeed);
        assertNotEquals(0, animal.getCoordinate().getPosX());
        assertNotEquals(0, animal.getCoordinate().getPosY());
    }

    @Test
    void testHasReachedTarget() {
        animal.setActualCoordinate(new Coordinate(100, 100));
        animal.setTargetCoordinate(new Coordinate(100, 100));
        assertTrue(animal.hasReachedTarget());
    }

    @Test
    void testGenerateRandomTarget() {
        animal.generateRandomTarget();
        assertNotNull(animal.targetCoordinate);
    }

    @Test
    void testJoinGroup() {
        List<Animal> group = new ArrayList<>();
        animal.joinGroup(group);
        assertTrue(animal.isInGroup());
        assertEquals(group, animal.getGroup());
    }

    @Test
    void testIsThirsty() {
        animal.waterLevel = 20.0;
        assertTrue(animal.isThirsty());
        animal.waterLevel = 50.0;
        assertFalse(animal.isThirsty());
    }

    @Test
    void testIsHungry() {
        animal.foodLevel = 20.0;
        assertTrue(animal.isHungry());
        animal.foodLevel = 50.0;
        assertFalse(animal.isHungry());
    }

    @Test
    void testIsAdult() {
        assertFalse(animal.isAdult());
        animal.age = 11000;
        assertTrue(animal.isAdult());
    }

    @Test
    void testAgeEffectOnHunger() {
        animal.age = 13000; // Between 12000 and 24000
        animal.decreaseHunger(gameSpeed.getMulti());
        double middleAgeFoodLevel = animal.getFoodLevel();
        
        animal.foodLevel = 100.0;
        animal.age = 25000; // Above 24000
        animal.decreaseHunger(gameSpeed.getMulti());
        double oldAgeFoodLevel = animal.getFoodLevel();
        
        assertTrue(oldAgeFoodLevel < middleAgeFoodLevel);
    }

    @Test
    void testAddFoodIfEdible() {
        Tile grassTile = new Tile(Tile.TileType.GRASS);
        
        animal.addFoodIfEdible(grassTile, 1, 1);
        assertEquals(1, animal.food.size());
        
    }
    
}
