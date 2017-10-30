package com.mygdx.minerbob.ui.ShopHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.screen.GameScreen;

import java.util.ArrayList;

/**
 * Created by Алексей on 20.09.2017.
 */

public class Page {
    private ArrayList<Item> items;
    private final int amountRow = 3, amountCol = 2;

    public Page(GameWorld gameWorld, int amount, int number) {
        items = new ArrayList<Item>();
        int index = 0;
        Item item;
        float tx = GameScreen.WIDTH / 7, ty = GameScreen.HEIGHT / 6;
        for(int i = 0; i < amountRow; i++) {
            for (int j = 0; j < amountCol && items.size() < amount; j++) {
                index = number * 6 + j + 2 * (i);
                item = new Item(gameWorld, index, tx + GameScreen.WIDTH * number, ty, GameScreen.WIDTH / 3, GameScreen.HEIGHT / 5, gameWorld.assetLoader.textures.get(index).cost,
                        gameWorld.assetLoader.textures.get(index).textureFall, gameWorld.assetLoader.textures.get(index).animation);
                item.setSelected(AssetLoader.prefs.getBoolean("selected" + index));
                item.setBought(AssetLoader.prefs.getBoolean("bought" + index));
                items.add(item);
                tx += GameScreen.WIDTH / 3 + 5;
            }
            ty += GameScreen.HEIGHT / 5 + 5;
            tx = GameScreen.WIDTH / 7;
        }
    }

    public void draw(ShapeRenderer renderer, SpriteBatch batcher, float runTime) {
        for(Item i : items)
            i.draw(renderer, batcher, runTime);
    }

    public void setVelocity(float x, float y) {
        for (Item item : items) {
            item.setVelocity(x, y);
        }
    }

    public void setAcceleration(float x, float y) {
        for (Item item : items) {
            item.setAcceleration(x, y);
        }
    }

    public void update(float delta) {
        for (Item item : items) {
            item.update(delta);
        }
    }

    public boolean isMove() {
        return items.get(0).isMove();
    }

    public Item isClicked(float x, float y) {
        for (Item i : items) {
            if (i.isClicked(x, y)) {
                return i;
            }
        }
        return null;
    }

    public void setSelected(boolean selected) {
        for (Item i : items) {
            i.setSelected(selected);
        }
    }
}
