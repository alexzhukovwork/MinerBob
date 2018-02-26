package com.mygdx.minerbob.gameobjects;

import com.mygdx.minerbob.gameworld.GameWorld;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.minerbob.gameobjects.typeblock.ITypeBlock;

import com.mygdx.minerbob.helpers.Money;

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

    private boolean isCollisedLava = false;
    private boolean isDestroyed;

    private int countKick;
    private int countPosition;

    private Rectangle rectangleBounds;
    private Actor actor;
    private GameWorld gameWorld;

    public Block(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        actor = gameWorld.getActor();
        rectangleBounds = new Rectangle();
        position = new Vector2();
        velocity = new Vector2(0, 0);
        staticPosition = new Vector2(0, 0);
        tempVector = new Vector2(0, 0);
    }

    public void update(float delta) {
        tempVector.set(velocity.x, velocity.y);
        position.add(tempVector.scl(delta));

        rectangleBounds.set(position.x, position.y, width, height);
        if(isDestroyed)
            countAnim = 10;

        tempVector.set(velocity.x, velocity.y);
        staticPosition.add(tempVector.scl(delta));
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
        staticPosition.y = y;
    }

    public void setType(ITypeBlock type) {
        this.type = type;
        subHeight = height / type.getLevel();
    }

    public boolean isCollised(float delta) {
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
/*
                if (!actor.getOnBlock() && gameWorld.isSound)
                    gameWorld.assetLoader.fall.play(0.4f);
*/
                actor.setOnBlock(true);
                actor.setVelocity(0, 0);

                if(type.getName().equals("Lava")) {
                    actor.isOnLava = true;
                    if(!isCollisedLava) {
                        gameWorld.getRowBlock().timeLava = gameWorld.currentTime;
                        isCollisedLava = true;
                    }
                    if (gameWorld.currentTime - gameWorld.getRowBlock().timeLava > 300) {
                        actor.setAlive(false);
                    }
                } else {
                    actor.isOnLava = false;
                }

                if (position.y <= gameWorld.HEIGHT - height)
                    kick(delta);

                return true;
            }
            else if(isDestroyed && Intersector.overlaps(rectangleBounds, actor.getRectangleBounds()))
                actor.setOnBlock(false);
            else
                isCollisedLava = false;
        }

        return false;
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
        return type.getTexture(countPosition, countAnim);
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public Color getColor() {
        return type.getColor();
    }

    public void restart(float x, float y, float width, float height, float velocityY,
                        ITypeBlock type, int countPosition) {
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
        this.countPosition = countPosition;
    }

    private void kick(float delta) {
      /*  tempKick += 10 / type.getLevel();
        if (tempKick >= countAnim) {
            countAnim++;
            if(countAnim >= 10)
                countAnim = 9;
        }
        */


        tempKick += subHeight * delta * 60.0f;
        if (tempKick >= (gameWorld.getRowBlock().heightBlock) / 10f) {
            tempKick = 0;

            if(countAnim != 10) {
                countAnim++;
            }
        }

        if (gameWorld.isSound) {
            countKick++;
            if(countKick >= 10) {
                gameWorld.assetLoader.drill.play(0.8f);
                countKick = 0;
            }
        }

        position.y += subHeight * delta * 60.0f;
        height -= subHeight * delta * 60.0f;
        rectangleBounds.set(position.x, position.y, width, height);

        actor.setPosition(actor.getX(), position.y - actor.getHeight() + 1);
        actor.setRectangleBounds(actor.getX(),  position.y - actor.getHeight() + 1, actor.getWidth(), actor.getHeight());

//        if(type.getName().equals("Lava"))
//            if(TimeUtils.millis() - timeCollised > 100)
//            {
//                actor.setAlive(false);
//                Gdx.app.log("timeCol_kick", timeCollised + "");
//            }

        if (height <= 0) {

            if (type.getName().equals("Slow")) {
                gameWorld.getRowBlock().setSlowSpeed();
            }
            if (type.getName().equals("Lava"))
                actor.setAlive(false);

            if (type.getName().equals("Disorientation"))
                actor.setMode(gameWorld.disorientationMode);
            if(gameWorld.isSound)
               gameWorld.assetLoader.drill.stop();
            if (!gameWorld.isKickedFirst) {
                gameWorld.isKickedFirst = true;
                //if(gameWorld.isSound)
                    //gameWorld.assetLoader.explode.play(1.0f);
            }

            GameWorld.score += type.getScore() * gameWorld.scl;

            if (gameWorld.lastDestroyed != null && !gameWorld.lastDestroyed.getName().equals("Earth") &&
                    !type.getName().equals("Gold") &&
                    gameWorld.lastDestroyed.getName().equals(type.getName())) {
                gameWorld.countCombo++;

                if (gameWorld.lastDestroyed.getName().equals("Clay") && gameWorld.countCombo >= 4
                        && gameWorld.scl < 2) {
                    gameWorld.scl = 2;
                    gameWorld.startCombo = gameWorld.currentTime;
                }

                if (gameWorld.lastDestroyed.getName().equals("Stone") && gameWorld.countCombo >= 3
                        && gameWorld.scl < 3) {
                    gameWorld.scl = 3;
                    gameWorld.startCombo = gameWorld.currentTime;
                }

                if (gameWorld.lastDestroyed.getName().equals("Diamond") && gameWorld.countCombo >= 2) {
                    gameWorld.scl = 5;
                    gameWorld.startCombo = gameWorld.currentTime;
                }

            } else if (!type.getName().equals("Gold")){
                gameWorld.countCombo = 1;
                gameWorld.lastDestroyed = type;
            }

            if(gameWorld.currentTime - gameWorld.startCombo > 5000) {
                gameWorld.scl = 1;
                gameWorld.startCombo = 0;
            }

            if ( type.getName().equals("Gold") ) {
                gameWorld.moneyAnimation.setAttributes(position.x + width / 2, position.y, gameWorld.scl);
                Money.add(gameWorld.scl);
                GameWorld.currentMoney += gameWorld.scl;
                if(gameWorld.isSound) {
                    switch(gameWorld.scl) {
                        case 1:
                            gameWorld.assetLoader.money.play(1.0f);
                            break;
                        case 2:
                            gameWorld.assetLoader.moneycombox2.play(1.0f);
                            break;
                        case 3:
                            gameWorld.assetLoader.moneycombox3.play(1.0f);
                            break;
                        case 5:
                            gameWorld.assetLoader.moneycombox5.play(1.0f);
                            break;
                    }
                }
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
