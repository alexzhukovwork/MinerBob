package com.mygdx.minerbob.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.Money;
import com.mygdx.minerbob.helpers.TextSize;
import com.mygdx.minerbob.screen.GameScreen;


/**
 * Created by Алексей on 19.09.2017.
 */

public class PauseForm {
    private Rectangle boundsBoard;
    private Rectangle boundsMenu;
    private Rectangle boundsRestart;
    private Rectangle boundsRestore;
    private State currentState;
    private GameWorld gameWorld;

    public enum State {
        PAUSE, RECORD, SCORE
    }

    public PauseForm(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        float x = gameWorld.WIDTH / 6;
        float y = gameWorld.HEIGHT / 4;
        float width = gameWorld.WIDTH / 4 * 2.7f;
        float height = width;
        boundsRestore = new Rectangle(-100, -100, gameWorld.WIDTH / 7 * 1.5f, gameWorld.WIDTH / 7);
        boundsBoard = new Rectangle(x, y, width, height);
        boundsMenu = new Rectangle(x + width - gameWorld.WIDTH / 7 * 1.5f - width / 9, y + height / 10 * 7, gameWorld.WIDTH / 7 * 1.5f, gameWorld.WIDTH / 7);
        boundsRestart = new Rectangle(x + width / 9, y + height / 10 * 7, gameWorld.WIDTH / 7 * 1.5f, gameWorld.WIDTH / 7);
    }

    public void checkRecord() {
        if (GameWorld.score > AssetLoader.prefs.getInteger("highScore")) {
            GameWorld.isRecord = true;
            AssetLoader.prefs.putInteger("highScore", GameWorld.score);
            currentState = State.RECORD;
        } else
            currentState = State.SCORE;
    }

    public void draw(ShapeRenderer shaper, SpriteBatch batcher) {
        shaper.begin(ShapeRenderer.ShapeType.Filled);
        shaper.setColor(0.75f, 0.75f, 0.75f, 1);
        shaper.rect(boundsBoard.x, boundsBoard.y, boundsBoard.width, boundsBoard.height);
        shaper.setColor(0, 0, 0, 1);
        shaper.rect(boundsMenu.x, boundsMenu.y, boundsMenu.width, boundsMenu.height);
        shaper.rect(boundsRestart.x, boundsRestart.y, boundsRestart.width, boundsRestart.height);
        if((currentState == State.SCORE || currentState == State.RECORD) && gameWorld.assetLoader.isInternet
                && AssetLoader.prefs.getInteger("countRestore") < 2 && GameWorld.score >= 150) {
            shaper.setColor(1, 1, 1, 1);
            shaper.rect(boundsRestore.x, boundsRestore.y, boundsRestore.width, boundsRestore.height);
        }
        shaper.end();
        batcher.begin();
        float width = 0;
        float height = 0;
        String text = "";

        if (isRecord()) {
            text = "NEW RECORD!";
        }
        else {
            if(currentState == State.PAUSE)
                text = "PAUSE";
            else
                text = "SCORE";
        }

        width = TextSize.getWidth(gameWorld.assetLoader.font, text);
        height = TextSize.getHeight(gameWorld.assetLoader.font, text);

        gameWorld.assetLoader.font.draw(batcher, text, boundsBoard.x + boundsBoard.width / 2 - width / 2, boundsBoard.y + height / 2);
        width = TextSize.getWidth(gameWorld.assetLoader.font, GameWorld.score + "");
        height = TextSize.getHeight(gameWorld.assetLoader.font, GameWorld.score + "");
        gameWorld.assetLoader.font.draw(batcher, GameWorld.score + "", boundsBoard.x + boundsBoard.width / 5,
                boundsBoard.y + boundsBoard.height / 10 * 4);
        batcher.end();
        Money.draw(shaper, batcher, boundsBoard.x + boundsBoard.width - boundsBoard.width / 3 , boundsBoard.y + boundsBoard.height / 10 * 4, GameWorld.currentMoney);
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

    public boolean isClickedRestore(float x, float y) {
        return (gameWorld.assetLoader.isInternet) ? boundsRestore.contains(x, y) : false;
    }

    public boolean isPause() {
        return currentState == State.PAUSE;
    }

    public boolean isScore() {
        return currentState == State.SCORE;
    }

    public boolean isRecord() {
        return currentState == State.RECORD;
    }

    public void setState(State currentState) {
        this.currentState = currentState;
        float x = gameWorld.WIDTH / 6;
        float y = gameWorld.HEIGHT / 4;
        float width = gameWorld.WIDTH / 4 * 2.7f;
        float height = width;
        if((currentState == State.SCORE || currentState == State.RECORD) && gameWorld.assetLoader.isInternet
                && AssetLoader.prefs.getInteger("countRestore") < 2 && GameWorld.score >= 150) {
            boundsMenu.set(x + width - gameWorld.WIDTH / 7 * 1.3f - width / 20, y + height / 10 * 7, gameWorld.WIDTH / 7 * 1.3f, gameWorld.WIDTH / 7);
            boundsRestart.set(x + width / 20, y + height / 10 * 7, gameWorld.WIDTH / 7 * 1.3f, gameWorld.WIDTH / 7);
            boundsRestore.set(x + width / 2 - (gameWorld.WIDTH / 7 * 1.3f) / 2, y + height / 10 * 7, gameWorld.WIDTH / 7 * 1.3f, gameWorld.WIDTH / 7);
        }
        else {
            boundsRestore.set(-100, -100, gameWorld.WIDTH / 7 * 1.5f, gameWorld.WIDTH / 7);
            boundsMenu.set(x + width - gameWorld.WIDTH / 7 * 1.5f - width / 9, y + height / 10 * 7, gameWorld.WIDTH / 7 * 1.5f, gameWorld.WIDTH / 7);
            boundsRestart.set(x + width / 9, y + height / 10 * 7, gameWorld.WIDTH / 7  * 1.5f, gameWorld.WIDTH / 7);
        }
    }
}
