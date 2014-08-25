package world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import helpers.AssetsLoader;
import utils.Point;

/**
 * Created by Peter on 8/25/2014.
 */
public class GameRender {
    public float gameHeight, gameWidth;

    private GameWorld myWorld;

    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;

    public GameRender(GameWorld gameWorld){
        this.myWorld = gameWorld;

        float screenHeight = Gdx.graphics.getHeight();
        float screenWidth = Gdx.graphics.getWidth();

        gameWidth = 640;
        gameHeight = screenHeight / (screenWidth / gameWidth);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, gameWidth / 2, gameHeight / 2);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);
    }

    int startX = 10, startY = 10;
    int width = 40, height = 40;

    /**
     * Render the game screen
     * @param delta Time passed since last render
     */
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        int x = startX;
        for (int i = 0; i < 4; i++) {
            int y = startY;
            for (int j = 0; j < 4; j++) {
                shapeRenderer.rect(x, y, width, height);
                y += height;
            }
            x += width;
        }
        shapeRenderer.end();

        batcher.begin();

        myWorld.greenNinja.draw(batcher);
        myWorld.redNinja.draw(batcher);
        myWorld.yellowNinja.draw(batcher);
        myWorld.blueNinja.draw(batcher);

        batcher.end();
    }

}
