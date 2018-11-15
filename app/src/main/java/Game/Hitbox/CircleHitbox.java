package Game.Hitbox;

import Vector.Vector2D;

public class CircleHitbox implements Hitbox {

    private Vector2D position;
    private double radius;

    public CircleHitbox (Vector2D pos, double rad) {
        this.position = pos;
        this.radius = rad;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public double getRadius() {
        return this.radius;
    }
}
