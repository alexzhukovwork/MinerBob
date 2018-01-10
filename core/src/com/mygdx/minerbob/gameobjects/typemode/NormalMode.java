package com.mygdx.minerbob.gameobjects.typemode;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.minerbob.gameworld.GameWorld;

/**
 * Created by Алексей on 07.01.2018.
 */

public class NormalMode extends TypeMode{
    public NormalMode (GameWorld gameWorld) {
        super(gameWorld);
    }

    @Override
    public void execute(float x, float y) {
        super.execute(x, y);

        if (gameWorld.getActor().getOnBlock() && gameWorld.getActor().getAlive()) {
            if (isTouchRight(x)) {
                setStepRight();
            } else if (isTouchLeft(x)) {
                setStepLeft();
            }

            if (!isTouchLeft(x) && !isTouchRight(x))
                gameWorld.getActor().hasCurrentBlock = false;

            gameWorld.getActor().setOnBlock(false);
        }
    }

    @Override
    public TypeMode getMode() {
        return this;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void drawTime(SpriteBatch batcher) {

    }
}
