package Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;

import Game.Actor.Garbage;
import Game.Actor.GarbageState;
import Game.Actor.Trashcan;
import Vector.Vector2D;

public abstract class Level {

    protected ArrayList<Trashcan> bins;
    protected ArrayList<Garbage> garbages;
    protected Garbage currentGarbage;

    //indicates the starting point of all the garbages
    protected Vector2D launchLocation;

    //indicates the limit that the user can pull the garbage
    protected double radiusLimit;

    protected int levelWidth;
    protected int levelHeight;

    protected double gravity;

    protected Level (int width, int height) {
        this.levelWidth = width;
        this.levelHeight = height;
    }

    protected boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (touchedGarbage(event.getX(), event.getY()) &&
                    currentGarbage.getState().equals(GarbageState.STANDBY)) {
                //the event will be consumed
                return true;
            }
        }
        else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            Vector2D distanceStartEvent = new Vector2D(
                    event.getX() - launchLocation.getX(),
                    event.getY() - launchLocation.getY()
            );

            if (!isFullyCharged(distanceStartEvent)) {
                //coordinates are between the launching point and the circle delimiting the limit
                currentGarbage.setLocation(event.getX(), event.getY());
            }
            else {
                //the coordinates are beyond the circle delimiting the limits
                Vector2D direction = distanceStartEvent.normalize();
                currentGarbage.setLocation(
                        launchLocation.getX() + radiusLimit * direction.getX(),
                        launchLocation.getY() + radiusLimit * direction.getY()
                );
            }
            return true;
        }
        else if (event.getAction() == (MotionEvent.ACTION_UP)) {
            Vector2D direction = new Vector2D(
                    launchLocation.getX() - currentGarbage.getX(),
                    launchLocation.getY() - currentGarbage.getY()
            );

            double power = direction.getMagnitude() / 900;
            currentGarbage.applyForce(power, direction.normalize());
            currentGarbage.setState(GarbageState.LAUNCHED);
            return true;
        }
        //we don't care about the event, we will not consume it
        return false;
    }

    /**
     * check if the coordinates passed in the params are inside the launch parameters
     * @param distance the vector representing the distance between the touch point and the launch point
     * @return true if the new coordinates are valid false otherwise
     */
    private boolean isFullyCharged(Vector2D distance) {
        return distance.getSquaredMagnitude() >= radiusLimit;
    }

    /**
     * check if the garbage has been touched
     * @param x the x coordinates of the touch point
     * @param y the y coordinates of the touch point
     * @return true if the garbage is touched, false otherwise
     */
    private boolean touchedGarbage(double x, double y) {
        Vector2D distanceGarEvent = new Vector2D(currentGarbage.getX() - x, currentGarbage.getY() - y);
        return distanceGarEvent.getSquaredMagnitude() < currentGarbage.getRadius() * 1.6;
    }

    protected Garbage nextGarbage() {
        int nextIndex = garbages.indexOf(currentGarbage) + 1;
        if (nextIndex >= garbages.size()) {
            garbages = initGarbages();
            return garbages.get(0);
        }
        return garbages.get(garbages.indexOf(currentGarbage) + 1);
    }

    public void draw(Canvas canvas){
        for (int i = 0; i < bins.size(); i++) {
            bins.get(i).draw(canvas);
        }

        //TODO draw garbages corrrectly
        //unused garbages on the top left reduce
        //used garbages on the
        currentGarbage.draw(canvas);

        if (currentGarbage.getState().equals(GarbageState.STANDBY)) {
            Paint p = new Paint();
            p.setColor(Color.WHITE);
            canvas.drawLine((float) currentGarbage.getX(),(float)  currentGarbage.getY(),(float)  launchLocation.getX(),(float)  launchLocation.getY(), p);
        }
    }

    public abstract void update();

    protected abstract ArrayList<Garbage> initGarbages();
    protected abstract ArrayList<Trashcan> initTrashcans();
}
