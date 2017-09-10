package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.helpers.AssetLoader;
import com.mygdx.game.screen.GameScreen;

/**
 * Created by Алексей on 10.09.2017.
 */

public class GameRenderer {
    private GameWorld gameWorld;
    private OrthographicCamera camera;

    private SpriteBatch batcher;

    public GameRenderer(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, GameScreen.WIDTH, GameScreen.HEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batcher.begin();
        batcher.enableBlending();
        batcher.draw((TextureRegion) AssetLoader.actorAnimation.getKeyFrame(runTime), gameWorld.getActor().getX(),
                gameWorld.getActor().getY(), gameWorld.getActor().getWidth(), gameWorld.getActor().getHeight());
        batcher.end();
    }
}
