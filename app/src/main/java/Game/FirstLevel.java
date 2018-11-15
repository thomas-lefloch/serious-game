package Game;

import android.widget.Toast;

import java.util.ArrayList;

import Game.Actor.Garbage;
import Game.Actor.GarbageState;
import Game.Actor.GarbageType;
import Game.Actor.Obstacle;
import Game.Actor.Trashcan;
import Vector.Vector2D;

public class FirstLevel extends Level {


    public FirstLevel(int width, int height) {
        super(width, height);

        launchLocation = new Vector2D(300, levelHeight - 300);

        bins = initTrashcans();

        obstacles = initObstacles();
        garbages = initGarbages();
        currentGarbage = garbages.get(0);

        radiusLimit = 200;

        gravity = 0.69;
    }

    @Override
    protected  ArrayList<Obstacle> initObstacles() {
        ArrayList<Obstacle> obs =  new ArrayList<>();
        double height = 400;
        obs.add(new Obstacle(this.levelWidth - 375, this.levelHeight - height, 50,height));
        return obs;
    }

    @Override
    protected ArrayList<Trashcan> initTrashcans () {
        ArrayList<Trashcan> trashes = new ArrayList<>();
        double height = 200;
        trashes.add(new Trashcan(this.levelWidth - 250,this.levelHeight - height, 150, height, GarbageType.RECYCLABLE));
        trashes.add(new Trashcan(this.levelWidth - 600, this.levelHeight - height, 150, height, GarbageType.OTHER));
        return trashes;
    }

    @Override
    protected ArrayList<Garbage> initGarbages() {
        ArrayList<Garbage> junks = new ArrayList<>();
        junks.add(new Garbage(launchLocation.getX(),launchLocation.getY(),25, this.levelWidth, this.levelHeight, bins, obstacles, GarbageType.OTHER));
        junks.add(new Garbage(launchLocation.getX(),launchLocation.getY(),25, this.levelWidth, this.levelHeight, bins, obstacles, GarbageType.RECYCLABLE));
        junks.add(new Garbage(launchLocation.getX(),launchLocation.getY(),25, this.levelWidth, this.levelHeight, bins, obstacles, GarbageType.OTHER));
        junks.add(new Garbage(launchLocation.getX(),launchLocation.getY(),25, this.levelWidth, this.levelHeight, bins, obstacles, GarbageType.RECYCLABLE));
        return junks;
    }

    @Override
    public void update() {
        applyGravity();

        if (currentGarbage.getState().equals(GarbageState.STOPPED)) {
            currentGarbage = nextGarbage();
        }
    }

    private void applyGravity() {
        if(currentGarbage.getState().equals(GarbageState.LAUNCHED)) {
            currentGarbage.applyForce(gravity, new Vector2D(0, 1));
            currentGarbage.update();
        }
    }
}
