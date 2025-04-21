/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author nimro
 */
public class FinanceTest {
    
    private Finance finance;

    @BeforeEach
    void setUp() {
        finance = new Finance();
    }

    @Test
    void testConstructor() {
        // Ellenőrizzük, hogy az alap egyenleg helyesen inicializálódik
        assertEquals(100000, finance.getBalance());
    }

    @Test
    void testIncrease() {
        // Növeljük az egyenleget
        finance.increase(5000);
        assertEquals(105000, finance.getBalance());

        // Növeljük egy másik összeggel
        finance.increase(2500);
        assertEquals(107500, finance.getBalance());
    }

    @Test
    void testDecrease() {
        // Csökkentsük az egyenleget
        finance.decrease(10000);
        assertEquals(90000, finance.getBalance());

        // Csökkentsük egy másik összeggel
        finance.decrease(5000);
        assertEquals(85000, finance.getBalance());
    }

    @Test
    void testGetBalance() {
        // Ellenőrizzük az aktuális egyenleg lekérdezését
        assertEquals(100000, finance.getBalance());

        // Növeljük az egyenleget, majd ellenőrizzük újra
        finance.increase(20000);
        assertEquals(120000, finance.getBalance());

        // Csökkentsük az egyenleget, majd ellenőrizzük újra
        finance.decrease(5000);
        assertEquals(115000, finance.getBalance());
    }
    
}
