package gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Peter on 8/29/2014.
 */
public class CentralTextDrawer {
    BitmapFont font;

    float x,y;

    public CentralTextDrawer(BitmapFont font){
        this.font = font;
    }

    public void setScale(float scaleX, float scaleY){
        font.setScale(scaleX,scaleY);
    }

    public void setColor(Color color){
        this.font.setColor(color);
    }

    public void draw(SpriteBatch batcher,String text, Color color, float scaleX, float scaleY){
        setColor(color);
        setScale(scaleX,scaleY);
        draw(batcher,text);
    }

    public void draw(SpriteBatch batcher, String text){
        float width =  font.getBounds(text).width;
        font.draw(batcher,text,x-width/2,y);
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }
}
