package com.mygdx.minerbob.ui.ShopHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;

/**
 * Created by Алексей on 20.09.2017.
 */

public class Item {
    private int id;
    private Rectangle bound;
    private boolean isBought;
    private boolean isSelected;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private int cost;
    private float x;
    private TextureRegion textureRegion;
    private Animation animation;
    private GameWorld gameWorld;

    public boolean isAnimation;

    public Item(GameWorld gameWorld, int id, float x, float y, float width, float height, int cost, TextureRegion textureRegion, Animation animation) {
        this.gameWorld = gameWorld;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        isAnimation = false;
        acceleration = new Vector2(0, 0);
        bound = new Rectangle(x, y, width, height);
        this.cost = cost;
        this.textureRegion = textureRegion;
        this.animation = animation;
        this.id = id;
    }

    public void draw(ShapeRenderer renderer, SpriteBatch batcher, float runTime) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(1, 1, 1, 1f);
        renderer.rect(position.x, position.y, bound.width, bound.height);
        renderer.end();
        batcher.begin();
        if (!isAnimation)
            batcher.draw(textureRegion, position.x, position.y, bound.width, bound.height);
        else
            batcher.draw((TextureRegion)animation.getKeyFrame(runTime), position.x, position.y, bound.width, bound.height);
        batcher.end();
        Gdx.gl.glLineWidth(3);
        if (!isBought) {
            batcher.begin();
            gameWorld.assetLoader.font.draw(batcher, cost + "", position.x, position.y);
            batcher.end();
        }
        if (isBought && !isSelected) {
            renderer.begin(ShapeRenderer.ShapeType.Line);
            renderer.setColor(0, 0.7f, 0, 1);
            renderer.rect(position.x, position.y, bound.width, bound.height);
            renderer.end();
        }
        if (isSelected) {
            renderer.begin(ShapeRenderer.ShapeType.Line);
            renderer.setColor(1, 0.45f, 0, 1);
            renderer.rect(position.x, position.y, bound.width, bound.height);
            renderer.end();
        }
        Gdx.gl.glLineWidth(1);

    }

    public boolean isClicked(float x, float y) {
        return bound.contains(x, y);
    }

    public boolean isMove() {
        return velocity.x != 0;
    }

    public void update(float delta) {

        if (velocity.x != 0 && Math.abs(x - position.x) >= gameWorld.WIDTH - gameWorld.WIDTH / 20) {
          //  acceleration.set(0, 0);
            velocity.set(0, 0);
            position.x = x - position.x > 0 ? x - gameWorld.WIDTH : x + gameWorld.WIDTH;
        }
        bound.set(position.x, position.y, bound.width, bound.height);
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

    public void setSelected(boolean selected) {
        AssetLoader.prefs.putBoolean("selected" + id, selected);
        AssetLoader.prefs.flush();
        isSelected = selected;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public boolean getBought() {
        return isBought;
    }

    public int getCost() {
        return cost;
    }

    public void setBought(boolean bought) {
        AssetLoader.prefs.putBoolean("bought" + id, bought);
        AssetLoader.prefs.flush();
        this.isBought = bought;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public Animation getAnimation() {
        return animation;
    }
}
