package Game;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;

public class GameViewer extends SurfaceView implements SurfaceHolder.Callback {

    private GameRunner runner;

    public GameViewer (Context context) {
        super(context);

        getHolder().addCallback(this);

        setFocusable(true);

        setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return runner.onTouchEvent(event);
    }
}
