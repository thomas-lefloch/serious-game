package Game;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class Game implements IGame {

    private ArrayList<Level> levels;
    private Level selectedLevel;

    public Game(int width, int height) {
        // Initializing levels
        levels = new ArrayList<Level>();
        levels.add(new FirstLevel(width, height));


        //only one level for the moment
        selectedLevel = levels.get(0);
    }

    /**
     * initialise the game's state like the level ...
     */
    @Override
    public void setup() {

    }

    /**
     * called every frame
     * update the game's state
     */
    @Override
    public void update() {
        selectedLevel.update();
    }

    /**
     * called every frame
     * draw the actual game's state
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawRGB(40,40,40);
        selectedLevel.draw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return selectedLevel.onTouchEvent(event);
    }
}
