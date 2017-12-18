package com.mygdx.minerbob.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.TextSize;
import com.mygdx.minerbob.screen.GameScreen;

/**
 * Created by Алексей on 19.09.2017.
 */

public class RunningForm {
    Rectangle boundsPause;
    GameWorld gameWorld;

    public RunningForm(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        boundsPause = new Rectangle(gameWorld.WIDTH - gameWorld.buttonSize - gameWorld.MARGIN, gameWorld.MARGIN,
                gameWorld.buttonSize, gameWorld.buttonSize);
    }

    public void draw(SpriteBatch batcher) {
        //gameWorld.assetLoader.font.getData().setScale(0.2f, - 0.2f);
        batcher.begin();
        if(gameWorld.startCombo != 0) {
            gameWorld.assetLoader.font.draw(batcher, "X" + gameWorld.scl, gameWorld.MARGIN, gameWorld.MARGIN);
        }
        batcher.draw(gameWorld.assetLoader.buttonPauseTexture, boundsPause.x, boundsPause.y, boundsPause.width, boundsPause.height);
        batcher.end();
        //gameWorld.assetLoader.font.getData().setScale(0.1f, - 0.1f);
    }

    public boolean isClickedPause(float x, float y) {
        return boundsPause.contains(x, y);
    }
}
