package Game;

import android.graphics.Canvas;
import android.view.MotionEvent;

public abstract class Level {
    public abstract boolean onTouchEvent(MotionEvent event);
    public abstract void update();
    public abstract void draw(Canvas canvas);
}
