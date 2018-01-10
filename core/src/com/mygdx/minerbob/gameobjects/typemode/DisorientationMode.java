package com.mygdx.minerbob.gameobjects.typemode;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.TextSize;

/**
 * Created by Алексей on 07.01.2018.
 */

public class DisorientationMode extends TypeMode {
    public DisorientationMode (GameWorld gameWorld) {
        super(gameWorld);
    }

    @Override
    public void execute(float x, float y) {
        super.execute(x, y);
        if (gameWorld.getActor().getOnBlock() && gameWorld.getActor().getAlive()) {
            if (isTouchRight(x)) {
                setStepLeft();
            } else if (isTouchLeft(x)) {
                setStepRight();
            }

            if (!isTouchLeft(x) && !isTouchRight(x))
                gameWorld.getActor().hasCurrentBlock = false;

            gameWorld.getActor().setOnBlock(false);
        }
    }

    @Override
    public TypeMode getMode() {
        if (gameWorld.currentTime - time > 3000)
            return gameWorld.normalMode;
        return this;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void drawTime(SpriteBatch batcher) {
        int sec = (int)(3 - (gameWorld.currentTime - time) / 1000);
        sec = sec < 0 ? 0 : sec;
        gameWorld.assetLoader.font.draw(batcher,  sec + "",
                gameWorld.WIDTH / 2 - TextSize.getWidth(gameWorld.assetLoader.font, sec + "") / 2,
                gameWorld.buttonSize + gameWorld.MARGIN);
    }

    @Override
    public String getName() {
        return "Disorientation";
    }
}
