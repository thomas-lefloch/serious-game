package Game.Actor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Window;

import Vector.Vector2D;

public class Garbage implements GameActor{

    private Vector2D pos;
    private int radius;

    private Paint color;

    public Garbage (int x, int y, int radius) {
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
        canvas.drawCircle(this.pos.getX(), this.pos.getY(),this.radius, this.color);
    }
}
