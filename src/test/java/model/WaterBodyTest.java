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
public class WaterBodyTest {
    
    @Test
    void testWaterBodyInitialization() {
        Coordinate coordinate = new Coordinate(3, 7);
        WaterBody waterBody = new WaterBody(coordinate);

        assertEquals(coordinate, new Coordinate(3, 7));
        assertEquals(1000, WaterBody.PRICE);
    }

    
}
