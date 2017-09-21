package com.mygdx.game.ui.ShopHelpers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.screen.GameScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алексей on 20.09.2017.
 */

public class Page {
    private ArrayList<Item> items;
    private final int amountRow = 3, amountCol = 2;

    public Page(int amount, int number) {
        items = new ArrayList<Item>();
        float tx = GameScreen.WIDTH / 7, ty = GameScreen.HEIGHT / 6;
        for(int i = 0; i < amountRow; i++) {
            for (int j = 0; j < amountCol && items.size() < amount; j++) {
                items.add(new Item(tx + GameScreen.WIDTH * number, ty, GameScreen.WIDTH / 3, GameScreen.HEIGHT / 5, 1000, null));
                tx += GameScreen.WIDTH / 3 + 5;
            }
            ty += GameScreen.HEIGHT / 5 + 5;
            tx = GameScreen.WIDTH / 7;
        }
    }

    public void draw(ShapeRenderer renderer, SpriteBatch batcher) {
        for(Item i : items)
            i.draw(renderer, batcher);
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
}
