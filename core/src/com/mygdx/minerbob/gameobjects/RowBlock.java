package com.mygdx.minerbob.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.minerbob.gameobjects.typeblock.ClayBlock;
import com.mygdx.minerbob.gameobjects.typeblock.DiamondBlock;
import com.mygdx.minerbob.gameobjects.typeblock.EarthBlock;
import com.mygdx.minerbob.gameobjects.typeblock.GoldBlock;
import com.mygdx.minerbob.gameobjects.typeblock.GrassBlock;
import com.mygdx.minerbob.gameobjects.typeblock.ITypeBlock;
import com.mygdx.minerbob.gameobjects.typeblock.StoneBlock;
import com.mygdx.minerbob.gameobjects.typeblock.DeadBlock;
import com.mygdx.minerbob.gameworld.GameWorld;


/**
 * Created by Алексей on 11.09.2017.
 */

public class RowBlock {
    private Array<Array<Block>> rows;

    private GameWorld gameWorld;

    public float heightBlock, widthBlock;

    private long lastTimeSpeed;

    //Animation
    private int countEarthAnim = 0;

    private ITypeBlock earthBlock, clayBlock, stoneBlock, diamondBlock, titanBlock, goldBlock, grassBlock;
    private Array<ITypeBlock> typeBlocks;

    //private ArrayList<Boolean> bools = new ArrayList<Boolean>();
    private Array<Boolean> bools = new Array<Boolean>();

    private int minTitan = 0;
    private int maxTitan = 1;
    private int countTitan = 0;

    private float speedRow = -20;
    private float currentSpeed = 0;

    private float earthLevel = 52; //52
    private float clayLevel = 62; //62
    private float stoneLevel = 110; //110
    private float diamondLevel = 130; //130

    private int scoreCount = 0;
    private int maxspeed = 55;


    public RowBlock(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        heightBlock = gameWorld.WIDTH / 5 * 180 / 200 - 2;
        widthBlock = gameWorld.WIDTH / 5;
        lastTimeSpeed = TimeUtils.nanoTime();
        initObjects();
    }

    public Block getBlock(int i, int j) {
        return rows.get(i).get(j);
    }

