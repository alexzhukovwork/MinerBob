package com.mygdx.minerbob.helpers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.minerbob.screen.GameScreen;
import com.mygdx.minerbob.ui.ShopHelpers.Item;

/**
 * Created by Алексей on 24.09.2017.
 */

public class Money {
    public static int money;

    public static boolean buy(Item item) {
        boolean result = false;
        if (money >= item.getCost()) {
            money -= item.getCost();
            item.setBought(true);
            result = true;
            saveMoney();
        }

        return result;
    }

    public static boolean hasEnough(int cost) {
        return money - cost >= 0;
    }

    private static void saveMoney() {
        AssetLoader.prefs.putInteger("money", money);
        AssetLoader.prefs.flush();
    }

    public static void add(int money) {
        Money.money += money;
        saveMoney();
    }

    public static void draw(ShapeRenderer renderer, SpriteBatch batcher, float x, float y, int money) {
        float radius = 0;
        batcher.begin();
        AssetLoader.font.draw(batcher, "" + money, x, y);
        batcher.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(1, 1, 0, 1);
        radius = TextSize.getHeight(AssetLoader.font, money + "") / 2;
        renderer.circle(x + TextSize.getWidth(AssetLoader.font, money + "") + radius + radius,
                y + radius,
                radius);
        renderer.end();
    }
}
