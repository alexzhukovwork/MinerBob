package com.mygdx.minerbob.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.Money;
import com.mygdx.minerbob.screen.GameScreen;

/**
 * Created by Алексей on 18.09.2017.
 */

public class MenuForm {
    Rectangle boundsPlay;
    Rectangle boundsShop;
    Rectangle boundsSound;

    public MenuForm() {
        boundsPlay = new Rectangle(GameScreen.WIDTH / 2 - GameScreen.WIDTH / 6, GameScreen.HEIGHT / 2 - GameScreen.HEIGHT / 8, GameScreen.WIDTH / 3, GameScreen.HEIGHT / 6);
        boundsSound = new Rectangle(GameScreen.WIDTH - GameScreen.WIDTH / 6 * 2 - 5, 0, GameScreen.WIDTH / 6, GameScreen.HEIGHT / 12);
        boundsShop = new Rectangle(GameScreen.WIDTH - GameScreen.WIDTH / 6, 0, GameScreen.WIDTH / 6, GameScreen.HEIGHT / 12);
    }

    public void draw(ShapeRenderer shaper, SpriteBatch batcher) {
        batcher.begin();
        AssetLoader.font.draw(batcher, "RECORD " + AssetLoader.prefs.getInteger("highScore") + "", GameScreen.WIDTH / 10, 0);
        batcher.end();
        Money.draw(shaper, batcher, GameScreen.WIDTH / 10, GameScreen.HEIGHT - GameScreen.HEIGHT / 10, Money.money);
        shaper.begin(ShapeRenderer.ShapeType.Filled);
        shaper.setColor(1, 1, 1, 1);
        shaper.rect(boundsSound.x, boundsSound.y, boundsSound.width, boundsSound.height);
        shaper.rect(boundsShop.x, boundsShop.y, boundsShop.width, boundsShop.height);
        shaper.rect(boundsPlay.x, boundsPlay.y, boundsPlay.width, boundsPlay.height);
        shaper.end();
    }

    public boolean isClickedPlay(float x, float y) {
        return boundsPlay.contains(x, y);
    }

    public boolean isClickedShop(float x, float y) {
        return boundsShop.contains(x, y);
    }

    public boolean isClickedSound(float x, float y) {
        return boundsSound.contains(x, y);
    }
}
