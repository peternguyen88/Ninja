package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import aurelienribon.tweenengine.TweenManager;
import constant.C;
import game.NinjaGame;
import helpers.AssetsLoader;
import ui.Button;
import utils.RandomGenerator;

/**
 * Created by Peter on 8/25/2014.
 */
public class IntroScreen implements Screen, InputProcessor{

    public float gameHeight, gameWidth, screenHeight, screenWidth;
    private float scaleFactorX, scaleFactorY;

    private TweenManager tweenManager;
    private int width, height;
    private Texture bgTexture;
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;

    private NinjaGame myGame;
    boolean downBtn;

    float stateTime;

    TextureRegion[] ninjaFrames;
    Animation ninjaAnimation;

    Button startGame, sound, leaderBoard;

    public IntroScreen(NinjaGame game){
        this.myGame = game;
        Gdx.input.setInputProcessor(this);

        screenHeight = Gdx.graphics.getHeight();
        screenWidth = Gdx.graphics.getWidth();

        gameWidth = 640;
        gameHeight = screenHeight / (screenWidth / gameWidth);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, gameWidth, gameHeight);

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);

        initButton();

        initNinjaAnimation();
    }

    private void initNinjaAnimation() {
        ninjaFrames = new TextureRegion[4];
        ninjaFrames[0] = AssetsLoader.ninjaAtlas.findRegion("red-ninja");
        ninjaFrames[1] = AssetsLoader.ninjaAtlas.findRegion("green-ninja");
        ninjaFrames[2] = AssetsLoader.ninjaAtlas.findRegion("blue-ninja");
        ninjaFrames[3] = AssetsLoader.ninjaAtlas.findRegion("yellow-ninja");

        ninjaAnimation = new Animation(0.5f, ninjaFrames);
        stateTime = 0;
    }

    private void initButton(){
        downBtn = false;
        startGame = new Button(AssetsLoader.buttonUp,AssetsLoader.buttonDown,400,40);

        final Preferences pref = Gdx.app.getPreferences(C.Prefs.PREFERENCE_NAME);
        sound = new Button(AssetsLoader.ninjaAtlas.findRegion("sound"), AssetsLoader.ninjaAtlas.findRegion("no-sound"),50,20);
        sound.setOnClickListener(new Button.OnClickListener() {

            @Override
            protected void onClick(boolean clickedValue) {
                pref.putBoolean(C.Prefs.SOUND_ENABLE, !clickedValue);
                pref.flush();
            }
        });
        sound.setClickedValue(!pref.getBoolean(C.Prefs.SOUND_ENABLE,true));

        leaderBoard = new Button(AssetsLoader.ninjaAtlas.findRegion("leader-board"),130,20);
    }

    private void calculateScaleFactor(){
        this.scaleFactorX = Gdx.graphics.getWidth()/gameWidth;
        this.scaleFactorY = Gdx.graphics.getHeight()/gameHeight;
    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();

        spriteBatch.draw(AssetsLoader.background,0,0);
        spriteBatch.draw(AssetsLoader.title, 300, 130);

        stateTime += delta;
        spriteBatch.draw(ninjaAnimation.getKeyFrame(stateTime,true),50,100);

        startGame.render(spriteBatch);
        sound.render(spriteBatch);
        leaderBoard.render(spriteBatch);

        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        calculateScaleFactor();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = (int) (screenX/scaleFactorX);
        screenY = (int) ((int) gameHeight - screenY/scaleFactorY);

        startGame.isClicked(screenX,screenY);
        sound.toggle(screenX,screenY);
        if(leaderBoard.isClicked(screenX,screenY)){
            System.out.println("Load Leader Board");
            NinjaGame.loadLeaderBoard();
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(startGame.getClickedValue()){
            startGame.touchUp();
            NinjaGame.googleServices.showBannerAd(false);
            myGame.setScreen(new GameScreen(myGame));
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
}
