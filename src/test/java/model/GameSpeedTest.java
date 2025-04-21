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
public class GameSpeedTest {

    private GameSpeed gameSpeed;

    @BeforeEach
    void setUp() {
        gameSpeed = new GameSpeed();
    }

    @Test
    void testConstructor() {
        // Ellenőrizzük, hogy az alapértelmezett értékek helyesek
        assertEquals(1, gameSpeed.getMulti());
        assertTrue(gameSpeed.getElapsedTimeInSeconds() >= 0);
    }

    @Test
    void testChangeGameSpeed() throws InterruptedException {
        // Alapértelmezett sebesség
        assertEquals(1, gameSpeed.getMulti());

        // Timer indítása
        gameSpeed.startTimer();
        Thread.sleep(100);

        // Sebességváltás
        gameSpeed.changeGameSpeed(2);
        assertEquals(2, gameSpeed.getMulti());

        // Ellenőrizzük, hogy az eltelt idő helyesen frissül
        long elapsedTimeBefore = gameSpeed.getElapsedTimeInSeconds();
        Thread.sleep(100);
        long elapsedTimeAfter = gameSpeed.getElapsedTimeInSeconds();
        assertTrue(elapsedTimeAfter >= elapsedTimeBefore);
    }

    @Test
    void testStartTimer() throws InterruptedException {
        // Timer indítása
        gameSpeed.startTimer();
        Thread.sleep(200); // Növeljük a várakozási időt

        // Ellenőrizzük, hogy az eltelt idő növekszik
        long elapsedTimeBefore = gameSpeed.getElapsedTimeInSeconds();
        Thread.sleep(200); // További várakozás
        long elapsedTimeAfter = gameSpeed.getElapsedTimeInSeconds();
        assertTrue(elapsedTimeAfter >= elapsedTimeBefore);
    }

    @Test
    void testStopTimer() throws InterruptedException {
        // Timer indítása
        gameSpeed.startTimer();
        Thread.sleep(100);

        // Timer leállítása
        gameSpeed.stopTimer();
        long elapsedTime = gameSpeed.getElapsedTimeInSeconds();

        // Ellenőrizzük, hogy az eltelt idő nem növekszik tovább
        Thread.sleep(100);
        assertEquals(elapsedTime, gameSpeed.getElapsedTimeInSeconds());
    }

    @Test
    void testGetElapsedTimeInSeconds() throws InterruptedException {
        // Ellenőrizzük, hogy az eltelt idő helyesen számolódik
        gameSpeed.startTimer();
        Thread.sleep(1000);
        long elapsedTime = gameSpeed.getElapsedTimeInSeconds();
        assertTrue(elapsedTime >= 1);
    }

    @Test
    void testGetFormattedTime() throws InterruptedException {
        // Ellenőrizzük, hogy az idő helyesen formázódik
        gameSpeed.startTimer();
        Thread.sleep(2000); // 2 másodperc
        String formattedTime = gameSpeed.getFormattedTime();
        assertTrue(formattedTime.startsWith("00:02") || formattedTime.startsWith("00:03"));
    }

}
