package com.mygdx.minerbob.ui.ShopHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.Money;
import com.mygdx.minerbob.helpers.TextSize;

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

        batcher.begin();

        if(!isBought) {
            batcher.draw(gameWorld.assetLoader.item, position.x, position.y, bound.width, bound.height);
        }
        if (isBought && !isSelected) {
            batcher.draw(gameWorld.assetLoader.boughtItem, position.x, position.y, bound.width, bound.height);
        }
        if (isSelected) {
            batcher.draw(gameWorld.assetLoader.selectedItem, position.x, position.y, bound.width, bound.height);
        }
        if (!isBought) {
        /*    float width = TextSize.getWidth(gameWorld.assetLoader.font, "" + cost);
            float height = TextSize.getHeight(gameWorld.assetLoader.font, "" + cost);
            gameWorld.assetLoader.font.draw(batcher, cost + "", position.x + bound.width / 2 - width / 2,
                    position.y + bound.height / 2 - height / 2);
        */
            gameWorld.assetLoader.font.getData().setScale(0.075f, -0.075f);
            float textWidth = TextSize.getWidth(gameWorld.assetLoader.font, cost + "");
            //float textHeight = TextSize.getHeight(gameWorld.assetLoader.font, cost + "");
            gameWorld.assetLoader.font.draw(batcher, cost + "", position.x + bound.width / 2 - textWidth / 2,
                    position.y + bound.height - bound.height / 6);
            gameWorld.assetLoader.font.getData().setScale(0.1f, -0.1f);
            batcher.draw(textureRegion, position.x + bound.width / 4, position.y + 2,
                    bound.width / 2.0f, bound.height - 10);
        } else {
            if (!isAnimation)
                batcher.draw(textureRegion, position.x + bound.width / 6, position.y + 2,
                        bound.width / 1.5f, bound.height - 4);
            else
                batcher.draw((TextureRegion)animation.getKeyFrame(runTime),position.x + bound.width / 6,
                        position.y + 2, bound.width / 1.5f, bound.height - 4);
        }
        batcher.end();
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
