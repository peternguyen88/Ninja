package gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import constant.NinjaColor;
import utils.Point;
import world.GameWorld;

/**
 * Created by Peter on 8/25/2014.
 */
public abstract class AbstractNinja {
    protected Sprite ninjaSprite;
    protected float aliveTime;
    protected GameWorld gameWorld;

    public static final int WIDTH = 66, HEIGHT = 76;

    protected boolean isDisplay = false;
    protected Point location;

    public NinjaColor ninjaColor;

    public AbstractNinja(Sprite ninjaSprite, GameWorld gameWorld, NinjaColor ninjaColor) {
        this.ninjaSprite = ninjaSprite;
        this.gameWorld = gameWorld;
        this.location = gameWorld.locationGenerator.nextPoint();
        this.isDisplay = true;
        this.ninjaColor = ninjaColor;
    }

    public void nextPoint() {
        location = gameWorld.locationGenerator.nextPoint();
    }

    public void setDisplay(boolean display) {
        this.isDisplay = display;
    }

    public void draw(SpriteBatch batcher) {
        if (isDisplay)
        {
            ninjaSprite.setBounds(location.x,location.y,WIDTH,HEIGHT);
            ninjaSprite.draw(batcher);
        }

    }

    public Point getLocation() {
        return location;
    }

    private Color randomColor;

    public Color getRandomColor() {
        if (randomColor == null) {
            randomColor = NinjaColor.randomColor();
        }
        return randomColor;
    }

    public void clearRandomColor() {
        this.randomColor = null;
    }
}
