package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import helpers.AssetsLoader;
import utils.Point;
import world.GameRender;
import world.GameWorld;

/**
 * Created by Peter on 8/25/2014.
 */
public class GameScreen implements Screen {
    private GameWorld gameWorld;
    private GameRender gameRender;

    public GameScreen() {
        AssetsLoader.load();

        gameWorld = new GameWorld();
        gameRender = new GameRender(gameWorld);
    }

    @Override
    public void render(float delta) {
        gameWorld.update(delta);
        gameRender.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("Resize");
    }

    @Override
    public void show() {
        System.out.println("Show");
    }

    @Override
    public void hide() {
        System.out.println("Hide");
    }

    @Override
    public void pause() {
        System.out.println("Pause");
    }

    @Override
    public void resume() {
        System.out.println("Resume");
    }

    @Override
    public void dispose() {
        System.out.println("Dispose");
    }
}
