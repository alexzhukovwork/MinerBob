package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.gameworld.GameWorld;
import com.mygdx.game.helpers.AssetLoader;
import com.mygdx.game.screen.GameScreen;

/**
 * Created by Алексей on 18.09.2017.
 */

public class MenuForm {
    Rectangle boundsPlay;
    Rectangle boundsShop;
    Rectangle boundsSound;

    public MenuForm() {
        boundsPlay = new Rectangle(GameScreen.WIDTH /  5 * 2, GameScreen.HEIGHT / 5 * 2, GameScreen.WIDTH / 5, GameScreen.HEIGHT / 10);
        boundsShop = new Rectangle(GameScreen.WIDTH /  10 * 9, 0, GameScreen.WIDTH / 10, GameScreen.WIDTH / 10);
        boundsSound = new Rectangle(GameScreen.WIDTH /  10 * 7 + GameScreen.WIDTH / 20, 0, GameScreen.WIDTH / 10, GameScreen.WIDTH / 10);
    }

    public void draw(ShapeRenderer shaper, SpriteBatch batcher) {
        batcher.begin();
        AssetLoader.font.draw(batcher, "Record", GameScreen.WIDTH / 10, GameScreen.HEIGHT / 10);
        AssetLoader.font.draw(batcher, AssetLoader.prefs.getInteger("highScore") + "", GameScreen.WIDTH / 10, GameScreen.HEIGHT / 10 + GameScreen.HEIGHT / 10);
        batcher.end();
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
