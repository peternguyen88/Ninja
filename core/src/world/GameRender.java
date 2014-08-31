package world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.sql.Time;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Back;
import aurelienribon.tweenengine.equations.Quart;
import game.NinjaGame;
import gameobjects.AbstractNinja;
import gameobjects.CentralTextDrawer;
import helpers.AssetsLoader;
import tweenacessors.SpriteAccessor;
import tweenacessors.TextAccessor;

/**
 * Created by Peter on 8/25/2014.
 */
public class GameRender {
    public float gameHeight, gameWidth, screenHeight, screenWidth;

    private GameWorld myWorld;

    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;

    private Sprite timeUp;
    private CentralTextDrawer yourScore, highScore;
    private TweenManager tweenManager;

    public GameRender(GameWorld gameWorld){
        this.myWorld = gameWorld;
        this.myWorld.registerGameRender(this);

        screenHeight = Gdx.graphics.getHeight();
        screenWidth = Gdx.graphics.getWidth();

        gameWidth = 640;
        gameHeight = screenHeight / (screenWidth / gameWidth);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, gameWidth, gameHeight);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        timeUp = AssetsLoader.ninjaAtlas.createSprite("timeup");

        yourScore = new CentralTextDrawer(AssetsLoader.font);
        highScore = new CentralTextDrawer(AssetsLoader.font);

    }

    /**
     * Render the game screen
     * @param delta Time passed since last render
     */
    public void render(float delta){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();

        drawBackground();

        AssetsLoader.font.setColor(Color.CYAN);
        AssetsLoader.font.setScale(0.25f, 0.25f);
        AssetsLoader.font.draw(batcher,"SCORE:"+myWorld.score,360,340);

        AssetsLoader.font.draw(batcher,"TIME:"+myWorld.getTimeLeft(),540,340);

        if(myWorld.isStateRunning() || myWorld.isStateNinjaChosen() || myWorld.isStateGameOver()) {
            drawNinjas();
        }

        if(myWorld.isStateChoosing()){
            displayNinjaChoosingText();
        }

        if(myWorld.isStateGameOver()){
            tweenManager.update(delta);
            timeUp.draw(batcher);
            yourScore.draw(batcher,"YOUR SCORE: "+ myWorld.score, Color.WHITE, 0.5f,0.5f);
            highScore.draw(batcher,"HIGH SCORE: " + myWorld.highScore, Color.RED, 0.5f,0.5f);

            myWorld.play.render(batcher);
            myWorld.star.render(batcher);
            myWorld.leader.render(batcher);
    }

    batcher.end();

}

    private void drawNinjas() {
        for(AbstractNinja ninja : myWorld.ninjas){
            ninja.draw(batcher);
        }
    }

    private void drawBackground() {
        batcher.draw(AssetsLoader.background,0,0);

        batcher.setColor(Color.BLUE);
        batcher.draw(AssetsLoader.matrix,20,20);
        batcher.setColor(Color.WHITE);
    }

    private void displayNinjaChoosingText() {
        AssetsLoader.font.setColor(Color.WHITE);
        AssetsLoader.font.setScale(0.5f, 0.5f);
        AssetsLoader.font.draw(batcher, "FIND NINJA", 410, 220);

        if(myWorld.sameTextColor){
            AssetsLoader.font.setColor(myWorld.currentNinja.ninjaColor.getColor());
        }
        else{
            AssetsLoader.font.setColor(myWorld.currentNinja.getRandomColor());
        }

        AssetsLoader.font.setScale(1f, 1f);
        float width = AssetsLoader.font.getBounds(myWorld.currentNinja.ninjaColor.colorName()).width;
        AssetsLoader.font.draw(batcher, myWorld.currentNinja.ninjaColor.colorName(), 500-width/2, 160);
    }

    public void setupTween(){
        Tween.registerAccessor(Sprite.class,new SpriteAccessor());
        Tween.registerAccessor(CentralTextDrawer.class,new TextAccessor());
        tweenManager = new TweenManager();
        timeUp.setPosition(gameWidth/2-200,0);
        yourScore.setPosition(gameWidth / 2, -90);
        highScore.setPosition(gameWidth/2,- 120);

        Timeline text = Timeline.createParallel().push(Tween.to(timeUp, SpriteAccessor.POSITION_Y, 1f).target(130).ease(Back.OUT))
            .push(Tween.to(yourScore, TextAccessor.POSITION_Y, 1f).target(245).ease(Back.OUT))
            .push(Tween.to(highScore, TextAccessor.POSITION_Y, 1f).target(205).ease(Back.OUT));
        Timeline button = Timeline.createSequence()
            .push(createTimeLine(myWorld.play.getSprite()))
            .push(createTimeLine(myWorld.star.getSprite()))
            .push(createTimeLine(myWorld.leader.getSprite()));

        TweenCallback callback = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                NinjaGame.googleServices.showInterstitialAd(true);
            }
        };

        Timeline.createParallel().push(text).push(button).setCallback(callback).start(tweenManager);
    }

    private Timeline createTimeLine(Sprite sprite){
        return Timeline.createSequence()
                .push(Tween.set(sprite,SpriteAccessor.SCALE_XY).target(10,10))
                .push(Tween.set(sprite, SpriteAccessor.OPACITY).target(0))
                .beginParallel()
                .push(Tween.to(sprite, SpriteAccessor.OPACITY, 0.6f).target(1).ease(Quart.INOUT))
                .push(Tween.to(sprite, SpriteAccessor.SCALE_XY, 0.6f).target(1, 1).ease(Quart.INOUT))
                .end();
    }
}
