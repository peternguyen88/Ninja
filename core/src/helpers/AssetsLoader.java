package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Peter on 8/25/2014.
 */
public class AssetsLoader {
    public static Texture greenNinja,redNinja,yellowNinja,blueNinja;

    public static void load(){
        greenNinja = new Texture(Gdx.files.internal("green-ninja.png"));
        redNinja = new Texture(Gdx.files.internal("red-ninja.png"));
        yellowNinja = new Texture(Gdx.files.internal("yellow-ninja.png"));
        blueNinja = new Texture(Gdx.files.internal("blue-ninja.png"));
    }
}