    private void initObjects() {
        goldBlock = new GoldBlock(gameWorld.assetLoader, earthLevel);
        earthBlock = new EarthBlock(gameWorld.assetLoader, earthLevel);
        clayBlock = new ClayBlock(gameWorld.assetLoader, clayLevel);
        stoneBlock = new StoneBlock(gameWorld.assetLoader, stoneLevel);
        diamondBlock = new DiamondBlock(gameWorld.assetLoader, diamondLevel);
        grassBlock = new GrassBlock(gameWorld.assetLoader, earthLevel);
        titanBlock = new DeadBlock(gameWorld.assetLoader,5000);
        typeBlocks = new Array<ITypeBlock>();

        typeBlocks.add(earthBlock);
        typeBlocks.add(clayBlock);
        typeBlocks.add(stoneBlock);
        typeBlocks.add(diamondBlock);


        rows = new Array<Array<Block>>();

        for(int i = 0; i < 4; i++) {
            rows.add(new Array<Block>());
            for (int j = 0; j < 5; j++) {
                rows.get(i).add(new Block(gameWorld));
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
        grassBlock.setLevel(earthLevel);
        titanBlock.setLevel(5000);
    }

    private void restartRow() {
        countTitan = 0;
        float height = gameWorld.HEIGHT / 5 + heightBlock;

        for (int i = 0; i < 5; i++) {
            rows.get(0).get(i).restart(gameWorld.WIDTH / 5 * i, gameWorld.HEIGHT - heightBlock, widthBlock, heightBlock, -1, grassBlock, i);
        }

        for (int i = 1; i < 4; i++) {
            generateRow(i, gameWorld.WIDTH / 5, 2 * gameWorld.HEIGHT - heightBlock + (i) * height, -1);
        }
    }

    private void setVelocity(float velocity) {
        for (Array<Block> r : rows) {
            for (Block b : r) {
                b.setVelocity(0, velocity);
            }
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

        if (GameWorld.score / 50 > scoreCount) {
            j = rnd(0, 4);
            scoreCount = GameWorld.score / 50;
            bools.set(j, true);
            goldBlock.setLevel(earthBlock.getLevel());
            rows.get(index).get(j).restart(x * j, y, widthBlock, heightBlock, speed, goldBlock, j);
        }

        j = rnd(0, 1);

        if (j == 0) {
            j = rnd(0, 4);
            if (!bools.get(j)) {
                rows.get(index).get(j).restart(x * j, y, widthBlock, heightBlock, speed, earthBlock, j);
                bools.set(j, true);
            }
        } else {
            j = rnd(0, 4);
            if (!bools.get(j)) {
                rows.get(index).get(j).restart(x * j, y, widthBlock, heightBlock, speed, clayBlock, j);
                bools.set(j, true);
            }
        }

        for (int i = 0; i < maxCount; i++) {
            j = rnd(0, 4);
            if (!bools.get(j)) {
                bools.set(j, true);
                rows.get(index).get(j).restart(x * j, y, widthBlock, heightBlock, speed, titanBlock, j);
            }
        }

        ITypeBlock typeBlock;
        for(int i = 0; i < 5; i++){
            if (!bools.get(i)) {
                typeBlock = getRandomType();
                rows.get(index).get(i).restart(x * i, y, widthBlock, heightBlock, speed, typeBlock, i);
            }
        }

    }

    private ITypeBlock getRandomType() {
        ITypeBlock type = typeBlocks.get(rnd(0, typeBlocks.size - 1));
        return type;
    }

    public void update(float delta) {

        if (!gameWorld.getActor().getAlive())
            return;

        boolean isCollised = false;
        int i = 0;

        // изменение скорости
        float velocity = rows.get(0).get(0).getVelocity().y;
        velocity += velocity / 24.0f;

        long timeNow = TimeUtils.nanoTime();

        for (Array<Block> l : rows) {
            countTitan = 0;

            //b.getVelocity().y + (b.getVelocity().y / 24.0f
            for (Block b : l) {
                if (timeNow - lastTimeSpeed > 3000000000L) {
                    if (b.getVelocity().y > - maxspeed) {
                        b.setVelocity(0, velocity);
                    }
                }
                b.update(delta);

                if (!isCollised) {
                    if (b.isCollised(delta)) {
                        isCollised = true;
                    }
                }
            }
            if (l.get(0).getStaticY() + heightBlock < 0) {
                if (i == 0)
                    generateRow(i, l.get(1).getX(), rows.get(3).get(0).getStaticY() + gameWorld.HEIGHT / 5 + heightBlock, l.get(0).getVelocity().y);
                else
                    generateRow(i, l.get(1).getX(), rows.get(i - 1).get(0).getStaticY() + gameWorld.HEIGHT / 5 + heightBlock, l.get(0).getVelocity().y);            }
            i++;
        }

         if (timeNow - lastTimeSpeed > 3000000000L) {
             lastTimeSpeed = TimeUtils.nanoTime();
             if (rows.get(0).get(0).getVelocity().y > - maxspeed) {
                 for (ITypeBlock type : typeBlocks)
                    type.setLevel(type.getLevel() - type.getLevel() / 24.0f);
             }
             maxTitan++;

             if(maxTitan >= 2)
                maxTitan = 2;
        }
        setStandartSpeed();
    }

    private void setStandartSpeed() {

        if (gameWorld.isStart && rows.get(0).get(0).getVelocity().y <= -1 && rows.get(0).get(0).getVelocity().y >= -2 && gameWorld.isKickedFirst) {
            setVelocity(-gameWorld.HEIGHT);
            gameWorld.getField().setVelocity(0, -gameWorld.HEIGHT);
            gameWorld.getActor().setVelocity(0, -100);
        }
        if (gameWorld.isCollisedSecond && rows.get(0).get(0).getVelocity().y == -gameWorld.HEIGHT) {
            setVelocity(speedRow);
            gameWorld.getField().setVelocity(0, -10);
        }
    }

    public void draw(SpriteBatch batcher) {
        for (Array<Block> l : rows) {
            for (Block b : l) {
                batcher.draw(b.getTexture(), b.getX(), b.getStaticY() - 2,
                        b.getWidth(), heightBlock + 2);
            }
        }
    }

    public void stop() {
        if (rows.get(0).get(0).getVelocity().y != 0) {
            currentSpeed = rows.get(0).get(0).getVelocity().y;
        }
        for (Array<Block> l : rows) {
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
        scoreCount = 0;
    }

    public int identifyRow(float x, float y) {
        float tx, ty;
        float acs = heightBlock / 2;
        int buf = -1;
        for(int i = 0; i < 4; i++) {
            tx = rows.get(i).get(0).getX();
            ty = rows.get(i).get(0).getY();
            if((x < tx + acs && x > tx - acs) && (y < ty + acs && y > ty - acs)) {
                buf = i;
                break;
            }
        }
        return buf;
    }

    private void restoreType() {
        earthBlock.setLevel(typeBlocks.get(0).getLevel() + typeBlocks.get(0).getLevel() / 8.0f);
        clayBlock.setLevel(typeBlocks.get(1).getLevel() + typeBlocks.get(1).getLevel() / 8.0f);
        stoneBlock.setLevel(typeBlocks.get(2).getLevel() + typeBlocks.get(2).getLevel() / 8.0f);
        diamondBlock.setLevel(typeBlocks.get(3).getLevel() + typeBlocks.get(3).getLevel() / 8.0f);
        titanBlock.setLevel(5000);
    }

    public void setRowEarth(int i) {
        for(int j = 0; j < 5; j++) {
            rows.get(i).get(j).setType(typeBlocks.get(0));
        }
    }

    public void restore() {
        if(currentSpeed - currentSpeed / 8.0f < speedRow) {
            currentSpeed -= currentSpeed / 8.0f;
            restoreType();
        }

        maxTitan = 0;
        lastTimeSpeed = TimeUtils.nanoTime();
        setVelocity(currentSpeed);
        scoreCount = 0;
    }
}
