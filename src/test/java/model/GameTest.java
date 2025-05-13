package model;

import view.Playing; // <-- EZ HIÁNYZOTT!
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameTest {

    private Game game;
    private Playing playingMock;

    @BeforeEach
    void setUp() {
        playingMock = mock(Playing.class, RETURNS_DEEP_STUBS);
        game = Mockito.spy(new Game(DifficultyLevel.EASY, "TestSafari"));
        doReturn(playingMock).when(game).getPlaying();
    }

    void runCheckGameEndExpectExit() throws Exception {
        SecurityManager originalSecurityManager = System.getSecurityManager();
        System.setSecurityManager(new SecurityManager() {
            @Override
            public void checkExit(int status) {
                throw new SecurityException("System.exit called");
            }
            @Override
            public void checkPermission(java.security.Permission perm) {}
        });
        try {
            assertThrows(SecurityException.class, () -> {
                var method = Game.class.getDeclaredMethod("checkGameEnd");
                method.setAccessible(true);
                method.invoke(game);
            });
        } finally {
            System.setSecurityManager(originalSecurityManager);
        }
    }

    @Test
    void testCheckGameEndLoseCondition() throws Exception {
        when(playingMock.gameMap.gazelles.size()).thenReturn(0);
        when(playingMock.gameMap.elephants.size()).thenReturn(0);
        when(playingMock.gameMap.lions.size()).thenReturn(0);
        when(playingMock.gameMap.cheetahs.size()).thenReturn(0);
        doReturn(new LinkedList<Tourist>()).when(game).getVisitors();
        runCheckGameEndExpectExit();
    }

    @Test
    void testCheckGameEndWinConditionEasy() throws Exception {
        when(playingMock.gameMap.gazelles.size()).thenReturn(3);
        when(playingMock.gameMap.elephants.size()).thenReturn(2);
        when(playingMock.gameMap.lions.size()).thenReturn(3);
        when(playingMock.gameMap.cheetahs.size()).thenReturn(2);
        when(playingMock.getFinance().getBalance()).thenReturn(50000.0);
        when(playingMock.gameMap.getJeeps().size()).thenReturn(1);

        Queue<Tourist> visitors = new LinkedList<>();
        for (int i = 0; i < 25; i++) visitors.add(new Tourist());
        doReturn(visitors).when(game).getVisitors();

        runCheckGameEndExpectExit();
    }

    @Test
    void testCheckGameEndWinConditionMedium() throws Exception {
        Game mediumGame = Mockito.spy(new Game(DifficultyLevel.MEDIUM, "TestSafari"));
        Playing mediumPlayingMock = mock(Playing.class, RETURNS_DEEP_STUBS);
        doReturn(mediumPlayingMock).when(mediumGame).getPlaying();

        when(mediumPlayingMock.gameMap.gazelles.size()).thenReturn(5);
        when(mediumPlayingMock.gameMap.elephants.size()).thenReturn(5);
        when(mediumPlayingMock.gameMap.lions.size()).thenReturn(5);
        when(mediumPlayingMock.gameMap.cheetahs.size()).thenReturn(5);
        when(mediumPlayingMock.getFinance().getBalance()).thenReturn(150000.0);
        when(mediumPlayingMock.gameMap.getJeeps().size()).thenReturn(2);

        Queue<Tourist> visitors = new LinkedList<>();
        for (int i = 0; i < 100; i++) visitors.add(new Tourist());
        doReturn(visitors).when(mediumGame).getVisitors();

        SecurityManager originalSecurityManager = System.getSecurityManager();
        System.setSecurityManager(new SecurityManager() {
            @Override
            public void checkExit(int status) {
                throw new SecurityException("System.exit called");
            }
            @Override
            public void checkPermission(java.security.Permission perm) {}
        });
        try {
            assertThrows(SecurityException.class, () -> {
                var method = Game.class.getDeclaredMethod("checkGameEnd");
                method.setAccessible(true);
                method.invoke(mediumGame);
            });
        } finally {
            System.setSecurityManager(originalSecurityManager);
        }
    }

    @Test
    void testCheckGameEndWinConditionHard() throws Exception {
        Game hardGame = Mockito.spy(new Game(DifficultyLevel.HARD, "TestSafari"));
        Playing hardPlayingMock = mock(Playing.class, RETURNS_DEEP_STUBS);
        doReturn(hardPlayingMock).when(hardGame).getPlaying();

        when(hardPlayingMock.gameMap.gazelles.size()).thenReturn(13);
        when(hardPlayingMock.gameMap.elephants.size()).thenReturn(12);
        when(hardPlayingMock.gameMap.lions.size()).thenReturn(13);
        when(hardPlayingMock.gameMap.cheetahs.size()).thenReturn(12);
        when(hardPlayingMock.getFinance().getBalance()).thenReturn(300000.0);
        when(hardPlayingMock.gameMap.getJeeps().size()).thenReturn(3);

        Queue<Tourist> visitors = new LinkedList<>();
        for (int i = 0; i < 500; i++) visitors.add(new Tourist());
        doReturn(visitors).when(hardGame).getVisitors();

        SecurityManager originalSecurityManager = System.getSecurityManager();
        System.setSecurityManager(new SecurityManager() {
            @Override
            public void checkExit(int status) {
                throw new SecurityException("System.exit called");
            }
            @Override
            public void checkPermission(java.security.Permission perm) {}
        });
        try {
            assertThrows(SecurityException.class, () -> {
                var method = Game.class.getDeclaredMethod("checkGameEnd");
                method.setAccessible(true);
                method.invoke(hardGame);
            });
        } finally {
            System.setSecurityManager(originalSecurityManager);
        }
    }

    @Test
    void testCheckGameEndNoEndCondition() throws Exception {
        // Nincs se win, se lose feltétel
        when(playingMock.gameMap.gazelles.size()).thenReturn(2);
        when(playingMock.gameMap.elephants.size()).thenReturn(2);
        when(playingMock.gameMap.lions.size()).thenReturn(2);
        when(playingMock.gameMap.cheetahs.size()).thenReturn(2);
        when(playingMock.getFinance().getBalance()).thenReturn(10000.0);
        when(playingMock.gameMap.getJeeps().size()).thenReturn(1);

        Queue<Tourist> visitors = new LinkedList<>();
        for (int i = 0; i < 10; i++) visitors.add(new Tourist());
        doReturn(visitors).when(game).getVisitors();

        // Nem dobhat kivételt, mert nincs végállapot
        var method = Game.class.getDeclaredMethod("checkGameEnd");
        method.setAccessible(true);
        assertDoesNotThrow(() -> method.invoke(game));
    }

    @Test
    void testGetVisitorsReturnsQueue() {
        Queue<Tourist> queue = game.getVisitors();
        assertNotNull(queue);
        assertTrue(queue instanceof Queue);
    }

    @Test
    void testGetGameSpeedReturnsInstance() {
        GameSpeed speed = game.getGameSpeed();
        assertNotNull(speed);
        assertTrue(speed instanceof GameSpeed);
    }

    @Test
    void testStartJeepWithNull() {
        // Should not throw exception
        assertDoesNotThrow(() -> game.startJeep(null));
    }

    @Test
    void testStartJeepWithJeep() {
        Jeep jeep = mock(Jeep.class);
        assertDoesNotThrow(() -> game.startJeep(jeep));
        verify(jeep, times(1)).startMoving();
    }

    @Test
    void testGetPlayingReturnsPlaying() {
        assertEquals(playingMock, game.getPlaying());
    }

    // A run() metódus végtelen ciklus, ezért csak azt teszteljük, hogy Thread elindul
    @Test
    void testGameThreadStartsOnConstructor() throws Exception {
        Game g = Mockito.spy(new Game(DifficultyLevel.EASY, "ThreadTest"));
        Thread.sleep(100); // adjunk időt a threadnek elindulni
        // Ellenőrizzük, hogy a thread él
        Thread thread = (Thread) Game.class.getDeclaredField("gameThread").get(g);
        assertTrue(thread.isAlive());
        thread.stop(); // leállítás, hogy ne fusson tovább
    }
}
