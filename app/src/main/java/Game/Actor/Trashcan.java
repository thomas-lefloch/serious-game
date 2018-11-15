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

    private RectangleHitbox leftHitbox;
    private RectangleHitbox rightHitbox;
    private RectangleHitbox bottomHitbox;
    private RectangleHitbox winningHitbox;

    private GarbageType type;

    public Trashcan(double x, double y, double width, double height, GarbageType type) {
        this.width = width;
        this.height = height;

        this.position = new Vector2D(x, y);

        double hBWidth = 20;
        this.leftHitbox = new RectangleHitbox(position, hBWidth, this.height);
        this.rightHitbox = new RectangleHitbox(new Vector2D(position.getX() + this.width - hBWidth, position.getY()), hBWidth, this.height);
        this.bottomHitbox = new RectangleHitbox(new Vector2D(position.getX() + hBWidth, position.getY() + this.height - hBWidth), this.width - hBWidth * 2, hBWidth);
        this.winningHitbox = new RectangleHitbox(new Vector2D(position.getX() + hBWidth, position.getY() + height/2), this.width - hBWidth * 2, this.height/2 - hBWidth);

        this.type = type;


        color = new Paint();
        color.setColor(chooseColor(this.type));
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

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(new RectF((float) position.getX(),(float) position.getY(),(float) (position.getX()+width), (float)(position.getY() + height)), color);

    }

    public GarbageType getType() {
        return this.type;
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
