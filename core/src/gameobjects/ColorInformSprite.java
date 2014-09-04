package gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Peter on 9/3/2014.
 */
public class ColorInformSprite{
    private Sprite sprite;
    private final float displayTime;

    private float counter;

    public ColorInformSprite(Sprite sprite, float displayTime) {
        this.sprite = sprite;
        this.displayTime = displayTime;
        setAlpha(sprite);
    }

    public void setPosition(float x, float y){
        sprite.setPosition(x,y);
    }

    private void setAlpha(Sprite target){
        Color c = target.getColor();
        c.set(c.r, c.g, c.b, 0.5f);
        target.setColor(c);
    }

    public void show(){
        this.counter = displayTime;
    }

    public void update(float delta){
        this.counter -= delta;
    }

    public void render(SpriteBatch batch){
        if(counter>0){
            sprite.draw(batch);
        }
    }
}
