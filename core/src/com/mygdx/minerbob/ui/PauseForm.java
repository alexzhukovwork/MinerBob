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

    public enum State {
        PAUSE, RECORD, SCORE
    }

    public PauseForm(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        float x = gameWorld.WIDTH / 6;
        float y = gameWorld.HEIGHT / 4;
        boundsRestore = new Rectangle(-100, -100, gameWorld.buttonDialogWidth, gameWorld.buttonSize);
        boundsBoard = new Rectangle(x, y, gameWorld.dialogWidth, gameWorld.dialogHeight);
        boundsMenu = new Rectangle(x + gameWorld.dialogWidth - gameWorld.buttonDialogWidth - gameWorld.dialogWidth / 9,
                y + gameWorld.dialogHeight / 10 * 7, gameWorld.buttonDialogWidth, gameWorld.buttonSize);
        boundsRestart = new Rectangle(x + gameWorld.dialogWidth / 9, y + gameWorld.dialogHeight / 10 * 7, gameWorld.buttonDialogWidth,
                gameWorld.buttonSize);
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
     /*   shaper.begin(ShapeRenderer.ShapeType.Filled);
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
        shaper.end();*/
        batcher.begin();
        float width = 0;
        float height = 0;
        String text = "";

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
                && AssetLoader.prefs.getInteger("countRestore") < 2 && GameWorld.score >= 150) {
            batcher.draw(gameWorld.assetLoader.videoMenuTexure, boundsRestore.x, boundsRestore.y, boundsRestore.width, boundsRestore.height);
        }

      //  width = TextSize.getWidth(gameWorld.assetLoader.font, text);
      //  height = TextSize.getHeight(gameWorld.assetLoader.font, text);

      //  gameWorld.assetLoader.font.draw(batcher, text, boundsBoard.x + boundsBoard.width / 2 - width / 2, boundsBoard.y + height / 2);
        //height = TextSize.getHeight(gameWorld.assetLoader.font, GameWorld.score + "");
        batcher.end();
        width = TextSize.getWidth(gameWorld.assetLoader.font, GameWorld.score + "");
        Record.draw(batcher,boundsRestart.x + boundsRestart.width / 2 - width - 1f, // -1f - это отступ между текстом и текстурой
                boundsBoard.y + boundsBoard.height / 10 * 4, GameWorld.score);
        width = TextSize.getWidth(gameWorld.assetLoader.font, GameWorld.currentMoney + "");
        Money.draw(batcher, boundsMenu.x + boundsMenu.width / 2 - width - 1f,
                boundsBoard.y + boundsBoard.height / 10 * 4, GameWorld.currentMoney);
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
        if((currentState == State.SCORE || currentState == State.RECORD) && gameWorld.assetLoader.isInternet
                && AssetLoader.prefs.getInteger("countRestore") < 2 && GameWorld.score >= 150) {
            boundsMenu.set(x + gameWorld.dialogWidth - gameWorld.buttonSize * 1.3f - gameWorld.dialogWidth / 20, y + gameWorld.dialogHeight / 10 * 7, gameWorld.buttonSize * 1.3f, gameWorld.buttonSize);
            boundsRestart.set(x + gameWorld.dialogWidth / 20, y + gameWorld.dialogHeight / 10 * 7, gameWorld.buttonSize * 1.3f, gameWorld.buttonSize);
            boundsRestore.set(x + gameWorld.dialogWidth / 2 - (gameWorld.buttonSize * 1.3f) / 2, y + gameWorld.dialogHeight / 10 * 7, gameWorld.buttonSize * 1.3f, gameWorld.buttonSize);
        }
        else {
            boundsRestore.set(-100, -100, gameWorld.buttonDialogWidth, gameWorld.buttonSize);
            boundsMenu.set(x + gameWorld.dialogWidth - gameWorld.buttonDialogWidth - gameWorld.dialogWidth / 9, y + gameWorld.dialogHeight / 10 * 7, gameWorld.buttonDialogWidth, gameWorld.buttonSize);
            boundsRestart.set(x + gameWorld.dialogWidth / 9, y + gameWorld.dialogHeight / 10 * 7, gameWorld.buttonSize  * 1.5f, gameWorld.buttonSize);
        }
    }
}
