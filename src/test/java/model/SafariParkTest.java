/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nimro
 */
public class SafariParkTest {
    
    @Test
    public void testConstructorInitializesFields() {
        SafariPark park = new SafariPark();
        assertNotNull(park.getEntities());
        assertNotNull(park.getMapGrid());
        assertEquals(10, park.getMapGrid().length);
        assertEquals(10, park.getMapGrid()[0].length);
    }

    @Test
    public void testGenerateMapSetsEntranceAndExit() {
        SafariPark park = new SafariPark();
        park.generateMap();
        Path[][] grid = park.getMapGrid();

        assertNotNull(grid[0][0]);
        assertEquals("/resources/images/entrance.png", grid[0][0].getImagePath());

        assertNotNull(grid[9][9]);
        assertEquals("/resources/images/exit.png", grid[9][9].getImagePath());
    }

    @Test
    public void testGetEntitiesReturnsList() {
        SafariPark park = new SafariPark();
        assertNotNull(park.getEntities());
        assertTrue(park.getEntities().isEmpty());
    }

    @Test
    public void testUpdateAnimalsDoesNotThrow() {
        SafariPark park = new SafariPark();
        assertDoesNotThrow(park::updateAnimals);
    }
    
}
