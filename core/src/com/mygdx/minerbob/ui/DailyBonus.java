package com.mygdx.minerbob.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.Money;
import com.mygdx.minerbob.helpers.TextSize;
import com.mygdx.minerbob.screen.GameScreen;

/**
 * Created by Алексей on 03.10.2017.
 */

public class DailyBonus {
    private Rectangle boundRectangle;
    private Rectangle boundOkey;
    private GameWorld gameWorld;

    public DailyBonus(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        boundRectangle = new Rectangle(GameScreen.WIDTH / 5, GameScreen.HEIGHT / 4, GameScreen.WIDTH / 5 * 3, GameScreen.WIDTH / 5 * 3);
        boundOkey = new Rectangle(GameScreen.WIDTH / 5 + GameScreen.WIDTH / 10 * 3 - GameScreen.WIDTH / 10,
                GameScreen.HEIGHT / 4 + GameScreen.WIDTH / 5 * 3 - GameScreen.HEIGHT / 10, GameScreen.WIDTH / 5, GameScreen.HEIGHT / 15);
    }

    public void draw(ShapeRenderer renderer, SpriteBatch batcher) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(0.75f, 0.75f, 0.75f, 1);
        renderer.rect(boundRectangle.x, boundRectangle.y, boundRectangle.width, boundRectangle.height);
        renderer.setColor(0, 0, 0, 1);
        renderer.rect(boundOkey.x, boundOkey.y, boundOkey.width, boundOkey.height);
        renderer.end();
        batcher.begin();
        String dayNum = "DAY " + AssetLoader.prefs.getInteger("countBonus");
        gameWorld.assetLoader.font.draw(batcher, dayNum,
                boundRectangle.x + boundRectangle.width / 2 - TextSize.getWidth(gameWorld.assetLoader.font, dayNum) / 2,
                boundRectangle.y + 3);
        batcher.end();
        Money.draw(renderer, batcher, boundRectangle.x + boundRectangle.width / 2 - TextSize.getWidth(gameWorld.assetLoader.font, AssetLoader.prefs.getInteger("countBonus") * 10 + "") / 2,
                boundRectangle.y + boundRectangle.height / 2 - TextSize.getHeight(gameWorld.assetLoader.font, AssetLoader.prefs.getInteger("countBonus") * 10 + ""),
                AssetLoader.prefs.getInteger("countBonus") * 10);
    }

    public boolean isClicked(float x, float y) {
        return boundOkey.contains(x, y);
    }
}
