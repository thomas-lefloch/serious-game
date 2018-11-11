package Game.Actor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import Vector.Vector2D;

public class Garbage implements GameActor {

    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;

    private int levelWidth;
    private int levelHeight;
    private final double maxSpeed = 10;
    private double radius;

    private Paint color;

    public Garbage(double x, double y, double radius, int levelWidth, int levelHeight) {
        this.color = new Paint();
        this.color.setColor(Color.WHITE);
        this.position = new Vector2D(x, y);
        this.velocity = new Vector2D(0, 0);
        this.acceleration = new Vector2D(0, 0);
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        this.radius = radius;
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

    /**
     * change the location of the current object
     * 
     * @param x the new x location
     * @param y the new y location
     */
    public void setLocation(double x, double y) {
        position.setValue(x, y);
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
        Vector2D movingDirection = velocity.normalize();
        double movingX = maxSpeed * movingDirection.getX();
        double movingY = maxSpeed * movingDirection.getY();

        // x et y < maxSpeed
        if (Math.abs(velocity.getX()) < Math.abs(movingX) && Math.abs(velocity.getY()) < Math.abs(movingY)) {
            move(velocity.getX(), velocity.getY());
            velocity.setValue(0, 0);
        }
        // x < maxSpeed et y > maxSpeed
        else if (Math.abs(velocity.getX()) < Math.abs(movingX)) {
            move(velocity.getX(), movingY);
            velocity.add(-velocity.getX(), -movingY);
        }
        // y < maxSpeed et x > maxSpeed
        else if (Math.abs(velocity.getY()) < Math.abs(movingY)) {
            move(movingX, velocity.getY());
            velocity.add(-movingX, -velocity.getY());
        }
        // x et y > maxSpeed
        else {
            move(movingX, movingY);
            velocity.add(-movingX, -movingY);
        }
    }

    /**
     * move the garbage position by an x offset and an y offset
     * 
     * @param xOffset offset to move the x coordinates
     * @param yOffset offset to move the y coordinates
     */
    public void move(double xOffset, double yOffset) {// TODO set to private (public for tests)
        position.add(xOffset, yOffset);
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
            velocity.multiplyY(-1);
        }
    }

    public boolean isOutOfLevelBounds() {
        return ((position.getX() - radius < 0) ||
                (position.getX() + radius >= levelWidth) ||
                (position.getY() - radius < 0) ||
                (position.getY() + radius >= levelHeight));
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

    public double getRadius() {
        return this.radius;
    }
}
