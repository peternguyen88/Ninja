package gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import utils.Point;
import world.GameWorld;

/**
 * Created by Peter on 8/25/2014.
 */
public abstract class AbstractNinja {
    protected Texture ninjaTexture;
    protected float aliveTime;
    protected GameWorld gameWorld;

    public static final int WIDTH = 33, HEIGHT = 38;

    protected boolean isDisplay = false;
    protected Point location;

    public AbstractNinja(Texture ninjaTexture, GameWorld gameWorld) {
        this.ninjaTexture = ninjaTexture;
        this.gameWorld = gameWorld;
        this.location = gameWorld.locationGenerator.nextPoint();
    }

    public void update(float delta) {
        aliveTime += delta;
        if(aliveTime > gameWorld.ninjaAliveTime){
            isDisplay = !isDisplay;
            aliveTime = 0;
            location = gameWorld.locationGenerator.nextPoint();
        }
    }

    public void draw(SpriteBatch batcher, int x, int y) {
        if (isDisplay)
            batcher.draw(ninjaTexture, x, y, WIDTH, HEIGHT);
    }

    public void draw(SpriteBatch batcher) {
        batcher.draw(ninjaTexture, location.x, location.y, WIDTH, HEIGHT);
    }
}
