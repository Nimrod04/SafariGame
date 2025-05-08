/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import org.junit.jupiter.api.Test;

import view.GamePanel;
import view.Playing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
// vagy
import static org.mockito.Mockito.atLeastOnce;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author nimro
 */
public class GameTest {
    private Game game;
    private Playing mockPlaying;
    private GamePanel mockGamePanel;
    private GameMap mockGameMap;
    private GameSpeed mockGameSpeed;

    @BeforeEach
    public void setUp() throws Exception {
        // Mock objektumok létrehozása
        mockPlaying = mock(Playing.class);
        mockGamePanel = mock(GamePanel.class);
        mockGameMap = mock(GameMap.class);
        mockGameSpeed = mock(GameSpeed.class);

        // Mock viselkedések beállítása
        when(mockPlaying.getGamePanel()).thenReturn(mockGamePanel);
        when(mockPlaying.getJPanel()).thenReturn(mockGamePanel);
        when(mockGamePanel.getGameMap()).thenReturn(mockGameMap);
        when(mockGameMap.getJeeps()).thenReturn(new ArrayList<>());
        when(mockGameMap.getJeepQueue()).thenReturn(new LinkedList<>()); // Add this
        when(mockGameMap.getAllAnimals()).thenReturn(new ArrayList<>());
        doNothing().when(mockPlaying).setSafariName(anyString());
        doNothing().when(mockPlaying).setVisible(anyBoolean());
        doNothing().when(mockPlaying).changeVisitorCount(anyInt(), anyInt());
        doNothing().when(mockPlaying).updateTime(anyString());

        // Game példány létrehozása
        game = new Game(DifficultyLevel.EASY, "TestSafari");

        // Reflection használata a private mezők beállításához
        setPrivateField(game, "playing", mockPlaying);
        setPrivateField(game, "gameSpeed", mockGameSpeed);

        // Set gameMap in Playing mock using reflection
        Field gameMapField = Playing.class.getDeclaredField("gameMap");
        gameMapField.setAccessible(true);
        gameMapField.set(mockPlaying, mockGameMap);
    }

    private void setPrivateField(Object object, String fieldName, Object value) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }

    @Test
    public void testConstructor() {
        Game newGame = new Game(DifficultyLevel.EASY, "TestSafari");
        assertNotNull(newGame);
        assertNotNull(newGame.getGameSpeed());
        assertNotNull(newGame.getVisitors());
        assertTrue(newGame.getVisitors().isEmpty());
    }

    @Test
    public void testUpdate_AddVisitor2() {
        // Given
        when(mockGameSpeed.getElapsedTimeInSeconds()).thenReturn(6L);
        when(mockGameSpeed.getFormattedTime()).thenReturn("00:06");
        
        // When
        game.update();
        
        // Then
        assertFalse(game.getVisitors().isEmpty());

        //Ez itt hiba lehet!!!
        //#

        assertEquals(1, game.getVisitors().size());
        verify(mockPlaying, atLeastOnce()).changeVisitorCount(anyInt(), anyInt());
        //verify(mockPlaying, times(3)).updateTime("00:06"); // verify exactly 3 calls
    }

    @Test
    public void testUpdate_AddVisitor() {
        // Given
        when(mockGameSpeed.getElapsedTimeInSeconds()).thenReturn(6L); // 6 másodperc telt el
        when(mockGameSpeed.getFormattedTime()).thenReturn("00:06");

        // When
        game.update(); // Meghívjuk az update-et

        // Then
        assertFalse(game.getVisitors().isEmpty()); // Nem üres a látogatók listája
        assertEquals(1, game.getVisitors().size()); // Pontosan 1 látogató került bele
        verify(mockPlaying).changeVisitorCount(anyInt(), anyInt()); // Frissült a látogatók számlálója
        verify(mockPlaying).updateTime("00:06"); // Frissült az idő kijelzése
    }

    @Test
    public void testUpdate_NoVisitorAdditionBeforeTime() {
        when(mockGameSpeed.getElapsedTimeInSeconds()).thenReturn(2L); // Kevesebb mint 5 másodperc

        assertTrue(game.getVisitors().isEmpty());
    }

    @Test
    public void testStartJeep_Valid() {
        Jeep mockJeep = mock(Jeep.class);
        game.startJeep(mockJeep);
        verify(mockJeep).startMoving();
    }

    @Test
    public void testStartJeep_Null() {
        assertDoesNotThrow(() -> game.startJeep(null));
    }

    @Test
    public void testGetGameSpeed() {
        GameSpeed speed = game.getGameSpeed();
        assertNotNull(speed);
    }

    @Test
    public void testGetVisitors() {
        assertNotNull(game.getVisitors());
    }

    @Test
    public void testConstants() {
        assertEquals(16, Game.TILES_DEFAULT_SIZE);
        assertEquals(4.0f, Game.SCALE);
        assertEquals(20, Game.TILES_IN_WIDTH);
        assertEquals(12, Game.TILES_IN_HEIGHT);
        assertEquals(64, Game.TILES_SIZE);
        assertEquals(1280, Game.GAME_WIDTH);
        assertEquals(768, Game.GAME_HEIGHT);
    }

    @Test
    public void testUpdate_AnimalDetection() {
        // Animal mock setup
        Animal mockAnimal = mock(Animal.class);
        when(mockAnimal.getHitbox()).thenReturn(new java.awt.Rectangle());

        // Jeep mock setup
        Jeep mockJeep = mock(Jeep.class);
        when(mockJeep.getHitbox()).thenReturn(new java.awt.Rectangle());

        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(mockAnimal);
        ArrayList<Jeep> jeeps = new ArrayList<>();
        jeeps.add(mockJeep);

        when(mockGameMap.getAllAnimals()).thenReturn(animals);
        when(mockGameMap.getJeeps()).thenReturn(jeeps);

    }
}
