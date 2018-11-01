package Game;

import android.graphics.Canvas;

import Game.Actor.Garbage;

public class FirstLevel implements ILevel {

    private Garbage g;

    public FirstLevel() {
        g = new Garbage(200,400,25);
    }

    @Override
    public void update() {
        g.update();
    }

    @Override
    public void draw(Canvas canvas) {
        g.draw(canvas);
    }
}
