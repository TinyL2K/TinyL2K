package sample;

import javafx.animation.AnimationTimer;

public class MyTimer extends AnimationTimer { //časovač
    private Game myGame;
    private long lastNanoTime;

    public MyTimer(Game mg) {
        myGame = mg;
    }

    @Override
    public void handle(long now) {
        myGame.update(now - lastNanoTime);
        lastNanoTime = now;
    }

    public void start() {
        lastNanoTime =  System.nanoTime();
        super.start();
    }

}
