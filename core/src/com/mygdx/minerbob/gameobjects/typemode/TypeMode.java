package com.mygdx.minerbob.gameobjects.typemode;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.minerbob.gameworld.GameWorld;

/**
 * Created by Алексей on 07.01.2018.
 */

public class TypeMode {
    protected GameWorld gameWorld;
    protected long time;
    protected long diffTime;

    public TypeMode(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void execute(float x, float y) {
        if (gameWorld.getActor().getOnBlock() && gameWorld.getActor().getAlive()) {
            gameWorld.getActor().position.y -= 1;
            gameWorld.getActor().rectangleBounds.y -= 1;
            gameWorld.getActor().velocity.y = -gameWorld.HEIGHT;
            gameWorld.getActor().acceleration.y = -gameWorld.getActor().velocity.y * 10f;
        }
    }

    protected boolean isTouchRight(float x) {
        //return x > gameWorld.getActor().position.x + gameWorld.getActor().getWidth();
        return  x > gameWorld.WIDTH / 2;
    }

    protected boolean isTouchLeft(float x) {
     //   return  x < gameWorld.getActor().position.x;
        return x < gameWorld.WIDTH / 2;
    }

    protected void setStepLeft() {
        if (gameWorld.getActor().position.x - gameWorld.WIDTH / 5 > 0) {
            gameWorld.getActor().velocity.x = -gameWorld.WIDTH + 10;
            gameWorld.getActor().hasCurrentBlock = true;
        }
    }

    protected void setStepRight() {
        if (gameWorld.getActor().position.x + gameWorld.WIDTH / 5 < gameWorld.WIDTH) {
            gameWorld.getActor().velocity.x = gameWorld.WIDTH - 10;
            gameWorld.getActor().hasCurrentBlock = true;
        }
    }

    public String getName() {
        return "";
    }

    public TypeMode getMode() {
        return null;
    }

    public void start() {
        time = gameWorld.currentTime;
    }

    public void drawTime(SpriteBatch batcher) {

    }

    public void pause() {
        diffTime = gameWorld.currentTime - time;
    }

    public void resume() {
        time = gameWorld.currentTime - diffTime;
    }
}
