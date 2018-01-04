package com.mygdx.minerbob.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.TextSize;

/**
 * Created by vovan on 04.01.2018.
 */

public class TrainingForm {

    private GameWorld gameWorld;
    private Rectangle board;
    private TextureRegion easy, low, medium, hard, dead, money, actor;
    private float width, height;
    private int currFrame;
    private final int maxFrames = 2;
    private float timeNow, lastTime = 0f, time, last = 0f;
    private float rad = 6f, tx, ty;
    private int flag = 0, pos = 0;

    public TrainingForm(GameWorld gameWorld){
        this.gameWorld = gameWorld;
        tx = gameWorld.WIDTH / 2 - gameWorld.WIDTH / 10;
        ty = gameWorld.HEIGHT / 2 - gameWorld.HEIGHT / 14;
        width = gameWorld.WIDTH / 5;
        height = gameWorld.HEIGHT / 15;
        board = new Rectangle(0, 0, gameWorld.WIDTH, gameWorld.HEIGHT);
        easy = gameWorld.assetLoader.earthTextures.get(0).get(0);
        low = gameWorld.assetLoader.clayTextures.get(0).get(0);
        medium = gameWorld.assetLoader.stoneTextures.get(0).get(0);
        hard = gameWorld.assetLoader.diamondTextures.get(0).get(0);
        dead = gameWorld.assetLoader.deadTextures.get(0);
        money = gameWorld.assetLoader.goldTextures.get(0).get(0);
        actor = gameWorld.assetLoader.actorKick1;
        currFrame = 0;
    }

    public void draw(ShapeRenderer renderer, SpriteBatch batcher) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(0f, 0f, 0f, 0.8f);
        renderer.rect(board.x, board.y, board.width, board.height);
        renderer.end();

        float x = gameWorld.WIDTH / 22, y = 5f;
        float textWidth;
        switch(currFrame) {
            case 0:
                batcher.begin();
                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "Welcome to Miner Bob");
                gameWorld.assetLoader.font.draw(batcher, "Welcome to Miner Bob", gameWorld.WIDTH / 2 - textWidth / 2, y);
                y += 15f;

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
                y += height + gameWorld.MARGIN + 10f;
                x = gameWorld.WIDTH / 22 + width + 3f;

                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "dead");
                batcher.draw(dead, x, y, width, height);
                gameWorld.assetLoader.font.draw(batcher, "dead", x + width / 2 - textWidth / 2, y + height + gameWorld.MARGIN);
                x += width + 3f;

                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "money");
                batcher.draw(money, x, y, width, height);
                gameWorld.assetLoader.font.draw(batcher, "money", x + width / 2 - textWidth / 2, y + height + gameWorld.MARGIN);
                y += height + gameWorld.MARGIN + 15f;

                x = gameWorld.WIDTH / 22;
                gameWorld.assetLoader.font.getData().setScale(0.1f, -0.1f);
                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "Combs");
                gameWorld.assetLoader.font.draw(batcher, "Combs", gameWorld.WIDTH / 2 - textWidth / 2, y - 5f);

                y += 8f;
                gameWorld.assetLoader.font.getData().setScale(0.08f, -0.08f);
                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "2X");
                gameWorld.assetLoader.font.draw(batcher, "2X", gameWorld.WIDTH / 2 - textWidth / 2, y - 5f);

                y += 2f;
                for (int i = 0; i < 4; i++) {
                    batcher.draw(low, x, y, width, height);
                    x += width + 3f;
                }
                y += height + gameWorld.MARGIN + 3f;

                float textHeight = TextSize.getHeight(gameWorld.assetLoader.font, "2X");
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
                y += height + gameWorld.MARGIN + 3f;

                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "Touch to continue");
                gameWorld.assetLoader.font.draw(batcher, "Touch to continue...", gameWorld.WIDTH / 2 - textWidth / 2, y);

                batcher.end();
                gameWorld.assetLoader.font.getData().setScale(0.1f, -0.1f);
                break;
            case 1:
                x = gameWorld.WIDTH / 8;
                y = 5f;
                renderer.setAutoShapeType(true);
                renderer.begin(ShapeRenderer.ShapeType.Filled);
                renderer.setColor(1f, 1f, 1f, 1f);
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
                gameWorld.assetLoader.font.getData().setScale(0.08f, -0.08f);
                textWidth = TextSize.getWidth(gameWorld.assetLoader.font, "Touch to continue");
                textHeight = TextSize.getHeight(gameWorld.assetLoader.font, "Touch to continue");
                gameWorld.assetLoader.font.draw(batcher, "Touch to continue...", gameWorld.WIDTH / 2 - textWidth / 2, gameWorld.HEIGHT - textHeight - 3f);
                gameWorld.assetLoader.font.getData().setScale(0.1f, -0.1f);
                batcher.draw(actor, tx, ty, gameWorld.WIDTH / 5, gameWorld.HEIGHT / 7);
                batcher.end();

                if((timeNow = TimeUtils.nanoTime()) - lastTime > 100000) {
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
                    }
                    lastTime = timeNow;
                }

                if((time = TimeUtils.nanoTime()) - last > 500000000L) {
                    switch (pos) {
                        case 0:
                            tx = gameWorld.WIDTH / 2 - gameWorld.WIDTH / 10;
                            ty = gameWorld.HEIGHT / 2 - gameWorld.HEIGHT / 14;
                            break;
                        case 1:
                            tx += gameWorld.WIDTH / 5;
                            ty -= gameWorld.HEIGHT / 7;
                            break;
                        case 2:
                            tx += gameWorld.WIDTH / 5;
                            ty = gameWorld.HEIGHT / 2 - gameWorld.HEIGHT / 14;
                            break;
                        case 3:
                            ty -= gameWorld.HEIGHT / 7;
                            tx -= gameWorld.WIDTH / 5;
                            break;
                        case 4:
                            tx = gameWorld.WIDTH / 2 - gameWorld.WIDTH / 10;
                            ty = gameWorld.HEIGHT / 2 - gameWorld.HEIGHT / 14;
                            break;
                        case 5:
                            tx -= gameWorld.WIDTH / 5;
                            ty -= gameWorld.HEIGHT / 7;
                            break;
                        case 6:
                            tx -= gameWorld.WIDTH / 5;
                            ty = gameWorld.HEIGHT / 2 - gameWorld.HEIGHT / 14;
                            break;
                        case 7:
                            tx += gameWorld.WIDTH / 5;
                            ty -= gameWorld.HEIGHT / 7;
                            break;
                        case 8:
                            tx = gameWorld.WIDTH / 2 - gameWorld.WIDTH / 10;
                            ty = gameWorld.HEIGHT / 2 - gameWorld.HEIGHT / 14;
                            break;
                    }
                    pos++;
                    if(pos > 8)
                        pos = 0;
                    last = time;
                }
                break;
        }
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }
    public boolean isClicked(float x, float y) {
        currFrame++;
        if(board.contains(x, y) && currFrame == maxFrames) {
            currFrame = 0;
            return  true;
        }
        else
            return  false;
    }
}
