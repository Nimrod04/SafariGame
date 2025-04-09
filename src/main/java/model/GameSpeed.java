package model;

public class GameSpeed {
    private long startTime;
    private long elapsedTime;

    public GameSpeed() {
        this.startTime = 0;
        this.elapsedTime = 0;
    }

    public void startTimer() {
        this.startTime = System.currentTimeMillis();
    }

    public void stopTimer() {
        this.elapsedTime = System.currentTimeMillis() - startTime;
    }

    public long getElapsedTimeInSeconds() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public String getFormattedTime() {
        long seconds = getElapsedTimeInSeconds();
        long minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}