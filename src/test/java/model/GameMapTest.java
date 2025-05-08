/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import org.junit.jupiter.api.Test;

import view.Playing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author nimro
 */
public class GameMapTest {
    
private GameMap gameMap;
    private Playing mockPlaying;
    private GameSpeed mockSpeed;

    @BeforeEach
    public void setUp() {
        mockPlaying = mock(Playing.class);
        mockSpeed = mock(GameSpeed.class);
        gameMap = new GameMap(40, 21, mockSpeed, mockPlaying);
    }

    @Test
    public void testConstructorInitializesFields() {
        assertNotNull(gameMap.getMap());
        assertEquals(40, gameMap.getWidth());
        assertEquals(21, gameMap.getHeight());
        assertNotNull(gameMap.getCameras());
        assertNotNull(gameMap.getChargingStations());
        assertNotNull(gameMap.getDrones());
        assertNotNull(gameMap.getAirships());
        assertNotNull(gameMap.getRangers());
        assertNotNull(gameMap.getRoads());
        assertNotNull(gameMap.getJeeps());
        assertNotNull(gameMap.getJeepQueue());
        assertNotNull(gameMap.elephants);
        assertNotNull(gameMap.gazelles);
        assertNotNull(gameMap.lions);
        assertNotNull(gameMap.cheetahs);
    }

    @Test
    public void testSetTileAndGetTile() {
        gameMap.setTile(1, 1, Tile.TileType.GRASS);
        assertEquals(Tile.TileType.GRASS, gameMap.getTile(1, 1).getType());
    }

    @Test
    public void testAddAndGetCameras() {
        Camera camera = mock(Camera.class);
        gameMap.addCamera(camera);
        assertTrue(gameMap.getCameras().contains(camera));
    }

    @Test
    public void testAddAndGetChargingStations() {
        ChargingStation cs = mock(ChargingStation.class);
        gameMap.addChargingStation(cs);
        assertTrue(gameMap.getChargingStations().contains(cs));
    }

    @Test
    public void testAddAndGetDrones() {
        Drone drone = mock(Drone.class);
        gameMap.addDrone(drone);
        assertTrue(gameMap.getDrones().contains(drone));
    }

    @Test
    public void testAddAndGetAirships() {
        Airship airship = mock(Airship.class);
        gameMap.addAirship(airship);
        assertTrue(gameMap.getAirships().contains(airship));
        gameMap.removeAirship(airship);
        assertFalse(gameMap.getAirships().contains(airship));
    }

    @Test
    public void testAddAndGetAnimals() {
        Gazelle gazelle = mock(Gazelle.class);
        Elephant elephant = mock(Elephant.class);
        Lion lion = mock(Lion.class);
        Cheetah cheetah = mock(Cheetah.class);

        gameMap.addGazelle(gazelle);
        gameMap.addElephant(elephant);
        gameMap.addLion(lion);
        gameMap.addCheetah(cheetah);

        assertTrue(gameMap.gazelles.contains(gazelle));
        assertTrue(gameMap.elephants.contains(elephant));
        assertTrue(gameMap.lions.contains(lion));
        assertTrue(gameMap.cheetahs.contains(cheetah));
    }

    @Test
    public void testAddAndGetRangers() {
        Ranger ranger = mock(Ranger.class);
        gameMap.addRanger(ranger);
        assertTrue(gameMap.getRangers().contains(ranger));
    }

    @Test
    public void testAddAndGetRoads() {
        Coordinate c = new Coordinate(5, 5);
        gameMap.addRoads(c);
        assertTrue(gameMap.getRoads().contains(c));
    }

    @Test
    public void testDeleteAnimals() {
        Elephant elephant = mock(Elephant.class);
        elephant.isAlive = true; // <-- közvetlenül állítsd be
        gameMap.elephants.clear();
        gameMap.elephants.add(elephant);
        List<Elephant> result = gameMap.deleteElephants(gameMap.elephants);
        assertTrue(result.contains(elephant));
    
        Gazelle gazelle = mock(Gazelle.class);
        gazelle.isAlive = true;
        gameMap.gazelles.clear();
        gameMap.gazelles.add(gazelle);
        List<Gazelle> result2 = gameMap.deleteGazelles(gameMap.gazelles);
        assertTrue(result2.contains(gazelle));
    
        Lion lion = mock(Lion.class);
        lion.isAlive = true;
        gameMap.lions.clear();
        gameMap.lions.add(lion);
        List<Lion> result3 = gameMap.deleteLions(gameMap.lions);
        assertTrue(result3.contains(lion));
    
        Cheetah cheetah = mock(Cheetah.class);
        cheetah.isAlive = true;
        gameMap.cheetahs.clear();
        gameMap.cheetahs.add(cheetah);
        List<Cheetah> result4 = gameMap.deleteCheetahs(gameMap.cheetahs);
        assertTrue(result4.contains(cheetah));
    }

