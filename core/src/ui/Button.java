package ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import game.NinjaGame;

/**
 * Created by Peter on 8/30/2014.
 */
public class Button {
    private TextureRegion background;
    private TextureRegion backgroundClicked;
    private Vector2 pos;
    private int width;
    private int height;
    private boolean clicked;

    private OnClickListener onClickListener;

    public Button(TextureRegion background, float x, float y) {
        this(background,background,x,y);
    }

    public Button(TextureRegion background, TextureRegion backgroundClicked, float x, float y) {
        this.background = background;
        this.backgroundClicked = backgroundClicked;
        pos = new Vector2(x, y);
        width = background.getRegionWidth();
        height = background.getRegionHeight();
    }

    public void render(SpriteBatch batch) {
        if (!clicked && background != null) {
            batch.draw(background, pos.x, pos.y);
        } else if (backgroundClicked != null) {
            batch.draw(backgroundClicked, pos.x, pos.y);
        }
    }

    public boolean isClicked(int mX, int mY) {
        if (mX > pos.x &&
                mX < pos.x + width &&
                mY > pos.y &&
                mY < pos.y + height) {
            clicked = true;
            if(onClickListener!=null){
                onClickListener.onClick(clicked);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean toggle(int mX, int mY) {
        if (mX > pos.x &&
                mX < pos.x + width &&
                mY > pos.y &&
                mY < pos.y + height) {
            clicked = !clicked;
            if(onClickListener!=null){
                onClickListener.onClick(clicked);
            }
            return true;
        } else {
            return false;
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public boolean getClickedValue(){
        return clicked;
    }

    public void setClickedValue(boolean clickedValue){
        this.clicked = clickedValue;
    }

    public void touchUp() {
        clicked = false;
    }

    public static abstract class OnClickListener{
        protected abstract void onClick(boolean clickedValue);
    }

    public float getX(){
        return pos.x;
    }

    public float getY(){
        return pos.y;
    }

    public void setX(float x){
        this.pos.x = x;
    }

    public void setY(float y){
        this.pos.y = y;
    }
}


