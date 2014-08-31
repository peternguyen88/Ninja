package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import utils.Converter;
import world.GameRender;
import world.GameWorld;

/**
 * Created by Peter on 8/26/2014.
 */
public class InputHandler implements InputProcessor{

    private GameWorld gameWorld;
    private float scaleFactorX, scaleFactorY;
    private float gameHeight, gameWidth;

    public InputHandler(GameWorld gameWorld, GameRender gameRender){
        this.gameWorld = gameWorld;
        this.scaleFactorX = gameRender.screenWidth/gameRender.gameWidth;
        this.scaleFactorY = gameRender.screenHeight/gameRender.gameHeight;
        this.gameHeight = gameRender.gameHeight;
        this.gameWidth = gameRender.gameWidth;
        Gdx.input.setCatchBackKey(true);
    }

    public void recalculateScaleFactor(){
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        this.scaleFactorX = screenWidth/gameWidth;
        this.scaleFactorY = screenHeight/gameHeight;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode== Input.Keys.BACK){
            gameWorld.backPress();
        }
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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = (int) gameHeight - scaleY(screenY);

        gameWorld.click(screenX,screenY);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(gameWorld.isStateChoosing()) {
            screenX = scaleX(screenX);
            screenY = (int) gameHeight - scaleY(screenY);

            if(screenX<30||screenX>350||screenY<20||screenY>340) {
                System.out.println("Click outside the box");
            }
            else{
                System.out.println(screenX + " --- " + screenY);
                gameWorld.chooseNinja(Converter.revertLocation(screenX, screenY));
            }
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

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }
}
