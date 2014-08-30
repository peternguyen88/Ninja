package ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Peter on 8/30/2014.
 */
public class SpriteButton {
    private Sprite sprite;

    public SpriteButton(Sprite sprite, float x, float y) {
        this.sprite = sprite;
        this.sprite.setPosition(x, y);
    }

    public void render(SpriteBatch batch) {
        this.sprite.draw(batch);
    }

    public boolean isClicked(int mX, int mY) {
        if (mX > this.sprite.getX() && mX < this.sprite.getX() + this.sprite.getWidth() &&
                mY > this.sprite.getY() && mY < this.sprite.getY() + this.sprite.getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public SpriteButton setOpacity(float opacity) {
        Color c = sprite.getColor();
        c.set(c.r, c.g, c.b, opacity);
        sprite.setColor(c);
        return this;
    }
}
