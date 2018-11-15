package Game.Actor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import Game.Hitbox.RectangleHitbox;
import Vector.Vector2D;

public class Obstacle implements GameActor {

    private Vector2D position;
    private double width;
    private double height;
    private Paint color;
    private RectangleHitbox hitbox;

    public Obstacle(double x, double y, double w, double h) {
        this.position = new Vector2D(x, y);
        this.width = w;
        this.height = h;

        this.hitbox = new RectangleHitbox(position, w, h);

        this.color = new Paint();
        this.color.setColor(Color.LTGRAY);
    }

    @Override
    public void update() {
        //nothing to do
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect((float) position.getX(), (float) position.getY(), (float)(position.getX() + width), (float) (position.getY() + height), color);
    }

    public RectangleHitbox getHitbox() {
        return this.hitbox;
    }
}
