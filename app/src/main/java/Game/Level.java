package Game;

import android.graphics.Canvas;
import android.view.MotionEvent;

public abstract class Level {

    protected int levelWidth;
    protected int levelHeight;

    public Level (int width, int height) {
        this.levelWidth = width;
        this.levelHeight = height;
    }

    public abstract boolean onTouchEvent(MotionEvent event);
    public abstract void update();
    public abstract void draw(Canvas canvas);
}
