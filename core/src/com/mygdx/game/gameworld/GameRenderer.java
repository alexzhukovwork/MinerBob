package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.helpers.AssetLoader;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.ui.MenuForm;

/**
 * Created by Алексей on 10.09.2017.
 */

public class GameRenderer {
    private GameWorld gameWorld;
    private OrthographicCamera camera;

    private SpriteBatch batcher;
    private ShapeRenderer shapeRenderer;

    private MenuForm menu;

    public GameRenderer(GameWorld gameWorld) {
        menu = gameWorld.getMenu();
        this.gameWorld = gameWorld;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, GameScreen.WIDTH, GameScreen.HEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameWorld.getField().draw(batcher);
        gameWorld.getRowBlock().draw(shapeRenderer, batcher);

        batcher.begin();
        batcher.enableBlending();

        if (gameWorld.getActor().getOnBlock()) {
            batcher.draw((TextureRegion) AssetLoader.actorAnimation.getKeyFrame(runTime), gameWorld.getActor().getX(),
                    gameWorld.getActor().getY(), gameWorld.getActor().getWidth(), gameWorld.getActor().getHeight());
        }

        else {
            batcher.draw((TextureRegion) AssetLoader.actorFall, gameWorld.getActor().getX(),
                    gameWorld.getActor().getY(), gameWorld.getActor().getWidth(), gameWorld.getActor().getHeight());
        }



        if (!gameWorld.getActor().getAlive()) {
            gameWorld.setState(GameWorld.GameState.RESTART);

        } else if (gameWorld.isRunning())
            AssetLoader.font.draw(batcher, GameWorld.score + "", GameScreen.WIDTH / 10, GameScreen.HEIGHT / 2 + GameScreen.HEIGHT / 3);

        batcher.end();

        if (gameWorld.isMenu())
            menu.draw(shapeRenderer, batcher);

        if (gameWorld.isShop()) {
            gameWorld.getShop().draw(shapeRenderer, batcher);
        }

        if (gameWorld.isRestart()) {
            gameWorld.getPauseForm().draw(shapeRenderer, batcher);
        }

        if (gameWorld.isPause()) {
            gameWorld.getPauseForm().draw(shapeRenderer, batcher);
        }

        if (gameWorld.isRunning()) {
            gameWorld.getRunningForm().draw(shapeRenderer, batcher);
        }
    }
}
