package com.mygdx.minerbob.ui.ShopHelpers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.screen.GameScreen;


/**
 * Created by Алексей on 20.09.2017.
 */

public class Page {
    private Array<Item> items;
    private final int amountRow = 3, amountCol = 2;

    public Page(GameWorld gameWorld, int amount, int number) {
        items = new Array<Item>();
        int index = 0;
        Item item;
        float tx = gameWorld.WIDTH / 7, ty = gameWorld.HEIGHT / 6;
        for(int i = 0; i < amountRow; i++) {
            for (int j = 0; j < amountCol && items.size < amount; j++) {
                index = number * 6 + j + 2 * (i);
                item = new Item(gameWorld, index, tx + gameWorld.WIDTH * number, ty, gameWorld.WIDTH / 3, gameWorld.HEIGHT / 5, gameWorld.assetLoader.textures.get(index).cost,
                        gameWorld.assetLoader.textures.get(index).textureFall, gameWorld.assetLoader.textures.get(index).animation);
                item.setSelected(AssetLoader.prefs.getBoolean("selected" + index));
                item.setBought(AssetLoader.prefs.getBoolean("bought" + index));
                items.add(item);
                tx += gameWorld.WIDTH / 3 + 5;
            }
            ty += gameWorld.HEIGHT / 5 + 5;
            tx = gameWorld.WIDTH / 7;
        }
    }

    public void draw(ShapeRenderer renderer, SpriteBatch batcher, float runTime) {
        for (int i = 0; i < items.size; i++)
            items.get(i).draw(renderer, batcher, runTime);
    }

    public void setVelocity(float x, float y) {
        for (int i = 0; i < items.size; i++)
            items.get(i).setVelocity(x, y);
    }

    public void setAcceleration(float x, float y) {
        for (int i = 0; i < items.size; i++)
            items.get(i).setAcceleration(x, y);
    }

    public void update(float delta) {
        for (int i = 0; i < items.size; i++)
            items.get(i).update(delta);
    }

    public boolean isMove() {
        return items.get(0).isMove();
    }

    public Item isClicked(float x, float y) {
        for (int i = 0; i < items.size; i++)
            if (items.get(i).isClicked(x, y)) {
                return items.get(i);
            }

        return null;
    }

    public void setSelected(boolean selected) {
        for (int i = 0; i < items.size; i++)
            items.get(i).setSelected(selected);
    }
}
