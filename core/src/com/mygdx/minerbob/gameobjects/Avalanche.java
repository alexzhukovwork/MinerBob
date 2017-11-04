package com.mygdx.minerbob.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
    private Color colorWhite;
    private Color colorBlack;
    private Color color;
    private float subHeight;
    private int countScore;

    public Avalanche(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        position = new Vector2(0, - gameWorld.HEIGHT / 7f - 5);
        velocity = new Vector2(0, 0);
        tempVector = new Vector2(0, 0);
        colorWhite = new Color();
        colorBlack = new Color();
        color = new Color();
        colorBlack.set(0, 0, 0, 1);
        colorWhite.set(1, 1, 1, 1);
        color.set(1, 1, 1, 1);
        rect = new Rectangle(0, position.y, gameWorld.WIDTH, gameWorld.HEIGHT / 7f);
        subHeight = rect.height / 3f;
        countScore = 1;
    }

    public void setVelocity(float x, float y) {
        velocity.x = x;
        velocity.y = y;
    }

    public void update(float delta) {
        if(countScore == (int)(GameWorld.score / 1000) && subHeight <= gameWorld.HEIGHT / 7f - subHeight / 2) {
            countScore++;
            subHeight += rect.height / 5f;
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

    public void draw(ShapeRenderer renderer, SpriteBatch batcher) {
        if (rect.y + rect.height >= 0) {
            renderer.begin(ShapeRenderer.ShapeType.Filled);
            /*if (color.equals(colorWhite))
                color.set(colorBlack);
            else color.set(colorWhite);
            */renderer.setColor(color);
            renderer.rect(rect.x, rect.y, rect.width, rect.height);
            renderer.end();
        }
    }
}