package model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JPanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import view.Playing;

public class GameTest {
    public class GameMapStub {

    public Queue<Jeep> getJeepQueue() {
        return new LinkedList<>();
    }

    public List<Jeep> getJeeps() {
        return List.of(); // vagy Arrays.asList() ha van elem
    }

    public List<Animal> getAllAnimals() {
        return List.of(); // Üres lista, ha nem akarjuk tesztelni most az állatokat
    }

    public int getWidth() {
        return 20;
    }

    public int getHeight() {
        return 15;
    }
}

    private Game game;

    @BeforeEach
    public void setUp() {
        // Mockoljuk a Playing osztályt, hogy ne nyisson GUI-t
        Playing mockPlaying = mock(Playing.class);
        //when(mockPlaying.getGamePanel()).thenReturn(mock(GamePanelStub.class));

        // Game példány létrehozása mockkal
        game = Mockito.spy(new Game(DifficultyLevel.EASY, "TestPark"));
        doReturn(mockPlaying).when(game).getPlaying();
    }

    @Test
    public void testGameSpeedIsInitialized() {
        GameSpeed speed = game.getGameSpeed();
        assertNotNull(speed);
    }

    @Test
    public void testVisitorQueueInitiallyEmpty() {
        Queue<Tourist> visitors = game.getVisitors();
        assertTrue(visitors.isEmpty());
    }

    @Test
    public void testStartJeep() {
        Jeep jeep = mock(Jeep.class);
        game.startJeep(jeep);
        verify(jeep, times(1)).startMoving();
    }
}
