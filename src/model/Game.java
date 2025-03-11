package model;

public class Game {
    private DifficultyLevel difficulty;
    private SafariPark park;
    private Finance finance;
    private GameState state;

    public Game(DifficultyLevel level) {
        this.difficulty = level;
        this.park = new SafariPark();
        this.finance = new Finance();
        this.state = GameState.READY;
    }
    
    public void startGame() {
        state = GameState.RUNNING;
    }
    
    public void endGame() {
        state = GameState.ENDED;
    }
    
    public void checkWinCondition() {
        // Ellenőrzi a nyerési feltételeket
    }
}

enum GameState {
    READY, RUNNING, ENDED;
}

enum DifficultyLevel {
    EASY, MEDIUM, HARD;
}
