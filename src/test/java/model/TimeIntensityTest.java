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
public class TimeIntensityTest {
    
    @Test
    void testEnumValues() {
        // Ellenőrizzük, hogy az enum értékek helyesen inicializálódtak
        assertEquals(0, TimeIntensity.NORMAL.getMulti());
        assertEquals(5, TimeIntensity.FAST.getMulti());
        assertEquals(10, TimeIntensity.FASTEST.getMulti());
    }

    @Test
    void testEnumNames() {
        // Ellenőrizzük az enum nevek helyességét
        assertEquals("NORMAL", TimeIntensity.NORMAL.name());
        assertEquals("FAST", TimeIntensity.FAST.name());
        assertEquals("FASTEST", TimeIntensity.FASTEST.name());
    }

    @Test
    void testEnumValuesArray() {
        // Ellenőrizzük, hogy az enum értékek tömbje helyes
        TimeIntensity[] values = TimeIntensity.values();
        assertArrayEquals(new TimeIntensity[]{TimeIntensity.NORMAL, TimeIntensity.FAST, TimeIntensity.FASTEST}, values);
    }

    @Test
    void testValueOf() {
        // Ellenőrizzük az enum értékek lekérését név alapján
        assertEquals(TimeIntensity.NORMAL, TimeIntensity.valueOf("NORMAL"));
        assertEquals(TimeIntensity.FAST, TimeIntensity.valueOf("FAST"));
        assertEquals(TimeIntensity.FASTEST, TimeIntensity.valueOf("FASTEST"));
    }
    
}
