package tweenacessors;

import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * Created by Peter on 8/29/2014.
 */
public class SpriteAccessor implements TweenAccessor<Sprite> {
    public static final int POSITION_Y = 1;

    @Override
    public int getValues(Sprite target, int tweenType, float[] returnValues) {

        switch (tweenType) {
            case POSITION_Y:
                returnValues[0] = target.getY();
                return 1;
            default: assert false; return -1;
        }
    }

    @Override
    public void setValues(Sprite target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case POSITION_Y:
                target.setY(newValues[0]);
                break;
            default: assert false; break;
        }
    }
}
