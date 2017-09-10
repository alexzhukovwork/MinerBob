package com.mygdx.game.gameworld;

import com.mygdx.game.gameobjects.Actor;
import com.mygdx.game.screen.GameScreen;

/**
 * Created by Алексей on 10.09.2017.
 */

public class GameWorld {
    private Actor actor;

    public GameWorld() {
        actor = new Actor(GameScreen.WIDTH / 2, GameScreen.HEIGHT / 2, GameScreen.WIDTH / 5, GameScreen.HEIGHT / 5);
    }

    public void update(float delta) {
        actor.update(delta);
    }

    public Actor getActor() {
        return actor;
    }
}
