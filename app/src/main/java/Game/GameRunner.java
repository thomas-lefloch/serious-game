package Game;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class GameRunner extends Thread {

    private boolean running;
    private IGame game; /*Game's state*/
    private final SurfaceHolder surfaceHolder;

    public GameRunner(SurfaceHolder surfaceHolder) {
        super();

        this.surfaceHolder = surfaceHolder;

        this.game = new Game();
        this.running = false;

        game.setup();
    }

    @Override
    public void run() {
        int frameCount = 0;
        long totalTime = 0;
        Canvas canvas = null;

        while (running) {

            long startTime = 0;
            try {
                startTime = System.nanoTime();

                canvas = this.surfaceHolder.lockCanvas();

                synchronized (surfaceHolder) {
                    this.game.update();
                    this.game.draw(canvas);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }   catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if (totalTime >= 1000000000) {// 1 sec in ns
                totalTime = 0;
                System.out.println("FPS : " + frameCount);
                frameCount = 0;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        return game.onTouchEvent(event);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
