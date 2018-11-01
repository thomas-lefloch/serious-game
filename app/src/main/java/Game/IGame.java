package Game;

import android.graphics.Canvas;

public interface IGame {

    /**
     * called only at the start
     * set the game state
     */
    void setup();
    /**
     * called every frame
     * update the game's state
     */
    void update();
    /**
     * called every frame
     * draw the actual game's state
     */
    void draw(Canvas canvas);
}
