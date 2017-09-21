package com.mygdx.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.gameobjects.typeblock.ClayBlock;
import com.mygdx.game.gameobjects.typeblock.DiamondBlock;
import com.mygdx.game.gameobjects.typeblock.EarthBlock;
import com.mygdx.game.gameobjects.typeblock.ITypeBlock;
import com.mygdx.game.gameobjects.typeblock.StoneBlock;
import com.mygdx.game.gameobjects.typeblock.TitanBlock;
import com.mygdx.game.gameworld.GameWorld;
import com.mygdx.game.screen.GameScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Алексей on 11.09.2017.
 */

public class RowBlock {
    private List<List<Block>> rows;
    private List<List<Block>> rowsPause;

    private GameWorld gameWorld;

    private float heightBlock, widthBlock;

    private long lastTimeSpeed;

    private ITypeBlock earthBlock, clayBlock, stoneBlock, diamondBlock, titanBlock;
    private ArrayList<ITypeBlock> typeBlocks;


    private ArrayList<Boolean> bools = new ArrayList<Boolean>();

    private int minTitan = 0;
    private int maxTitan = 1;
    private int countTitan = 0;

    private Random random;

    private float speedRow = -20;

    private float earthLevel = 45;
    private float clayLevel = 60;
    private float stoneLevel = 100;
    private float diamondLevel = 120;



    public RowBlock(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        heightBlock = GameScreen.HEIGHT / 15;
        widthBlock = GameScreen.WIDTH / 5;
        lastTimeSpeed = TimeUtils.nanoTime();
        initObjects();
    }

    private void initObjects() {
        random = new Random();

        earthBlock = new EarthBlock(earthLevel);
        clayBlock = new ClayBlock(clayLevel);
        stoneBlock = new StoneBlock(stoneLevel);
        diamondBlock = new DiamondBlock(diamondLevel);
        titanBlock = new TitanBlock(5000);
        typeBlocks = new ArrayList<ITypeBlock>();

        typeBlocks.add(earthBlock);
        typeBlocks.add(clayBlock);
        typeBlocks.add(stoneBlock);
        typeBlocks.add(diamondBlock);


        rows = new ArrayList<List<Block>>();
        rowsPause = new ArrayList<List<Block>>();


        for(int i = 0; i < 4; i++) {
            rowsPause.add(new ArrayList<Block>());
            rows.add(new ArrayList<Block>());
            for (int j = 0; j < 5; j++) {
                rows.get(i).add(new Block());
                rowsPause.get(i).add(new Block());
            }
        }

        for(int i = 0; i < 5; i++) {
            bools.add(false);
        }

        restartRow();
    }

    private void restartType() {
        earthBlock.setLevel(earthLevel);
        clayBlock.setLevel(clayLevel);
        stoneBlock.setLevel(stoneLevel);
        diamondBlock.setLevel(diamondLevel);
        titanBlock.setLevel(5000);
    }

    private void restartRow() {
        countTitan = 0;
        float height = GameScreen.HEIGHT / 5 + heightBlock;

        for (int i = 0; i < 5; i++) {
            rows.get(0).get(i).restart(GameScreen.WIDTH / 5 * i, height, widthBlock, heightBlock, speedRow, earthBlock);
        }

        for (int i = 1; i < 4; i++) {
            generateRow(i, GameScreen.WIDTH / 5, (i+1) * height, speedRow);
        }
    }

    private int rnd(int min, int max) {
        max -= min;
        return (int)(Math.random() * ++max) + min;
    }

    private void generateRow(int index, float x, float y, float speed) {
        int maxCount = rnd(maxTitan - 1, maxTitan);
        int j = 0;

        for (int i = 0; i < 5; i++)
            bools.set(i, false);

        j = rnd(0, 1);

        if (j == 0) {
            j = rnd(0, 4);
            rows.get(index).get(j).restart(x * j, y, widthBlock, heightBlock, speed, earthBlock);
            bools.set(j, true);
        } else {
            j = rnd(0, 4);
            rows.get(index).get(j).restart(x * j, y, widthBlock, heightBlock, speed, clayBlock);
            bools.set(j, true);
        }

        for (int i = 0; i < maxCount; i++) {
            j = rnd(0, 4);
            if (!bools.get(j)) {
                bools.set(j, true);
                rows.get(index).get(j).restart(x * j, y, widthBlock, heightBlock, speed, titanBlock);
            }
        }

        for(int i = 0; i < 5; i++){
            if (!bools.get(i))
                rows.get(index).get(i).restart(x * i, y, widthBlock, heightBlock, speed, getRandomType());
        }

    }

