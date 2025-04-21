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
public class RoadTest {
    
    @Test
    void testConstructor() {
        // Létrehozunk egy Road példányt, és ellenőrizzük, hogy nem null
        Road road = new Road();
        assertNotNull(road);
    }

    @Test
    void testPriceConstant() {
        // Ellenőrizzük, hogy a PRICE konstans értéke helyes
        assertEquals(200, Road.PRICE);
    }
    
}
