package world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import gameobjects.AbstractNinja;
import helpers.AssetsLoader;

/**
 * Created by Peter on 8/25/2014.
 */
public class GameRender {
    public float gameHeight, gameWidth, screenHeight, screenWidth;

    private GameWorld myWorld;

    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;

    public GameRender(GameWorld gameWorld){
        this.myWorld = gameWorld;

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
    }

    int startX = 20, startY = 20;
    int width = 80, height = 80;

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

        if(myWorld.isStateRunning() || myWorld.isStateNinjaChosen()) {
            for(AbstractNinja ninja : myWorld.ninjas){
                ninja.draw(batcher);
            }
        }

        if(myWorld.isStateChoosing()){
            displayNinjaChoosingText();
        }

        batcher.end();
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

        AssetsLoader.font.setColor(Color.YELLOW);
        AssetsLoader.font.setScale(1f, 1f);
        float width = AssetsLoader.font.getBounds(myWorld.currentNinja.ninjaColor.colorName()).width;
        AssetsLoader.font.draw(batcher, myWorld.currentNinja.ninjaColor.colorName(), 500-width/2, 160);
    }

}
