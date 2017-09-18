package com.mygdx.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameobjects.Actor;
import com.mygdx.game.gameobjects.Field;
import com.mygdx.game.gameobjects.RowBlock;
import com.mygdx.game.screen.GameScreen;

/**
 * Created by Алексей on 10.09.2017.
 */

public class GameWorld {
    private Actor actor;
    private RowBlock rowBlock;
    private Field field;
    public static boolean isRecord;
    public static boolean isEnd;
    public static int score = 0;

    public GameWorld() {
        isRecord = false;
        isEnd = false;
        actor = new Actor(GameScreen.WIDTH / 5 * 2 + 1, -10, GameScreen.WIDTH / 5 - 2, GameScreen.HEIGHT / 7);
        rowBlock = new RowBlock(this);
        field = new Field();
    }

    public void update(float delta) {
       // Gdx.app.log("GameWorld", "update");
       // Gdx.app.log("FPS", 1 / delta + "");
        field.update(delta);
        actor.update(delta);
        rowBlock.update(delta);

        if (!actor.getAlive())
            stop();

    }

    public void stop()
    {
        actor.stop();
        rowBlock.stop();
    }

    public void restart() {
        isRecord = false;
        score = 0;
        actor.restart(GameScreen.WIDTH / 5 * 2 + 1, -10, GameScreen.WIDTH / 5 - 2, GameScreen.HEIGHT / 7);
        rowBlock.restart();
    }

    public Actor getActor() {
        return actor;
    }

    public RowBlock getRowBlock() {
        return rowBlock;
    }

    public Field getField() {
        return field;
    }
}
