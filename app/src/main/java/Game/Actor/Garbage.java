package Game.Actor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.ObjectStreamClass;
import java.util.ArrayList;

import Game.Hitbox.CircleHitbox;
import Game.Hitbox.Hitbox;
import Game.Hitbox.HitboxController;
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
    private ArrayList<Obstacle> obstacles;

    private GarbageType type;

    private boolean isInTheRightBin;

    public Garbage(double x, double y, double radius, int levelWidth, int levelHeight, ArrayList<Trashcan> bins, ArrayList<Obstacle> obs, GarbageType type) {
        this.position = new Vector2D(x, y);
        this.velocity = new Vector2D(0, 0);
        this.acceleration = new Vector2D(0, 0);

        this.radius = radius;

        this.bins =  bins;
        this.obstacles = obs;

        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;

        this.state = GarbageState.STANDBY;

        this.type = type;

        this.color = new Paint();
        this.color.setColor(chooseColor(type));

        this.isInTheRightBin = false;
    }

    /**
     * return a color based on the type of garbage passed in the parameter
     * @param t the type of garbage
     * @return the color
     */
    private int chooseColor(GarbageType t){
        if (t.equals(GarbageType.RECYCLABLE)) {
            return Color.YELLOW;
        } else if (t.equals(GarbageType.GLASS)) {
            return  Color.WHITE;
        } else {
            return Color.GREEN;
        }
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
        canvas.drawCircle((float) position.getX(), (float) position.getY(), (float) radius, color);
    }

    public void drawMin(Canvas canvas, float x, float y, float scalingFactor) {
        canvas.drawCircle(x, y, (float) radius * scalingFactor, color);
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
    //TODO refactor this code
    public void move(double xOffset, double yOffset) {// TODO set to private (public for tests)
        position.add(xOffset, yOffset);

        checkAndResolveBinsCollision();

        checkAndResolveBoundsCollision();

        checkAndResolveObsCollsion();
    }

    public void checkAndResolveBinsCollision() {
        for (int i = 0 ; i < bins.size(); i++) {
            RectangleHitbox leftHB = bins.get(i).getLeftHitbox();
            if (HitboxController.intersect(getHitbox(), leftHB)) {
                if (getVel().getX() <= 0) {
                    setX(leftHB.getX() + leftHB.getWidth() + getRadius());
                } else {
                    setX(leftHB.getX() - getRadius());
                }
                getVel().multiplyX(-1);
            }
            RectangleHitbox rightHB = bins.get(i).getRightHitbox();
            if (HitboxController.intersect(getHitbox(), rightHB)) {
                if (getVel().getX() <= 0) {
                    setX(rightHB.getX() + rightHB.getWidth() + getRadius());
                } else {
                    setX(rightHB.getX() - getRadius());
                }
                getVel().multiplyX(-1);
            }
            RectangleHitbox bottomHB = bins.get(i).getBottomHitbox();
            if (HitboxController.intersect(getHitbox(), bottomHB)) {
                if (velocity.getY() <= 0) {
                    setY(bottomHB.getY() + bottomHB.getHeight() + getRadius());
                } else {
                    setY(bottomHB.getY() - getRadius());
                }
                getVel().multiplyY(-1);
            }
            RectangleHitbox winningHB = bins.get(i).getWinningHitbox();
            if (HitboxController.intersect(getHitbox(), winningHB)) {
                velocity.setValue(0, 0);
                if (bins.get(i).getType().equals(this.type)) {
                    setState(GarbageState.STOPPED);
                    isInTheRightBin = true;
                }
                else {
                    position.setValue(winningHB.getX() + winningHB.getWidth()/2, winningHB.getY() - radius);
                    double forceX = Math.random() * (0.85 + 0.85) - 0.85;
                    applyForce(40, new Vector2D(forceX, -1).normalize());
                }
            }
        }
    }

    public void checkAndResolveObsCollsion() {
        for (int i = 0; i < obstacles.size(); i++) {
            RectangleHitbox obsHitbox = obstacles.get(i).getHitbox() ;
            if (HitboxController.intersect(getHitbox(), obsHitbox)) {
                if (getVel().getX() <= 0) {
                    setX(obsHitbox.getX() + obsHitbox.getWidth() + getRadius());
                } else {
                    setX(obsHitbox.getX() - getRadius());
                }
                getVel().multiplyX(-1);
            }
        }
    }

    public void checkAndResolveBoundsCollision() {
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

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
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

    public boolean isInTheRightBin() {
        return isInTheRightBin;
    }
}
