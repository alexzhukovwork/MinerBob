package com.mygdx.game.ui.ShopHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.screen.GameScreen;

/**
 * Created by Алексей on 20.09.2017.
 */

public class Item {
    private Rectangle bound;
    private boolean isBought;
    private boolean isSelected;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private int cost;
    private float x;
    private TextureRegion textureRegion;

    public Item(float x, float y, float width, float height, int cost, TextureRegion textureRegion) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        bound = new Rectangle(x, y, width, height);
        this.cost = cost;
        this.textureRegion = textureRegion;
    }

    public void draw(ShapeRenderer renderer, SpriteBatch batcher) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(1, 1, 1, 1f);
        renderer.rect(position.x, position.y, bound.width, bound.height);
        renderer.end();
    }

    public boolean isClicked(float x, float y) {
        return bound.contains(x, y);
    }

    public boolean isMove() {
        return velocity.x != 0;
    }

    public void update(float delta) {

        if (velocity.x != 0 && Math.abs(x - position.x) >= GameScreen.WIDTH - GameScreen.WIDTH / 20) {
          //  acceleration.set(0, 0);
            velocity.set(0, 0);
            position.x = x - position.x > 0 ? x - GameScreen.WIDTH : x + GameScreen.WIDTH;
        }
           // velocity.add(acceleration.cpy().scl(delta));
            position.add(velocity.cpy().scl(delta));

    }

    public void setVelocity(float x, float y) {
        this.x = position.x;
        this.velocity.set(x, y);
    }

    public void setAcceleration(float x, float y) {
        this.acceleration.set(x, y);
    }
}
