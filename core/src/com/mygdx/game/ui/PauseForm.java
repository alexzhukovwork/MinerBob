package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.gameworld.GameWorld;
import com.mygdx.game.helpers.AssetLoader;
import com.mygdx.game.screen.GameScreen;

/**
 * Created by Алексей on 19.09.2017.
 */

public class PauseForm {
    private Rectangle boundsBoard;
    private Rectangle boundsMenu;
    private Rectangle boundsRestart;
    private String message;

    public PauseForm() {
        float x = GameScreen.WIDTH / 5;
        float y = GameScreen.HEIGHT / 5;
        float width = GameScreen.WIDTH / 5 * 3;
        float height = width;
        message = "";
        boundsBoard = new Rectangle(x, y, width, height);
        boundsMenu = new Rectangle(x + width / 2 + width / 10, y + height / 10 * 7, width / 2 - width / 10 * 2, height / 10 * 2);
        boundsRestart = new Rectangle(x + width / 10, y + height / 10 * 7, width / 2 - width / 10 * 2, height / 10 * 2);
    }

    public void checkRecord() {
        if (GameWorld.score > AssetLoader.prefs.getInteger("highScore")) {
            GameWorld.isRecord = true;
            AssetLoader.prefs.putInteger("highScore", GameWorld.score);
            message = "New Record!";
        } else
            message = "Score";
    }

    public void draw(ShapeRenderer shaper, SpriteBatch batcher) {
        shaper.begin(ShapeRenderer.ShapeType.Filled);
        shaper.setColor(1, 1, 1, 1);
        shaper.rect(boundsBoard.x, boundsBoard.y, boundsBoard.width, boundsBoard.height);
        shaper.setColor(0, 0, 0, 1);
        shaper.rect(boundsMenu.x, boundsMenu.y, boundsMenu.width, boundsMenu.height);
        shaper.rect(boundsRestart.x, boundsRestart.y, boundsRestart.width, boundsRestart.height);
        shaper.end();
        batcher.begin();
        AssetLoader.font.draw(batcher, message, boundsBoard.x + boundsBoard.width / 10 * 2, boundsBoard.y + boundsBoard.height / 10);
        AssetLoader.font.draw(batcher, GameWorld.score + "", boundsBoard.x + boundsBoard.width / 2 - boundsBoard.width / 20
                , boundsBoard.y + boundsBoard.height / 10 * 4);
        batcher.end();
    }

    public boolean isClickedRestart(float x, float y) {
        return boundsRestart.contains(x, y);
    }

    public boolean isClickedMenu(float x, float y) {
        return boundsMenu.contains(x, y);
    }

    public boolean isClickedResume(float x, float y) {
        return boundsRestart.contains(x, y);
    }
}
