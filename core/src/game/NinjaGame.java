package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import constant.C;
import helpers.AssetsLoader;
import helpers.IGoogleServices;
import screens.IntroScreen;

/**
 * Created by Peter on 8/25/2014.
 */
public class NinjaGame extends Game {

    public static IGoogleServices googleServices;
    public static int launchTime = 0;

    public NinjaGame(IGoogleServices googleServices)
    {
        super();
        NinjaGame.googleServices = googleServices;
    }

    @Override
    public void create() {
        System.out.println("Start Game");

        System.out.println("Load Resources");
        AssetsLoader.load();
        System.out.println("End Load Resources");

        setScreen(new IntroScreen(this));

        System.out.println(Gdx.graphics.getWidth());
        System.out.println(Gdx.graphics.getHeight());

        Preferences prefs = Gdx.app.getPreferences(C.Prefs.PREFERENCE_NAME);
        launchTime = prefs.getInteger(C.Prefs.LAUNCH_TIME,0);
        prefs.putInteger(C.Prefs.LAUNCH_TIME,++launchTime);
        prefs.flush();
    }

    @Override
    public void dispose(){

    }

    public static void loadLeaderBoard(){
        if(!googleServices.isSignedIn()){
            googleServices.signIn();
        }
        if(googleServices.isSignedIn()){
            googleServices.showScores();
        }
    }
}
