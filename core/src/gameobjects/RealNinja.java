package gameobjects;

import com.badlogic.gdx.graphics.Texture;

import constant.NinjaColor;
import world.GameWorld;

/**
 * Created by Peter on 8/25/2014.
 */
public class RealNinja extends AbstractNinja{

    public RealNinja(Texture ninjaTexture, GameWorld gameWorld, NinjaColor ninjaColor) {
        super(ninjaTexture, gameWorld, ninjaColor);
    }
}
