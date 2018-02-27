package com.mygdx.minerbob.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.TextSize;

/**
 * Created by vovan on 04.01.2018.
 */

public class TrainingForm {

    private GameWorld gameWorld;
    private Rectangle board, boundsCancel;
    private TextureRegion easy, low, medium, hard, dead, money;
    private float width, height;
    private int currFrame, flag = 0, direction = 0;
    private final int maxFrames = 2;
    private long timeNow, lastTime = 0;
    private float rad = 6f, pos;
    private Vector2 position, velocity, acceleration, tempVector;
    private boolean isOnBlock = true;
    private int countClick = 0;
    private float alpha = 1f;
    private boolean isUp = false;

    private Circle circleLeft, circleRight;

    public TrainingForm(GameWorld gameWorld){
        circleLeft = new Circle();
        circleRight = new Circle();
        this.gameWorld = gameWorld;
        width = gameWorld.WIDTH / 5;
        height = gameWorld.WIDTH / 5 * 180 / 200 - 2;
        position = new Vector2(gameWorld.WIDTH / 2 - gameWorld.WIDTH / 10, gameWorld.HEIGHT / 2 - gameWorld.HEIGHT / 7 + 3f);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        tempVector = new Vector2(0, 0);
        board = new Rectangle(0, 0, gameWorld.WIDTH, gameWorld.HEIGHT);
        boundsCancel = new Rectangle(gameWorld.WIDTH - gameWorld.buttonSize - gameWorld.MARGIN, gameWorld.MARGIN,
                gameWorld.buttonSize, gameWorld.buttonSize);
        easy = gameWorld.assetLoader.earthTextures.get(0).get(0);
        low = gameWorld.assetLoader.clayTextures.get(0).get(0);
        medium = gameWorld.assetLoader.stoneTextures.get(0).get(0);
        hard = gameWorld.assetLoader.diamondTextures.get(0).get(0);
        dead = gameWorld.assetLoader.deadTextures.get(0);
        money = gameWorld.assetLoader.goldTextures.get(0).get(0);
        currFrame = 0;
    }

    public void draw(ShapeRenderer renderer, SpriteBatch batcher, float runTime) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(0f, 0f, 0f, 0.8f);
        renderer.rect(board.x, board.y, board.width, board.height);
        renderer.end();

