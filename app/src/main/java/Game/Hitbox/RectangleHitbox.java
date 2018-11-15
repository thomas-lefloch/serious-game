package Game.Hitbox;

import android.graphics.Canvas;
import android.graphics.Paint;

import Vector.Vector2D;

public class RectangleHitbox implements Hitbox {

    private Vector2D position;
    private double width;
    private double height;

    public RectangleHitbox(Vector2D pos, double w, double h) {
        this.position = pos;
        this.width = w;
        this.height = h;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public Vector2D getCenter() {
        return new Vector2D(this.position.getX() + this.width/2, this.position.getY() + this.height/2);
    }
}
