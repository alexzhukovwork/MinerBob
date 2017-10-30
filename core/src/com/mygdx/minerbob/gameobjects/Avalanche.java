package com.mygdx.minerbob.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public Avalanche(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        position = new Vector2(0, - gameWorld.buttonSize - 5);
        velocity = new Vector2(0, 0);
        tempVector = new Vector2(0, 0);
        rect = new Rectangle(0, - gameWorld.buttonSize - 5, GameScreen.WIDTH, gameWorld.buttonSize / 2);
    }

    public void setVelocity(float x, float y) {
        velocity.x = x;
        velocity.y = y;
    }

    public void update(float delta) {
        if (rect.y < 0) {
            tempVector.y = velocity.y;
            position.add(tempVector.scl(delta));
            rect.x = position.x;
            rect.y = position.y;
        }
    }

    public void restart() {
        velocity.y = 0;
        position.y = -gameWorld.buttonSize - 5;
        rect.y = position.y;
    }

    public void draw(ShapeRenderer renderer, SpriteBatch batcher) {
        if (rect.y + rect.height >= 0) {
            renderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.setColor(1, 1, 1, 1);
            renderer.rect(rect.x, rect.y, rect.width, rect.height);
            renderer.end();
        }
    }
}
