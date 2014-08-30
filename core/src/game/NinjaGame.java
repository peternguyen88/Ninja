package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import helpers.AssetsLoader;
import screens.IntroScreen;

/**
 * Created by Peter on 8/25/2014.
 */
public class NinjaGame extends Game {
    @Override
    public void create() {
        System.out.println("Start Game");

        System.out.println("Load Resources");
        AssetsLoader.load();
        System.out.println("End Load Resources");

        setScreen(new IntroScreen(this));

        System.out.println(Gdx.graphics.getWidth());
        System.out.println(Gdx.graphics.getHeight());
    }

    @Override
    public void dispose(){

    }
}
