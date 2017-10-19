package com.mygdx.minerbob.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.minerbob.gameobjects.typeblock.ITypeBlock;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.Money;
import com.mygdx.minerbob.screen.GameScreen;

/**
 * Created by Алексей on 11.09.2017.
 */

public class Block {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 staticPosition;
    private Vector2 tempVector;

    private ITypeBlock type;

    private int countAnim = 0;
    private float tempKick = 0;
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
        tempVector = new Vector2(0, 0);
    }

    public Block(float x, float y, float width, float height, float speed, com.mygdx.minerbob.gameobjects.typeblock.ITypeBlock type) {
        isDestroyed = false;
        countKick = 0;

        position = new Vector2(x, y);
        velocity = new Vector2(0, speed);
        tempVector = new Vector2(0, 0);

        this.width = width;
        this.height = height;
        this.type = type;
        subHeight = height / type.getLevel();
        rectangleBounds = new Rectangle(x, y, width, height);
        staticPosition.y = y;
    }

    public void update(float delta) {
        tempVector.set(velocity.x, velocity.y);
        position.add(tempVector.scl(delta));

        rectangleBounds.set(position.x, position.y, width, height);
        if(isDestroyed)
            countAnim = 9;

        tempVector.set(velocity.x, velocity.y);
        staticPosition.add(tempVector.scl(delta));
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
        staticPosition.y = y;
    }

    public boolean isCollised(GameWorld gameWorld, int rowIndex) {
        Actor actor = gameWorld.getActor();

        if(actor.getHasCurrentBlock() && actor.getBlock().equals(this)) {
            return false;
        }

        if (actor.getVelocity().y >= 0 && velocity.y != 0) {
            if (Intersector.overlaps(rectangleBounds, actor.getRectangleBounds()) && !isDestroyed) {

                if (!gameWorld.isCollisedSecond && gameWorld.isKickedFirst) {
                    gameWorld.isCollisedSecond = true;
                }

                actor.setCurrentBlock(this);
                actor.setHasCurrentBlock(false);
                actor.setPosition(position.x + 1, position.y - actor.getHeight() + 1);
                actor.setRectangleBounds(position.x + 1, position.y - actor.getHeight() + 1, actor.getWidth(), actor.getHeight());

                actor.setOnBlock(true);
                actor.setVelocity(0, 0);

                if (position.y <= GameScreen.HEIGHT - height)
                    kick(gameWorld);
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
        return type.getTexture(countAnim);
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public Color getColor() {
        return type.getColor();
    }

    public void restart(float x, float y, float width, float height, float velocityY, ITypeBlock type) {
        tempKick = 0;
        countAnim = 0;
        this.type = type;
        position.set(x, y);
        velocity.y = velocityY;
        this.width = width;
        this.height = height;
        subHeight = height / type.getLevel();
        isDestroyed = false;
        staticPosition.y = y;
    }

    private void kick(GameWorld gameWorld) {
        Actor actor = gameWorld.getActor();
        countKick++;
     /*   tempKick += 10 / type.getLevel();
        if (tempKick >= countAnim) {
            countAnim++;
            if(countAnim >= 10)
                countAnim = 9;
        }*/

        tempKick += subHeight;
        if (tempKick >= (GameScreen.HEIGHT/15) / 12f) {
            tempKick = 0;
            countAnim++;
            if(countAnim >= 10)
                countAnim = 9;
        }

        position.y += subHeight;
        height -= subHeight;
        rectangleBounds.set(position.x, position.y, width, height);

        actor.setPosition(actor.getX(), position.y - actor.getHeight() + 1);
        actor.setRectangleBounds(actor.getX(),  position.y - actor.getHeight() + 1, actor.getWidth(), actor.getHeight());

        if (height <= 0) {
            if (!gameWorld.isKickedFirst) {
                gameWorld.isKickedFirst = true;
            }

            tempKick = 0;
            countAnim = 0;

            if (gameWorld.lastDestroyed != null && !gameWorld.lastDestroyed.getName().equals("Earth") &&
                    !type.getName().equals("Gold") &&
                    gameWorld.lastDestroyed.getName().equals(type.getName())) {
                gameWorld.countCombo++;

                if (gameWorld.lastDestroyed.getName().equals("Clay") && gameWorld.countCombo >= 4
                        && gameWorld.scl < 2) {
                    gameWorld.scl = 2;
                    gameWorld.startCombo = TimeUtils.millis();
                }

                if (gameWorld.lastDestroyed.getName().equals("Stone") && gameWorld.countCombo >= 3
                        && gameWorld.scl < 3) {
                    gameWorld.scl = 3;
                    gameWorld.startCombo = TimeUtils.millis();
                }

                if (gameWorld.lastDestroyed.getName().equals("Diamond") && gameWorld.countCombo >= 2) {
                    gameWorld.scl = 5;
                    gameWorld.startCombo = TimeUtils.millis();
                }

            } else if (!type.getName().equals("Gold")){
                gameWorld.countCombo = 1;
                gameWorld.lastDestroyed = type;
            }

            if(TimeUtils.timeSinceMillis(gameWorld.startCombo) > 5000) {
                gameWorld.scl = 1;
                gameWorld.startCombo = 0;
            }


            GameWorld.score += type.getScore() * gameWorld.scl;

            if ( type.getName().equals("Gold") ) {
                Money.add(1 * gameWorld.scl);
                GameWorld.currentMoney++;
            }

            isDestroyed = true;
            actor.setOnBlock(false);
        }
    }

    public ITypeBlock getType() {
        return type;
    }

    public float getStaticY() {
        return staticPosition.y;
    }
}
