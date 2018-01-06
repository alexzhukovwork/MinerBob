package com.mygdx.minerbob.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.Money;
import com.mygdx.minerbob.helpers.Record;
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
    private final int restoreScore = 150;
    private final int padding = 13;

    public enum State {
        PAUSE, RECORD, SCORE
    }

    public PauseForm(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        float x = gameWorld.WIDTH / 2 - gameWorld.dialogWidth / 2;
        float y = gameWorld.HEIGHT / 2 - gameWorld.dialogHeight / 2;
        boundsBoard = new Rectangle(x, y, gameWorld.dialogWidth, gameWorld.dialogHeight);
        y = boundsBoard.y + boundsBoard.height - boundsBoard.height / 5;
        boundsRestart = new Rectangle(boundsBoard.x + boundsBoard.width / padding, y,
                gameWorld.buttonDialogWidth, gameWorld.buttonDialogHeight);
        boundsRestore = new Rectangle(-100, -100, gameWorld.buttonDialogWidth, gameWorld.buttonSize);
        boundsMenu = new Rectangle(boundsBoard.x + boundsBoard.width - gameWorld.buttonDialogWidth - boundsBoard.width / padding,
                y, gameWorld.buttonDialogWidth, gameWorld.buttonDialogHeight);
    }

    public void checkRecord() {
        if (GameWorld.score > AssetLoader.prefs.getInteger("highScore")) {
            GameWorld.isRecord = true;
            AssetLoader.prefs.putInteger("highScore", GameWorld.score);
            currentState = State.RECORD;
        } else
            currentState = State.SCORE;
    }

    public void draw(SpriteBatch batcher) {
        float width = 0;
        float height = 0;

        if (isRecord()) {
            batcher.draw(gameWorld.assetLoader.bestTexture, boundsBoard.x, boundsBoard.y, boundsBoard.width, boundsBoard.height);
        }
        else {
            if(currentState == State.PAUSE)
                batcher.draw(gameWorld.assetLoader.pauseTexture, boundsBoard.x, boundsBoard.y, boundsBoard.width, boundsBoard.height);
            else
                batcher.draw(gameWorld.assetLoader.scoreTexture, boundsBoard.x, boundsBoard.y, boundsBoard.width, boundsBoard.height);
        }

        batcher.draw(gameWorld.assetLoader.menuTexture, boundsMenu.x, boundsMenu.y, boundsMenu.width, boundsMenu.height);
        batcher.draw(gameWorld.assetLoader.playMenuTexture, boundsRestart.x, boundsRestart.y, boundsRestart.width, boundsRestart.height);

        if((currentState == State.SCORE || currentState == State.RECORD) && gameWorld.assetLoader.isInternet
                && AssetLoader.prefs.getInteger("countRestore") < 2 && GameWorld.score >= restoreScore) {
            batcher.draw(gameWorld.assetLoader.videoMenuTexure, boundsRestore.x, boundsRestore.y, boundsRestore.width, boundsRestore.height);
        }

        Record.draw(batcher,boundsBoard.x + boundsBoard.width / padding,
                boundsBoard.y + boundsBoard.height / 15, GameWorld.score);
        width = TextSize.getWidth(gameWorld.assetLoader.font, GameWorld.currentMoney + "");
        height = TextSize.getHeight(gameWorld.assetLoader.font, GameWorld.currentMoney + "");
        Money.draw(batcher, boundsBoard.x + boundsBoard.width - boundsBoard.width / padding - width - height - 1f,
                boundsBoard.y + boundsBoard.height / 15, GameWorld.currentMoney); // -1f - это отступ между текстом и текстурой
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
        float y = boundsBoard.y + boundsBoard.height - boundsBoard.height / 5;
        if((currentState == State.SCORE || currentState == State.RECORD) && gameWorld.assetLoader.isInternet
                && AssetLoader.prefs.getInteger("countRestore") < 2 && GameWorld.score >= restoreScore) {
            boundsRestart.set(boundsBoard.x + boundsBoard.width / padding, y,
                    gameWorld.buttonDialogWidth, gameWorld.buttonDialogHeight);
            boundsRestore.set(boundsBoard.x + boundsBoard.width / 2 - gameWorld.buttonDialogWidth / 2, y,
                    gameWorld.buttonDialogWidth, gameWorld.buttonDialogHeight);
            boundsMenu.set(boundsBoard.x + boundsBoard.width - gameWorld.buttonDialogWidth - boundsBoard.width / padding,
                    y, gameWorld.buttonDialogWidth, gameWorld.buttonDialogHeight);
        }
        else {
            boundsRestore.set(-100, -100, gameWorld.buttonDialogWidth, gameWorld.buttonSize);
            boundsRestart.set(boundsBoard.x + boundsBoard.width / padding, y,
                    gameWorld.buttonDialogWidth, gameWorld.buttonDialogHeight);
            boundsMenu.set(boundsBoard.x + boundsBoard.width - gameWorld.buttonDialogWidth - boundsBoard.width / padding,
                    y, gameWorld.buttonDialogWidth, gameWorld.buttonDialogHeight);
        }
    }
}
