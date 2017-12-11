package com.mygdx.minerbob.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.minerbob.ui.MenuForm;


/**
 * Created by Алексей on 10.09.2017.
 */

public class GameRenderer {
    private GameWorld gameWorld;
    private OrthographicCamera camera;

    private SpriteBatch batcher;
    private ShapeRenderer shapeRenderer;

    private MenuForm menu;
    private float stateTime = 0f;
    private float stateLavaTime = 0f;

    public GameRenderer(GameWorld gameWorld) {
        menu = gameWorld.getMenu();
        this.gameWorld = gameWorld;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, gameWorld.WIDTH, gameWorld.HEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render(float runTime, float delta) { // delete delta for fps recording
      //  Gdx.gl.glClearColor(1, 1, 1, 1);
      //  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameWorld.getField().draw(batcher);
        gameWorld.getRowBlock().draw(shapeRenderer, batcher);

        batcher.begin();
        batcher.enableBlending();
      //  int fps = (int)(1.0f / delta);
       // gameWorld.assetLoader.font.draw(batcher, fps+ "", 10, 10);
        if(gameWorld.isRestoring) {
            batcher.draw((TextureRegion) gameWorld.assetLoader.restoreAnimation.getKeyFrame(stateTime), gameWorld.getActor().getX(),
                    gameWorld.getActor().getY(), gameWorld.getActor().getWidth(), gameWorld.getActor().getHeight());
            stateTime += Gdx.graphics.getDeltaTime();
            if(gameWorld.assetLoader.restoreAnimation.isAnimationFinished(stateTime)) {
                gameWorld.isRestoring = false;
                gameWorld.restoring();
                stateTime = 0f;
            }
        }
        else {
            if (gameWorld.getActor().getOnBlock()) {
                batcher.draw((TextureRegion) gameWorld.assetLoader.currentAnimation.getKeyFrame(runTime), gameWorld.getActor().getX(),
                        gameWorld.getActor().getY(), gameWorld.getActor().getWidth(), gameWorld.getActor().getHeight());
            } else {
                batcher.draw((TextureRegion) gameWorld.assetLoader.currentTexture, gameWorld.getActor().getX(),
                        gameWorld.getActor().getY(), gameWorld.getActor().getWidth(), gameWorld.getActor().getHeight());
            }
        }

        if (!gameWorld.getActor().getAlive()) {
            gameWorld.setState(GameWorld.GameState.RESTART);

        } else if (gameWorld.isRunning())
            gameWorld.assetLoader.font.draw(batcher, GameWorld.score + "", gameWorld.WIDTH / 10, gameWorld.HEIGHT - gameWorld.HEIGHT / 7);

        batcher.end();

        if (gameWorld.isMenu()) {
            menu.draw(shapeRenderer, batcher);
        }

        if (gameWorld.isShop()) {
            gameWorld.getShop().draw(batcher, runTime);
        }

        if (gameWorld.isRestart()) {
            gameWorld.getPauseForm().draw(batcher);
        }

        if (gameWorld.isPause()) {
            gameWorld.getPauseForm().draw(batcher);
        }
        gameWorld.avalanche.draw(batcher);
        if (gameWorld.isRunning()) {
            gameWorld.getRunningForm().draw(shapeRenderer, batcher);
            if(gameWorld.startCombo != 0) {
                batcher.begin();
                gameWorld.assetLoader.font.draw(batcher, "X" + gameWorld.scl, 0, 0);
                batcher.end();
            }

        }

        if (gameWorld.isDailyBonus()) {
            gameWorld.getDailyBonus().draw(batcher);
        }

        gameWorld.moneyAnimation.draw(batcher, shapeRenderer);
    }
}
