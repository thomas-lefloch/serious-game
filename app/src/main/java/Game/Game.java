package Game;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Game implements IGame {

    private ArrayList<ILevel> levels;
    private ILevel selectedLevel;

    public Game() {
        // Initializing levels
        this.levels = new ArrayList<ILevel>();
        this.levels.add(new FirstLevel());


        //only one level for the moment
        this.selectedLevel = this.levels.get(0);
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
}
