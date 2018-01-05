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
        boundsPause = new Rectangle(gameWorld.WIDTH - gameWorld.buttonSize / 1.3f - gameWorld.MARGIN, gameWorld.MARGIN * 2 + 0.6f,
                gameWorld.buttonSize / 1.8f, gameWorld.buttonSize / 1.8f);
    }

    public void draw(SpriteBatch batcher) {
        if(gameWorld.startCombo != 0) {
            switch (gameWorld.scl) {
                case 2:
                    batcher.draw(gameWorld.assetLoader.x2Texture, gameWorld.MARGIN, gameWorld.MARGIN, gameWorld.buttonSize, gameWorld.buttonSize);
                    break;
                case 3:
                    batcher.draw(gameWorld.assetLoader.x3Texture, gameWorld.MARGIN, gameWorld.MARGIN, gameWorld.buttonSize, gameWorld.buttonSize);
                    break;
                case 5:
                    batcher.draw(gameWorld.assetLoader.x5Texture, gameWorld.MARGIN, gameWorld.MARGIN, gameWorld.buttonSize, gameWorld.buttonSize);
                    break;
            }
        }
        batcher.draw(gameWorld.assetLoader.buttonPauseTexture, boundsPause.x, boundsPause.y, boundsPause.width, boundsPause.height);
    }

    public boolean isClickedPause(float x, float y) {
        return boundsPause.contains(x, y);
    }
}
