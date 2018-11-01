package Game;

import android.content.Context;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class GameViewer extends SurfaceView implements SurfaceHolder.Callback {

    private GameRunner runner;

    public GameViewer (Context context) {
        super(context);

        getHolder().addCallback(this);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        runner = new GameRunner(holder);
        runner.setRunning(true);
        runner.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                runner.setRunning(false);
                runner.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }
}
