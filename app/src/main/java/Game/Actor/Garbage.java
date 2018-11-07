package Game.Actor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import Vector.Vector2D;

public class Garbage implements GameActor{

    private Vector2D pos;
    private double radius;

    private Paint color;

    public Garbage (double x, double y, double radius) {
        this.color = new Paint();
        this.color.setColor(Color.WHITE);
        this.pos = new Vector2D(x, y);
        this.radius = radius;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float) this.pos.getX(), (float) this.pos.getY(), (float) this.radius, this.color);
    }

    /**
     * change the location of the current object
     * @param x the new x location
     * @param y the new y location
     */
    public void setLocation(double x, double y) {
        this.pos.setX(x);
        this.pos.setY(y);
    }

    public Vector2D getPos() {
        return this.pos;
    }

    public double getRadius() {
        return this.radius;
    }
}
