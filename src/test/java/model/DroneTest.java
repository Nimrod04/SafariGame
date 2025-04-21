/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author nimro
 */
public class DroneTest {
    
    private Drone drone;
    private GameSpeed gameSpeed;
    private ChargingStation chargingStation;
    private Coordinate initialPosition;

    @BeforeEach
    void setUp() {
        gameSpeed = new GameSpeed();
        chargingStation = new ChargingStation(new Coordinate(10, 10));
        initialPosition = new Coordinate(5, 5);
        drone = new Drone(initialPosition, chargingStation, gameSpeed);
    }

    @Test
    void testConstructor() {
        assertEquals(initialPosition, drone.getPosition());
        assertEquals(chargingStation, drone.getChargingStation());
        assertNotNull(drone.getWaypoints());
        assertTrue(drone.getWaypoints().isEmpty());
    }

    @Test
    void testSetPosition() {
        Coordinate newPosition = new Coordinate(8, 8);
        drone.setPosition(newPosition);
        assertEquals(newPosition, drone.getPosition());

        Rectangle hitbox = drone.getHitbox();
        assertEquals(7, hitbox.x); // 8 - 1 (HITBOX_RADIUS)
        assertEquals(7, hitbox.y); // 8 - 1 (HITBOX_RADIUS)
        assertEquals(3, hitbox.width); // HITBOX_SIZE
        assertEquals(3, hitbox.height); // HITBOX_SIZE
    }

    @Test
    void testSetChargingStation() {
        ChargingStation newStation = new ChargingStation(new Coordinate(15, 15));
        drone.setChargingStation(newStation);
        assertEquals(newStation, drone.getChargingStation());
    }

    @Test
    void testAddAndClearWaypoints() {
        Coordinate waypoint1 = new Coordinate(6, 6);
        Coordinate waypoint2 = new Coordinate(7, 7);

        drone.addWaypoint(waypoint1);
        drone.addWaypoint(waypoint2);

        List<Coordinate> waypoints = drone.getWaypoints();
        assertEquals(2, waypoints.size());
        assertEquals(waypoint1, waypoints.get(0));
        assertEquals(waypoint2, waypoints.get(1));

        drone.clearWaypoints();
        assertTrue(drone.getWaypoints().isEmpty());
    }

    @Test
    void testOrbitChargingStation() {
        // Teszteljük az orbitálást a töltőállomás körül
        drone.orbitChargingStation();
        Coordinate newPosition = drone.getPosition();
        assertEquals(9, newPosition.getPosX()); // 10 - 1 (első lépés az offsets alapján)
        assertEquals(8, newPosition.getPosY()); // 10 - 2

        // További lépések
        drone.orbitChargingStation();
        newPosition = drone.getPosition();
        assertEquals(9, newPosition.getPosX()); // 10 - 1 (második lépés)
        assertEquals(8, newPosition.getPosY()); // 10 - 2
    }

    @Test
    void testOrbitChargingStationWithDelay() throws InterruptedException {
        // Teszteljük a késleltetést
        drone.orbitChargingStation();
        Coordinate firstPosition = drone.getPosition();

        // Azonnali második hívás nem változtatja meg a pozíciót
        drone.orbitChargingStation();
        assertEquals(firstPosition, drone.getPosition());

        // Várakozás a késleltetés lejártáig
        Thread.sleep(1000 / gameSpeed.getMulti());
        drone.orbitChargingStation();
        assertNotEquals(firstPosition, drone.getPosition());
    }

    @Test
    void testDrawHitbox() {
        // Ellenőrizzük, hogy a drawHitbox metódus nem dob hibát
        Graphics g = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).getGraphics();
        drone.drawHitbox(g, 0, 0, 10);
    }

    @Test
    void testNoOrbitWithoutChargingStation() {
        // Állítsuk null-ra a töltőállomást
        drone.setChargingStation(null);

        Coordinate initialPos = drone.getPosition();
        drone.orbitChargingStation();

        // A pozíciónak változatlannak kell maradnia
        assertEquals(initialPos, drone.getPosition());
    }
    
}
