package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import screens.GameScreen;
import screens.SplashScreen;

/**
 * Created by Peter on 8/25/2014.
 */
public class Ninja extends Game {
    @Override
    public void create() {
        System.out.println("Start Game");
        setScreen(new GameScreen());
        System.out.println(Gdx.graphics.getWidth());
        System.out.println(Gdx.graphics.getHeight());
    }

    @Override
    public void dispose(){

    }
}