        float x = gameWorld.WIDTH / 22f, y = 5f;
        float textWidth, textHeight;
        switch(currFrame) {
            case 0:
                batcher.begin();
                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "Welcome to Miner Bob");
                gameWorld.assetLoader.font.draw(batcher, "Welcome to Miner Bob", gameWorld.WIDTH / 2 - textWidth / 2, y);
                y += 10f;

                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "Block Types");
                gameWorld.assetLoader.font.draw(batcher, "Block Types", gameWorld.WIDTH / 2 - textWidth / 2, y);
                y += 10f;

                gameWorld.assetLoader.font.getData().setScale(0.08f, -0.08f);
                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "easy");
                batcher.draw(easy, x, y, width, height);
                gameWorld.assetLoader.font.draw(batcher, "easy", x + width / 2 - textWidth / 2, y + height + gameWorld.MARGIN);
                x += width + 3f;

                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "low");
                batcher.draw(low, x, y, width, height);
                gameWorld.assetLoader.font.draw(batcher, "low", x + width / 2 - textWidth / 2, y + height + gameWorld.MARGIN);
                x += width + 3f;

                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "medium");
                batcher.draw(medium, x, y, width, height);
                gameWorld.assetLoader.font.draw(batcher, "medium", x + width / 2 - textWidth / 2, y + height + gameWorld.MARGIN);
                x += width + 3f;

                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "hard");
                batcher.draw(hard, x, y, width, height);
                gameWorld.assetLoader.font.draw(batcher, "hard", x + width / 2 - textWidth / 2, y + height + gameWorld.MARGIN);
                y += height + gameWorld.MARGIN + 5f;

                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "impossible");
                x = gameWorld.WIDTH / 22f + width * 2 + 3f - width / 2 - width;
                batcher.draw(dead, x, y, width, height);
                gameWorld.assetLoader.font.draw(batcher, "impossible", x + width / 2 - textWidth / 2, y + height + gameWorld.MARGIN);

                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "money");
                x = gameWorld.WIDTH / 22f + width * 3 + 6f - width / 2;
                batcher.draw(money, x, y, width, height);
                gameWorld.assetLoader.font.draw(batcher, "money", x + width / 2 - textWidth / 2, y + height + gameWorld.MARGIN);
                y += height + gameWorld.MARGIN + 10f;

                gameWorld.assetLoader.font.getData().setScale(0.1f, -0.1f);
                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "Special Types");
                gameWorld.assetLoader.font.draw(batcher, "Special Types", gameWorld.WIDTH / 2 - textWidth / 2, y);
                gameWorld.assetLoader.font.getData().setScale(0.08f, -0.08f);

                x = gameWorld.WIDTH / 2f - width - 1.5f;
                y += 10f;
                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "dead");
                batcher.draw(gameWorld.assetLoader.lavaTextures.get(0), x, y, width, height);
                gameWorld.assetLoader.font.draw(batcher, "dead", x + width / 2 - textWidth / 2, y + height + gameWorld.MARGIN);
                x += width + 3f;

                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "slow");
                batcher.draw(gameWorld.assetLoader.timeTextures.get(0).get(0), x, y, width, height);
                gameWorld.assetLoader.font.draw(batcher, "slow", x + width / 2 - textWidth / 2, y + height + gameWorld.MARGIN);
                y += height + gameWorld.MARGIN + 5f;

                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "disorientation");
                batcher.draw(gameWorld.assetLoader.disorientationTextures.get(0).get(0), gameWorld.WIDTH / 2 - width / 2, y, width, height);
                gameWorld.assetLoader.font.draw(batcher, "disorientation", gameWorld.WIDTH / 2 - textWidth / 2, y + height + gameWorld.MARGIN);

                textHeight = TextSize.getHeight(gameWorld.assetLoader.font, "Touch to continue");
                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "Touch to continue");
                y = gameWorld.HEIGHT - gameWorld.MARGIN * 1.5f - textHeight;
                gameWorld.assetLoader.font.draw(batcher, "Touch to continue...", gameWorld.WIDTH / 2 - textWidth / 2, y);

                batcher.end();
                gameWorld.assetLoader.font.getData().setScale(0.1f, -0.1f);
                break;
            case 1:
                batcher.begin();
                x = gameWorld.WIDTH / 22;
                y = 5f;
                gameWorld.assetLoader.font.getData().setScale(0.1f, -0.1f);
                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "Combs");
                gameWorld.assetLoader.font.draw(batcher, "Combs", gameWorld.WIDTH / 2 - textWidth / 2, y);

                y += 13f;
                gameWorld.assetLoader.font.getData().setScale(0.08f, -0.08f);
                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "2X");
                gameWorld.assetLoader.font.draw(batcher, "2X", gameWorld.WIDTH / 2 - textWidth / 2, y - 5f);

                y += 2f;
                for (int i = 0; i < 4; i++) {
                    batcher.draw(low, x, y, width, height);
                    x += width + 3f;
                }
                y += height + gameWorld.MARGIN + 3f;

                textHeight = TextSize.getHeight(gameWorld.assetLoader.font, "2X");
                x = gameWorld.WIDTH / 22;
                for (int i = 0; i < 3; i++) {
                    batcher.draw(medium, x, y, width, height);
                    x += width + 3f;
                }
                gameWorld.assetLoader.font.draw(batcher, "3X", x, y + height / 2 - textHeight / 2);
                y += height + gameWorld.MARGIN + 3f;

                x = gameWorld.WIDTH / 22;
                for (int i = 0; i < 2; i++) {
                    batcher.draw(hard, x, y, width, height);
                    x += width + 3f;
                }
                gameWorld.assetLoader.font.draw(batcher, "5X", x, y + height / 2 - textHeight / 2);

                textHeight = TextSize.getHeight(gameWorld.assetLoader.font, "Touch to continue");
                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "Touch to continue");
                y = gameWorld.HEIGHT - gameWorld.MARGIN * 1.5f - textHeight;
                gameWorld.assetLoader.font.draw(batcher, "Touch to continue...", gameWorld.WIDTH / 2 - textWidth / 2, y);
                batcher.end();
                gameWorld.assetLoader.font.getData().setScale(0.1f, -0.1f);
                break;
            case 2:
                x = gameWorld.WIDTH / 8;
                y = 5f;
                renderer.setAutoShapeType(true);
                renderer.begin(ShapeRenderer.ShapeType.Filled);
                renderer.setColor(1f, 1f, 1f, 1f);
                circleLeft.set(x, gameWorld.HEIGHT - gameWorld.HEIGHT / 7, rad);
                circleRight.set(gameWorld.WIDTH - x, gameWorld.HEIGHT - gameWorld.HEIGHT / 7, rad);
                renderer.circle(x, gameWorld.HEIGHT - gameWorld.HEIGHT / 7, rad, 100);
                renderer.circle(gameWorld.WIDTH - x, gameWorld.HEIGHT - gameWorld.HEIGHT / 7, rad, 100);
                Gdx.gl.glLineWidth(3f);
                renderer.set(ShapeRenderer.ShapeType.Line);
                renderer.circle(x, gameWorld.HEIGHT - gameWorld.HEIGHT / 7, rad + 2f, 1000);
                renderer.circle(gameWorld.WIDTH - x, gameWorld.HEIGHT - gameWorld.HEIGHT / 7, rad + 2f, 1000);
                renderer.end();
                Gdx.gl.glLineWidth(1f);

                batcher.begin();
                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "Moving");
                gameWorld.assetLoader.font.draw(batcher, "Moving", gameWorld.WIDTH / 2 - textWidth / 2, y);
                gameWorld.assetLoader.font.draw(batcher, "TAP", gameWorld.WIDTH / 22, gameWorld.HEIGHT - gameWorld.HEIGHT / 3.7f);
                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "TAP");
                gameWorld.assetLoader.font.draw(batcher, "TAP", gameWorld.WIDTH - gameWorld.WIDTH / 22 - textWidth,
                        gameWorld.HEIGHT - gameWorld.HEIGHT / 3.7f);
                batcher.draw(gameWorld.assetLoader.buttonBackWhite, boundsCancel.x, boundsCancel.y, boundsCancel.width, boundsCancel.height);
                if(isOnBlock)
                    batcher.draw((TextureRegion) gameWorld.assetLoader.currentAnimation.getKeyFrame(runTime), position.x, position.y,
                            gameWorld.WIDTH / 5, gameWorld.HEIGHT / 7);
                else
                    batcher.draw(gameWorld.assetLoader.currentTexture, position.x, position.y, gameWorld.WIDTH / 5, gameWorld.HEIGHT / 7);
                y = gameWorld.HEIGHT / 2;
                x = 0;
                for(int i = 0; i < 5; i++) {
                    batcher.draw(gameWorld.assetLoader.clayTextures.get(i).get(0), x, y, width, height);
                    x += width;
                }

                if (countClick > 4) {
                    String text = "TAP TO CONTINUE";
                    float width = TextSize.getWidth(gameWorld.assetLoader.font, text);
                    if (isUp) {
                        alpha += 0.01;
                        isUp = alpha < 1f;
                    } else {
                        alpha -= 0.01;
                        isUp = alpha < 0.4;
                    }
                    gameWorld.assetLoader.font.setColor(1, 1, 1, alpha);
                    gameWorld.assetLoader.font.draw(batcher, text, gameWorld.WIDTH / 2 - width / 2, gameWorld.buttonSize * 2);
                    gameWorld.assetLoader.font.setColor(1, 1, 1, 1f);
                }

                batcher.end();
                break;
        }
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public void update(float delta) {
        if(currFrame == 2) {
            if ((timeNow = gameWorld.currentTime) - lastTime > 10) {
                switch (flag) {
                    case 0:
                        rad -= 0.1f;
                        if(rad <= 3f)
                            flag = 1;
                        break;
                    case 1:
                        rad += 0.1f;
                        if(rad >= 6f)
                            flag = 0;
                        break;
                }
                lastTime = timeNow;
            }

            if (position.y > gameWorld.HEIGHT / 2 - gameWorld.HEIGHT / 7 + 3f) {
                velocity.y = 0;
                velocity.x = 0;
                acceleration.y = 0;
                if(direction == 1)
                    pos += width;
                if(direction == 2)
                    pos -= width;
                position.x = pos;
                position.y = gameWorld.HEIGHT / 2 - gameWorld.HEIGHT / 7 + 3f;
                isOnBlock = true;
            }

            tempVector.set(acceleration.x, acceleration.y);
            velocity.add(tempVector.scl(delta));

            tempVector.set(velocity.x, velocity.y);
            position.add(tempVector.scl(delta));
        }
    }

    private boolean isTouchRight(float x, float y) {
        return circleRight.contains(x, y);
    }

    private boolean isTouchLeft(float x, float y) {
        return circleLeft.contains(x, y);
    }

    public boolean isClicked(float x, float y) {
        if(currFrame < 2) {
            currFrame++;
            if(currFrame == 1)
            {
                pos = position.x = gameWorld.WIDTH / 2 - gameWorld.WIDTH / 10;
                position.y = gameWorld.HEIGHT / 2 - gameWorld.HEIGHT / 14 + 2f;
                direction = 0;
            }
        }
        else {
            if(isOnBlock) {
                if (isTouchRight(x, y)) {
                    if (position.x + gameWorld.WIDTH / 5 < gameWorld.WIDTH) {
                        countClick++;
                        velocity.x = gameWorld.WIDTH - 10;
                        direction = 1;
                        velocity.y = -gameWorld.HEIGHT;
                        acceleration.y = -velocity.y * 10f;
                        isOnBlock = false;
                    } else
                        direction = 0;
                }
                else if (isTouchLeft(x, y)) {
                    if (position.x > width - 1f) {
                        countClick++;
                        velocity.x = -gameWorld.WIDTH + 10;
                        direction = 2;
                        velocity.y = -gameWorld.HEIGHT;
                        acceleration.y = -velocity.y * 10f;
                        isOnBlock = false;
                    } else
                        direction = 0;
                } else if (countClick > 4) {
                    gameWorld.setState(GameWorld.GameState.RUNNING);
                    countClick = 0;
                    currFrame = 0;
                    AssetLoader.prefs.putBoolean("isFirstRun", false);
                    AssetLoader.prefs.flush();
                }
            }
        }
        return board.contains(x, y);
    }

    public void onBackClick() {
        if(currFrame == 0)
            gameWorld.setState(GameWorld.GameState.MENU);
        else
            currFrame--;
    }

    public boolean isClickedCancel(float x, float y) {
        if(boundsCancel.contains(x, y) && currFrame == maxFrames) {
            currFrame = 0;
            return  true;
        }
        else
            return  false;
    }
}
