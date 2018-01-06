package com.mygdx.minerbob.helpers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.mygdx.minerbob.screen.GameScreen;
import com.mygdx.minerbob.ui.ShopHelpers.Item;

/**
 * Created by Алексей on 24.09.2017.
 */

public class Money {
    public static int money;
    private static AssetLoader assetLoader;

    public Money(AssetLoader assetLoader) {
        this.assetLoader = assetLoader;
    }

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

    public static void draw(SpriteBatch batcher, float x, float y, int money) {
        float width = TextSize.getHeight(assetLoader.font, money + "");
        batcher.draw(assetLoader.moneyTexture, x, y + 1f, width, width);
        assetLoader.font.draw(batcher, "" + money, x + width + 1f, y);
    }
}
