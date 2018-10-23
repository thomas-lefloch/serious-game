package Game;

public class GameRunner extends Thread {

    private boolean running;

    public GameRunner() {
        this.running = true;
        this.setup();
    }

    /**
     * initialise the game's state like the level ...
     */
    private void setup() {

    }

    /**
     * called every frame
     * update the game's state
     */
    private void update() {

    }

    /**
     * called every frame
     * draw the actual game's state
     */
    private void draw() {

    }

    @Override
    public void run() {
        while (running) {
            this.update();
            this.draw();
        }
    }
}
