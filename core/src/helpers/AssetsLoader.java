package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Peter on 8/25/2014.
 */
public class AssetsLoader {
    public static Texture greenNinja,redNinja,yellowNinja,blueNinja;

    public static Texture background, matrix;

    public static BitmapFont font = new BitmapFont();

    public static void load(){
        greenNinja = new Texture(Gdx.files.internal("green-ninja.png"));
        redNinja = new Texture(Gdx.files.internal("red-ninja.png"));
        yellowNinja = new Texture(Gdx.files.internal("yellow-ninja.png"));
        blueNinja = new Texture(Gdx.files.internal("blue-ninja.png"));

        background = new Texture(Gdx.files.internal("grungy-greenish-background.jpg"));
        matrix = new Texture(Gdx.files.internal("matrix.png"));

        font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.setScale(0.25f, 0.25f);
    }
}
