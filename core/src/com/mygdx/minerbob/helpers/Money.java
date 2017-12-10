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

    public static void draw(ShapeRenderer renderer, SpriteBatch batcher, float x, float y, int money) {
        float width = 0;
        width = TextSize.getHeight(assetLoader.font, money + "");
        batcher.begin();
        assetLoader.font.draw(batcher, "" + money, x, y);
        batcher.draw(assetLoader.moneyTexture, x + TextSize.getWidth(assetLoader.font, money + "") + 1f,
                y + 1f, width, width);
        batcher.end();
      //  renderer.begin(ShapeRenderer.ShapeType.Filled);
       // renderer.setColor(1, 1, 0, 1);

        //renderer.circle(x + TextSize.getWidth(assetLoader.font, money + "") + width + width,
            //    y + width + 1f,
            //    width, 100);

        //renderer.end();
    }
}
