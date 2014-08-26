package gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import constant.NinjaColor;
import utils.Point;
import world.GameWorld;

/**
 * Created by Peter on 8/25/2014.
 */
public abstract class AbstractNinja {
    protected Texture ninjaTexture;
    protected float aliveTime;
    protected GameWorld gameWorld;

    public static final int WIDTH = 66, HEIGHT = 76;

    protected boolean isDisplay = false;
    protected Point location;

    public NinjaColor ninjaColor;

    public AbstractNinja(Texture ninjaTexture, GameWorld gameWorld, NinjaColor ninjaColor) {
        this.ninjaTexture = ninjaTexture;
        this.gameWorld = gameWorld;
        this.location = gameWorld.locationGenerator.nextPoint();
        this.isDisplay = true;
        this.ninjaColor = ninjaColor;
    }

    public void nextPoint() {
        location = gameWorld.locationGenerator.nextPoint();
    }

    public void setDisplay(boolean display){
        this.isDisplay = display;
    }

    public void draw(SpriteBatch batcher) {
        if (isDisplay)
            batcher.draw(ninjaTexture, location.x, location.y, WIDTH, HEIGHT);
    }

    public Point getLocation(){
        return location;
    }
}
