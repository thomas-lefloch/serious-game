package Game.Actor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import Game.Hitbox.CircleHitbox;
import Game.Hitbox.Hitbox;
import Game.Hitbox.RectangleHitbox;
import Vector.Vector2D;

public class Garbage implements GameActor {

    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;

    private int levelWidth;
    private int levelHeight;
    private double radius;

    private GarbageState state;

    private static final double slowingFactor = 0.99;
    private static final double velMinLimit = 3.9;

    private Paint color;

    private ArrayList<Trashcan> bins;

    private final int maxSpeed = 5;

    public Garbage(double x, double y, double radius, int levelWidth, int levelHeight, ArrayList<Trashcan> bins) {
        this.color = new Paint();
        this.color.setColor(Color.WHITE);
        this.position = new Vector2D(x, y);
        this.velocity = new Vector2D(0, 0);
        this.acceleration = new Vector2D(0, 0);
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        this.radius = radius;
        this.state = GarbageState.STANDBY;
        this.bins =  bins;

    }

    @Override
    public void update() {
        if (!acceleration.isAZeroVector()) {
            velocity.add(acceleration);
            acceleration.setValue(0, 0);
        }
        if (!velocity.isAZeroVector()) {
            updatePosition();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        color.setColor(Color.WHITE);
        if (state.equals(GarbageState.LAUNCHED)) {
            color.setColor(Color.CYAN);
        }
        if (state.equals(GarbageState.STOPPED)) {
            color.setColor(Color.RED);
        }
        canvas.drawCircle((float) position.getX(), (float) position.getY(), (float) radius, color);
    }



    /**
     * "apply a force" to the current garbage
     * 
     * @param power               the intensity of the force
     * @param NormalizedDirection the direction of the force. the vector must be normalized
     */
    public void applyForce(double power, Vector2D NormalizedDirection) {
        assert !NormalizedDirection.isAZeroVector() : "direction is a zero vector";
        assert Math.abs(NormalizedDirection.getSquaredMagnitude() - 1) < 0.001 : "Direction is not normalized";

        acceleration.add(power * NormalizedDirection.getX(), power * NormalizedDirection.getY());
    }

    /**
     * update the position of the garbage in function of the velocity
     */
    public void updatePosition() { // TODO set to private (public for tests)
        double xMove = velocity.getX();
        double yMove = velocity.getY();
        //TODO effectuer les check de collision plusieurs fois par update();
        move(velocity.getX(), velocity.getY());

        updateVelocity();
    }

    public void updateVelocity() {
        velocity.multiply(slowingFactor);
    }

    /**
     * move the garbage position by an x offset and an y offset
     * 
     * @param xOffset offset to move the x coordinates
     * @param yOffset offset to move the y coordinates
     */
    public void move(double xOffset, double yOffset) {// TODO set to private (public for tests)
        position.add(xOffset, yOffset);
        //

        for (int i = 0 ; i < bins.size(); i++) {
            RectangleHitbox leftHB = bins.get(i).getLeftHitbox();
            if (Hitbox.intersect(getHitbox(), leftHB)) {
                if (getVel().getX() <= 0) {
                    setX(leftHB.getX() + leftHB.getWidth() + getRadius());
                } else {
                    setX(leftHB.getX() - getRadius());
                }
                getVel().multiplyX(-1);
            }
            RectangleHitbox rightHB = bins.get(i).getRightHitbox();
            if (Hitbox.intersect(getHitbox(), rightHB)) {
                if (getVel().getX() <= 0) {
                    setX(rightHB.getX() + rightHB.getWidth() + getRadius());
                } else {
                    setX(rightHB.getX() - getRadius());
                }
                getVel().multiplyX(-1);
            }
            RectangleHitbox bottomHB = bins.get(i).getBottomHitbox();
            if (Hitbox.intersect(getHitbox(), bottomHB)) {
                if (velocity.getY() <= 0) {
                    setY(bottomHB.getY() + bottomHB.getHeight() + getRadius());
                } else {
                    setY(bottomHB.getY() - getRadius());
                }
                getVel().multiplyY(-1);
            }
            RectangleHitbox winningHB = bins.get(i).getWinningHitbox();
            if (Hitbox.intersect(getHitbox()    , winningHB)) {
                velocity.setValue(0, 0);
                setState(GarbageState.STOPPED);
            }
        }

        //collision limite du niveau
        if (position.getX() - radius < 0) {
            position.setX(radius);
            velocity.multiplyX(-1);
        }
        if (position.getX() + radius >= levelWidth) {
            position.setX(levelWidth - 1 - radius);
            velocity.multiplyX(-1);
        }
        if (position.getY() - radius < 0) {
            position.setY(radius);
            velocity.multiplyY(-1);
        }
        if (position.getY() + radius >= levelHeight) {
            position.setY(levelHeight - 1 - radius);
            if ((Math.abs(velocity.getY()) < velMinLimit)) {
                velocity.setY(0);
                state = GarbageState.STOPPED;
            }
            else {
                velocity.multiplyY(-1);
            }
        }
    }
    /**
     * change the location of the current object
     *
     * @param x the new x location
     * @param y the new y location
     */
    public void setLocation(double x, double y) {
        position.setValue(x, y);
    }

    public void setX(double x) {
        position.setX(x);
    }

    public void setY(double y) {
        position.setY(y);
    }

    public Vector2D getPos() {
        return this.position;
    }

    public Vector2D getAcc() {
        return this.acceleration;
    }

    public Vector2D getVel() {
        return this.velocity;
    }

    public void setVel(Vector2D v) {
        velocity = v;
    }

    public double getRadius() {
        return this.radius;
    }

    public GarbageState getState() {
        return state;
    }

    public void setState(GarbageState state) {
        this.state = state;
    }

    public static double getSlowingFactor() {
        return slowingFactor;
    }

    public static double getVelMinLimit() {
        return velMinLimit;
    }

    public CircleHitbox getHitbox() {
        return new CircleHitbox(position, radius);
    }
}
