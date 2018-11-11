package Game;

import android.graphics.Canvas;
import android.view.MotionEvent;


import Game.Actor.Garbage;
import Vector.Vector2D;

public class FirstLevel extends Level {

    private Garbage g;
    /**
     * indicates the starting point of all the garbages
     */
    private Vector2D launchLocation;
    /**
     * indicates the limit that the user can pull the garbage
     */
    private double radiusLimit;

    public FirstLevel(int width, int height) {
        super(width, height);

        this.launchLocation = new Vector2D(900, 400);
        this.g = new Garbage(launchLocation.getX(),launchLocation.getY(),25, this.levelWidth, this.levelHeight);
        this.radiusLimit = g.getRadius() * 5;
    }

    @Override
    public void update() {
        g.update();
    }

    @Override
    public void draw(Canvas canvas) {
        g.draw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (touchedGarbage(event.getX(), event.getY())) {
                //the event will be consumed
                return true;
            }
        }
        else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            Vector2D distanceStartEvent = new Vector2D(
                    event.getX() - launchLocation.getX(),
                    event.getY() - launchLocation.getY()
            );

            if (checksCoordinates(distanceStartEvent)) {
                //coordinates are between the launching point and the circle delimiting the limit
                g.setLocation(event.getX(), event.getY());
            }
            else {
                //the coordinates are beyond the circle delimiting the limits
                Vector2D direction = distanceStartEvent.normalize();
                g.setLocation(
                        launchLocation.getX() + radiusLimit * direction.getX(),
                        launchLocation.getY() + radiusLimit * direction.getY()
                );
            }
            return true;
        }
        else if (event.getAction() == (MotionEvent.ACTION_UP)) {
            Vector2D direction = new Vector2D(
                    launchLocation.getX() - event.getX(),
                    launchLocation.getY() - event.getY()
            );

            double power = direction.getMagnitude();
            g.applyForce(power, direction.normalize());
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
    private boolean checksCoordinates(Vector2D distance) {
        return distance.getSquaredMagnitude() < radiusLimit;
    }

    /**
     * check if the garbage has been touched
     * @param x the x coordinates of the touch point
     * @param y the y coordinates of the touch point
     * @return true if the garbage is touched, false otherwise
     */
    private boolean touchedGarbage(double x, double y) {
        Vector2D distanceGarEvent = new Vector2D(g.getPos().getX() - x, g.getPos().getY() - y);
        return distanceGarEvent.getSquaredMagnitude() < g.getRadius();
    }
}
