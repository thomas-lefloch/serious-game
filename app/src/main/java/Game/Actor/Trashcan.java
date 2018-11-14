package Game.Actor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import Game.Hitbox.RectangleHitbox;
import Vector.Vector2D;

public class Trashcan implements GameActor {

    private Vector2D position;
    private double width;
    private double height;
    
    private Paint color;

    private double levelWidth;
    private double levelHeight;

    private RectangleHitbox leftHitbox;
    private RectangleHitbox rightHitbox;
    private RectangleHitbox bottomHitbox;
    private RectangleHitbox winningHitbox;

    public Trashcan(double width, double height, double levelWidth, double levelHeight) {
        this.width = width;
        this.height = height;

        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;

        this.position = new Vector2D(this.levelWidth - 200 - width, this.levelHeight - height - 100);

        this.leftHitbox = new RectangleHitbox(position, 5, this.height);
        this.rightHitbox = new RectangleHitbox(new Vector2D(position.getX() + this.width, position.getY()), 5, this.height);
        this.bottomHitbox = new RectangleHitbox(new Vector2D(position.getX(), position.getY() + this.height - 10), this.width, 10);

        this.winningHitbox = new RectangleHitbox(new Vector2D(position.getX() + 2, position.getY() + height/2), width - 4, height/2);

        color = new Paint();
        color.setColor(Color.GREEN);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        //canvas.drawRect(new RectF((float) position.getX(),(float) position.getY(),(float) (position.getX()+width), (float)(position.getY() + height)), color);
        leftHitbox.draw(canvas);
        bottomHitbox.draw(canvas);
        rightHitbox.draw(canvas);
    }

    public RectangleHitbox getLeftHitbox() {
        return leftHitbox;
    }

    public RectangleHitbox getRightHitbox() {
        return rightHitbox;
    }

    public RectangleHitbox getBottomHitbox() {
        return bottomHitbox;
    }

    public RectangleHitbox getWinningHitbox() {
        return winningHitbox;
    }
}
