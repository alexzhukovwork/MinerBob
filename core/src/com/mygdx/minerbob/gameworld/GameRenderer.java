package com.mygdx.minerbob.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.minerbob.helpers.TextSize;
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

    public GameRenderer(GameWorld gameWorld) {
        menu = gameWorld.getMenu();
        this.gameWorld = gameWorld;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, gameWorld.WIDTH, gameWorld.HEIGHT);
        camera.rotate(25);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render(float runTime) {
        batcher.begin();
        batcher.enableBlending();
        gameWorld.getField().draw(batcher);
        gameWorld.getRowBlock().draw(batcher);

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
                batcher.draw(gameWorld.assetLoader.currentTexture, gameWorld.getActor().getX(),
                        gameWorld.getActor().getY(), gameWorld.getActor().getWidth(), gameWorld.getActor().getHeight());
            }
        }
        gameWorld.avalanche.draw(batcher);
        if (!gameWorld.getActor().getAlive()) {
            gameWorld.setState(GameWorld.GameState.RESTART);
        } else if (gameWorld.isRunning()) {
            gameWorld.assetLoader.font.draw(batcher, GameWorld.score + "", gameWorld.WIDTH / 10, gameWorld.HEIGHT - gameWorld.HEIGHT / 7);
        }


        if (gameWorld.isMenu()) {
            batcher.end();
            menu.draw(shapeRenderer, batcher);
            batcher.begin();
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

        if (gameWorld.isRunning()) {
            gameWorld.getRunningForm().draw(batcher);
        }

        if (gameWorld.isDailyBonus()) {
            gameWorld.getDailyBonus().draw(batcher);
        }

        gameWorld.moneyAnimation.draw(batcher, shapeRenderer);
        batcher.end();
    }
}
