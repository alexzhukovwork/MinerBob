package com.mygdx.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.gameobjects.typeblock.ITypeBlock;
import com.mygdx.game.gameworld.GameWorld;
import com.mygdx.game.screen.GameScreen;

/**
 * Created by Алексей on 11.09.2017.
 */

public class Block {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 staticPosition;
    private ITypeBlock type;


    private float width, height;

    private float subHeight;

    private boolean isDestroyed;

    private int countKick;

    private Rectangle rectangleBounds;

    public Block() {
        rectangleBounds = new Rectangle();
        position = new Vector2();
        velocity = new Vector2(0, 0);
        staticPosition = new Vector2(0, 0);
    }

    public Block(float x, float y, float width, float height, float speed, ITypeBlock type) {
        isDestroyed = false;
        countKick = 0;
        position = new Vector2(x, y);
        velocity = new Vector2(0, speed);
        this.width = width;
        this.height = height;
        this.type = type;
        subHeight = height / type.getLevel();
        rectangleBounds = new Rectangle(x, y, width, height);
        staticPosition.y = y;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
        rectangleBounds.set(position.x, position.y, width, height);
        staticPosition.add(velocity.cpy().scl(delta));
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
        staticPosition.y = y;
    }

    public boolean isCollised(Actor actor, int rowIndex) {
        if(actor.getBlock() != null && actor.getBlock().equals(this)) {
            return false;
        }


        if (actor.getVelocity().y >= 0) {
            if (Intersector.overlaps(rectangleBounds, actor.getRectangleBounds()) && !isDestroyed) {
                actor.setCurrentBlock(this);
                actor.setBlock(null);
                actor.setPosition(position.x + 1, position.y - actor.getHeight() + 1);
                actor.setRectangleBounds(position.x + 1, position.y - actor.getHeight() + 1, actor.getWidth(), actor.getHeight());

                actor.setOnBlock(true);
                actor.setVelocity(0, 0);

                if (position.y <= GameScreen.HEIGHT - height)
                    kick(actor);
            }
            else if(isDestroyed && Intersector.overlaps(rectangleBounds, actor.getRectangleBounds()))
                actor.setOnBlock(false);
        }

        return actor.getOnBlock();
    }

    public void stop() {
        velocity.y = 0;
        velocity.x = 0;
    }

    public void setVelocity(float x, float y) {
        velocity.set(x, y);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public boolean getDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public TextureRegion getTexture() {
        return type.getTexture(countKick);
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public Color getColor() {
        return type.getColor();
    }

    public void restart(float x, float y, float width, float height, float velocityY, ITypeBlock type) {
        this.type = type;
        position.set(x, y);
        velocity.y = velocityY;
        this.width = width;
        this.height = height;
        subHeight = height / type.getLevel();
        isDestroyed = false;
        staticPosition.y = y;
    }

    private void kick(Actor actor) {
        countKick++;
        position.y += subHeight;
        height -= subHeight;
        rectangleBounds.set(position.x, position.y, width, height);

        actor.setPosition(actor.getX(), position.y - actor.getHeight() + 1);
        actor.setRectangleBounds(actor.getX(),  position.y - actor.getHeight() + 1, actor.getWidth(), actor.getHeight());

        if (height <= 0) {
            GameWorld.score += type.getScore();
            isDestroyed = true;
            actor.setOnBlock(false);
        }
    }

    public float getStaticY() {
        return staticPosition.y;
    }
}
