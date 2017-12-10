package com.mygdx.minerbob.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.screen.GameScreen;

/**
 * Created by Алексей on 30.10.2017.
 */

public class Avalanche {
    private GameWorld gameWorld;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 tempVector;
    private Rectangle rect;
    private Color colorWhite;
    private Color colorBlack;
    private Color color;
    private float subHeight;
    private int countScore;
    private float stateTime;
    private Animation.PlayMode state;

    public Avalanche(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        position = new Vector2(0, - gameWorld.HEIGHT / 7f);
        velocity = new Vector2(0, 0);
        tempVector = new Vector2(0, 0);
        colorWhite = new Color();
        colorBlack = new Color();
        color = new Color();
        colorBlack.set(0, 0, 0, 1);
        colorWhite.set(1, 1, 1, 1);
        color.set(1, 1, 1, 1);
        rect = new Rectangle(0, position.y, gameWorld.WIDTH, gameWorld.HEIGHT / 7f);
        subHeight = rect.height / 2.5f;
        countScore = 1;
        stateTime = 0f;
        state = Animation.PlayMode.LOOP;
    }

    public void setVelocity(float x, float y) {
        velocity.x = x;
        velocity.y = y;
    }

    public void update(float delta) {
        if(countScore == (int)(GameWorld.score / 300) && subHeight <= gameWorld.HEIGHT / 7f - subHeight / 2) {
            countScore++;
            subHeight += rect.height / 2.5f;
        }

        if ((rect.y + rect.height) < subHeight) {
            tempVector.y = velocity.y;
            position.add(tempVector.scl(delta));
            rect.x = position.x;
            rect.y = position.y;
        }
       // Gdx.app.log("length", "y = " + rect.y + "width = " + rect.width);
    }

    public void restart() {
        velocity.y = 0;
        countScore = 1;
        subHeight = rect.height / 3f;
        position.y = -gameWorld.HEIGHT / 7f;
        rect.y = position.y;
    }

    public void draw(SpriteBatch batcher) {
        if (rect.y + rect.height >= 0) {
//           renderer.begin(ShapeRenderer.ShapeType.Filled);
//            if (color.equals(colorWhite))
//                color.set(colorBlack);
//            else color.set(colorWhite);
//            renderer.setColor(color);
//            renderer.rect(rect.x, rect.y, rect.width, rect.height);
//            renderer.end();

            batcher.begin();
            batcher.draw((TextureRegion) gameWorld.assetLoader.lavaAnimation.getKeyFrame(stateTime),
                    rect.x, rect.y - 0.5f, rect.width, rect.height);
            batcher.end();
            stateTime += Gdx.graphics.getDeltaTime();
            if(gameWorld.assetLoader.lavaAnimation.isAnimationFinished(stateTime)) {
                stateTime = 0f;
                if (state == Animation.PlayMode.LOOP)
                    state = Animation.PlayMode.LOOP_REVERSED;
                else
                    state = Animation.PlayMode.LOOP;

                gameWorld.assetLoader.lavaAnimation.setPlayMode(state);
              //  gameWorld.assetLoader.lavaAnimation.setPlayMode();
            }
        }
    }
}