    @Test
    public void testRemoveAnimal() {
        Gazelle gazelle = mock(Gazelle.class);
        gameMap.gazelles.add(gazelle);
        gameMap.removeAnimal(gazelle);
        assertFalse(gameMap.gazelles.contains(gazelle));
    }

    @Test
    public void testGetAllAnimals() {
        gameMap.elephants.clear();
        gameMap.gazelles.clear();
        gameMap.lions.clear();
        gameMap.cheetahs.clear();
        Elephant elephant = mock(Elephant.class);
        Gazelle gazelle = mock(Gazelle.class);
        Lion lion = mock(Lion.class);
        Cheetah cheetah = mock(Cheetah.class);
        gameMap.elephants.add(elephant);
        gameMap.gazelles.add(gazelle);
        gameMap.lions.add(lion);
        gameMap.cheetahs.add(cheetah);
        List<Animal> all = gameMap.getAllAnimals();
        assertTrue(all.contains(elephant));
        assertTrue(all.contains(gazelle));
        assertTrue(all.contains(lion));
        assertTrue(all.contains(cheetah));
    }

    @Test
    public void testAddAndGetJeeps() {
        Jeep jeep = mock(Jeep.class);
        when(jeep.getPosition()).thenReturn(new Coordinate(0, 10));
        when(jeep.hasReachedEnd()).thenReturn(false);
        gameMap.addJeep(jeep);
        assertTrue(gameMap.getJeeps().contains(jeep));
        assertTrue(gameMap.getJeepQueue().contains(jeep));
    }

    @Test
    public void testAddJeepNull() {
        gameMap.addJeep(null); // Should not throw
    }

    @Test
    public void testAddJeepInvalid() {
        Jeep jeep = mock(Jeep.class);
        when(jeep.getPosition()).thenReturn(null);
        when(jeep.hasReachedEnd()).thenReturn(false);
        gameMap.addJeep(jeep); // Should not throw

        Jeep jeep2 = mock(Jeep.class);
        when(jeep2.getPosition()).thenReturn(new Coordinate(0, 10));
        when(jeep2.hasReachedEnd()).thenReturn(true);
        gameMap.addJeep(jeep2); // Should not throw
    }

    @Test
    public void testGetPathBetweenGates_NoPath() {
        // Alapból csak a két kapu van az utakon, nincs összefüggő út
        List<Coordinate> path = gameMap.getPathBetweenGates();
        assertTrue(path.isEmpty());
    }
    
    @Test
    public void testGetPathBetweenGates_WithPath() {
        // Hozzáadunk minden köztes pontot, hogy legyen összefüggő út
        for (int x = 1; x < 39; x++) {
            gameMap.addRoads(new Coordinate(x, 10));
        }
        List<Coordinate> path = gameMap.getPathBetweenGates();
        assertFalse(path.isEmpty());
        assertEquals(new Coordinate(0, 10), path.get(0));
        assertEquals(new Coordinate(39, 10), path.get(path.size() - 1));
    }
    
    @Test
    public void testIsPathBetweenGates_NoPath() {
        // Alapból nincs út
        assertFalse(gameMap.isPathBetweenGates());
    }
    
    @Test
    public void testIsPathBetweenGates_WithPath() {
        // Hozzáadunk minden köztes pontot, hogy legyen összefüggő út
        for (int x = 1; x < 39; x++) {
            gameMap.addRoads(new Coordinate(x, 10));
        }
        assertTrue(gameMap.isPathBetweenGates());
    }

    @Test
    public void testUpdateAnimalsAndMoveAnimals() {
        // Just call to cover code, logic is tested elsewhere
        gameMap.updateAnimals();
    }

    @Test
    public void testUpdateJeeps() {
        Finance mockFinance = mock(Finance.class);
        when(mockPlaying.getFinance()).thenReturn(mockFinance);

        Jeep jeep = mock(Jeep.class);
        when(jeep.getPosition()).thenReturn(new Coordinate(0, 10));
        when(jeep.hasReachedEnd()).thenReturn(false);
        when(jeep.isReadyToMove()).thenReturn(true);
        when(jeep.getHitbox()).thenReturn(mock(java.awt.Rectangle.class));
        doNothing().when(jeep).move(any());
        doNothing().when(jeep).recordAnimal(anyString());
        doNothing().when(jeep).printSeenAnimals();
        doNothing().when(jeep).clearPassengers();
        doNothing().when(jeep).clearSeenAnimals();
        doNothing().when(jeep).resetPosition();

        gameMap.addJeep(jeep);
        gameMap.updateJeeps();
    }
    
}
