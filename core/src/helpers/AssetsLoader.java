package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Peter on 8/25/2014.
 */
public class AssetsLoader {
    public static Sprite greenNinja,redNinja,yellowNinja,blueNinja;

    public static Texture background, matrix;

    public static TextureRegion title, timeUp, buttonUp, buttonDown, exitGame;

    public static TextureAtlas ninjaAtlas;

    public static BitmapFont font = new BitmapFont();

    public static Sound correct, wrong;

    public static void load(){
        ninjaAtlas = new TextureAtlas(Gdx.files.internal("ninja.pack"));

        greenNinja = ninjaAtlas.createSprite("green-ninja");
        redNinja = ninjaAtlas.createSprite("red-ninja");
        yellowNinja = ninjaAtlas.createSprite("yellow-ninja");
        blueNinja = ninjaAtlas.createSprite("blue-ninja");

        title = ninjaAtlas.findRegion("title");
        timeUp = ninjaAtlas.findRegion("timeup.png");

        buttonUp = ninjaAtlas.findRegion("cover_button_start_up");
        buttonDown = ninjaAtlas.findRegion("cover_button_start_down");

        background = new Texture(Gdx.files.internal("background.jpg"));
        matrix = new Texture(Gdx.files.internal("matrix.png"));

        font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.setScale(0.25f, 0.25f);

        correct = Gdx.audio.newSound(Gdx.files.internal("sounds/ding.mp3"));
        wrong = Gdx.audio.newSound(Gdx.files.internal("sounds/buzzer.mp3"));
    }
}