    private ITypeBlock getRandomType() {
        ITypeBlock type = typeBlocks.get(rnd(0, typeBlocks.size() - 1));
        return type;
    }

    public void update(float delta) {
        if (!gameWorld.getActor().getAlive())
            return;

        int i = 0;
        float speed = 0;

        for (List<Block> l : rows) {
            countTitan = 0;
            for (Block b : l) {

                if (TimeUtils.nanoTime() - lastTimeSpeed > 3000000000L) {
                    if (b.getVelocity().y > - 45) {
                        b.setVelocity(0, b.getVelocity().y + (b.getVelocity().y / 24));
                        speed = b.getVelocity().y + (b.getVelocity().y / 24);
                    }
                }
                b.update(delta);

                if( b.isCollised(gameWorld.getActor(), i) ) {

                }
            }
            if (l.get(0).getStaticY() + heightBlock < 0) {
                generateRow(i, l.get(1).getX(), GameScreen.HEIGHT, l.get(0).getVelocity().y);
            }
            i++;
        }

         if (TimeUtils.nanoTime() - lastTimeSpeed > 3000000000L) {
             lastTimeSpeed = TimeUtils.nanoTime();
             if (rows.get(0).get(0).getVelocity().y > - 45) {
                 for (ITypeBlock type : typeBlocks)
                    type.setLevel(type.getLevel() - (-speed * 36 / 100 ) / 8);
             }
             maxTitan++;

             if(maxTitan >= 2)
                maxTitan = 2;
        }
    }

    public void draw(ShapeRenderer shaper, SpriteBatch batcher) {
        shaper.begin(ShapeRenderer.ShapeType.Filled);

        for (List<Block> l : rows) {
            for (Block b : l) {
                if (!b.getDestroyed()) {
                    shaper.setColor(b.getColor());
                    shaper.rect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
                }
            }
        }

        shaper.end();

        shaper.begin(ShapeRenderer.ShapeType.Line);
        shaper.setColor(0, 0, 0, 1);

        for (List<Block> l : rows) {
            for (Block b : l) {
                if (!b.getDestroyed()) {
                    shaper.rect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
                }
            }
        }

        shaper.end();
      /*  batcher.begin();
        for (List<Block> l : rows) {
            for (Block b : l) {
                if (!b.getDestroyed()) {
                    batcher.draw(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
                }
            }
        }
        batcher.end();*/
    }

    public void stop() {
        for (List<Block> l : rows) {
            for (Block b : l) {
                b.stop();
            }
        }
    }

    public void restart() {
        speedRow = -20;
        maxTitan = 0;
        lastTimeSpeed = TimeUtils.nanoTime();
        restartType();
        restartRow();
    }

    public void pause() {
        int i = 0, j = 0;
        for (List<Block> l : rows) {
            for (Block b : l) {
                rowsPause.get(i).get(j).setHeight(b.getHeight());
                rowsPause.get(i).get(j).setWidth(b.getWidth());
                rowsPause.get(i).get(j).setPosition(b.getX(), b.getY());
                rowsPause.get(i).get(j).setVelocity(b.getVelocity().x, b.getVelocity().y);
                b.setVelocity(0, 0);
                j++;
            }
            j = 0;
            i++;
        }
    }

    public void resume() {
        int i = 0, j = 0;
        for (List<Block> l : rowsPause) {
            for (Block b : l) {
                rows.get(i).get(j).setHeight(b.getHeight());
                rows.get(i).get(j).setWidth(b.getWidth());
                rows.get(i).get(j).setPosition(b.getX(), b.getY());
                rows.get(i).get(j).setVelocity(b.getVelocity().x, b.getVelocity().y);
                j++;
            }
            j = 0;
            i++;
        }
    }


}
