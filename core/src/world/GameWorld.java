package world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import constant.C;
import gameobjects.AbstractNinja;
import helpers.AssetsLoader;
import helpers.GameLevelManager;
import helpers.LocationGenerator;
import screens.GameScreen;
import ui.SpriteButton;
import utils.Point;

/**
 * Created by Peter on 8/25/2014.
 */
public class GameWorld {
    public List<AbstractNinja> ninjas;

    public LocationGenerator locationGenerator;
    public int score, highScore;

    public float ninjaAliveTime = 0.25f;
    public float flashingTime = 2f;
    public static final int PAUSE_TIME = 1;
    private float aliveTime, runtime, idleTime;

    private GameState currentState;
    public AbstractNinja currentNinja;
    private Random random = new Random();

    private float timeLeft;
    public static final int DEFAULT_CHOOSING_TIME = 3;
    private GameLevelManager gameLevelManager;
    private GameScreen gameScreen;
    private GameRender gameRender;

    public boolean sameTextColor;

    private Preferences prefs;

    public SpriteButton play,star,leader;

    enum GameState {
        RUNNING, CHOOSING, CHOSE,GAME_OVER
    }

    public GameWorld(GameScreen gameScreen) {
        locationGenerator = new LocationGenerator();
        this.gameScreen = gameScreen;
        ninjas = new ArrayList<AbstractNinja>();
        this.gameLevelManager = new GameLevelManager(this);
        currentState = GameState.RUNNING;
        this.timeLeft = DEFAULT_CHOOSING_TIME;
        prefs = Gdx.app.getPreferences("Preference");
        highScore = prefs.getInteger("high_score",0);

        play = new SpriteButton(AssetsLoader.ninjaAtlas.createSprite("play"),200,100).setOpacity(0);
        star = new SpriteButton(AssetsLoader.ninjaAtlas.createSprite("star"),280,100).setOpacity(0);
        leader = new SpriteButton(AssetsLoader.ninjaAtlas.createSprite("leader-board"),360,100).setOpacity(0);
    }

    public void update(float delta) {
        runtime += delta;

        if(runtime > flashingTime && isStateRunning()){
            switchToChoosingState();
        }

        switch (currentState) {
            case RUNNING:
                runningUpdate(delta);
                break;
            case CHOOSING:
                choosingUpdate(delta);
                break;
            case CHOSE:
                idleTime+=delta;
                if(idleTime>PAUSE_TIME){
                    idleTime = 0; aliveTime = 0; runtime = 0;
                    currentState = GameState.RUNNING;
                    for(AbstractNinja ninja : ninjas){
                        ninja.setDisplay(true);
                    }
                }
                break;
            case GAME_OVER:
                break;
            default:
                break;
        }
    }

    private void switchToChoosingState() {
        currentState = GameState.CHOOSING;

        for(AbstractNinja ninja : ninjas){
            ninja.setDisplay(false);
        }

        currentNinja = ninjas.get(random.nextInt(ninjas.size()));
    }

    private void runningUpdate(float delta){
        aliveTime += delta;
        if (aliveTime > ninjaAliveTime) {
            locationGenerator.reset();
            aliveTime = 0;

            for(AbstractNinja ninja : ninjas){
                ninja.nextPoint();
            }
        }
    }

    private void choosingUpdate(float delta){
        timeLeft -= delta;
        if(timeLeft<=0){
            timeLeft = 0;
            gameOver();
        }
    }

    private void gameOver() {
        currentState = GameState.GAME_OVER;

        if(score > highScore){
            highScore = score;
            prefs.putInteger("high_score", highScore);
            prefs.flush();
        }

        for(AbstractNinja ninja : ninjas){
            ninja.setDisplay(true);
        }
        gameRender.setupTween();
    }

    public void chooseNinja(Point p){
        currentState = GameState.CHOSE;

        currentNinja.setDisplay(true);
        currentNinja.clearRandomColor();

        if(currentNinja.getLocation().equals(p)){
            chooseCorrectNinja();
            gameLevelManager.chooseCorrectly();
        }
        else {
            chooseWrongNinja(p);
            gameLevelManager.chooseWrongly();
        }
    }

    private void chooseCorrectNinja() {
        if(prefs.getBoolean(C.Prefs.SOUND_ENABLE))
            AssetsLoader.correct.play();
        System.out.println("Correct");
        score++;
        timeLeft++;
    }

    private void chooseWrongNinja(Point p) {
        if(prefs.getBoolean(C.Prefs.SOUND_ENABLE))
            AssetsLoader.wrong.play();
        for (AbstractNinja ninja : ninjas) {
            if (ninja.getLocation().equals(p)) {
                ninja.setDisplay(true);
            }
        }
        System.out.println("Incorrect");
    }

    public void click(int x, int y){
        if(currentState == GameState.GAME_OVER) {
            if (play.isClicked(x, y)) {
                this.restart();
            }
            if (star.isClicked(x, y)) {
                Gdx.net.openURI(C.Info.URI);
            }
            if (leader.isClicked(x, y)) {
                System.out.println("Leader");
            }
        }
    }

    public void restart(){
        this.ninjas.clear();
        this.currentState = GameState.RUNNING;
        this.runtime = 0;
        this.idleTime = 0;
        this.aliveTime = 0;
        this.timeLeft = DEFAULT_CHOOSING_TIME;
        this.play.setOpacity(0);
        this.star.setOpacity(0);
        this.leader.setOpacity(0);
        this.score = 0;
        gameLevelManager.level1();
    }

    public void registerGameRender(GameRender gameRender){
        this.gameRender = gameRender;
    }

    public float getTimeLeft(){
        return (int)(timeLeft * 100)/100.0f;
    }

    public boolean isStateChoosing(){
        return currentState == GameState.CHOOSING;
    }

    public boolean isStateRunning(){
        return currentState == GameState.RUNNING;
    }

    public boolean isStateNinjaChosen() {
        return currentState == GameState.CHOSE;
    }

    public boolean isStateGameOver(){return currentState == GameState.GAME_OVER;}
}
