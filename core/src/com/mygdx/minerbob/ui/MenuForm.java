package com.mygdx.minerbob.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.minerbob.gameworld.GameWorld;
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
    private GameWorld gameWorld;

    TextureRegion playTexture, shopTexture, soundTexture;
    public MenuForm(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        playTexture = this.gameWorld.assetLoader.buttonPlay;
        shopTexture = this.gameWorld.assetLoader.buttonShop;
        boundsPlay = new Rectangle(gameWorld.WIDTH / 2 - gameWorld.WIDTH / 6, gameWorld.HEIGHT / 2 - gameWorld.WIDTH / 6, gameWorld.WIDTH / 3, gameWorld.WIDTH / 3);
        boundsSound = new Rectangle(gameWorld.WIDTH - gameWorld.buttonSize * 2 - 5, 0, gameWorld.buttonSize, gameWorld.buttonSize);
        boundsShop = new Rectangle(gameWorld.WIDTH - gameWorld.buttonSize, 0, gameWorld.buttonSize, gameWorld.buttonSize);
    }

    public void draw(ShapeRenderer shaper, SpriteBatch batcher) {
        batcher.begin();
        gameWorld.assetLoader.font.draw(batcher, "RECORD " + AssetLoader.prefs.getInteger("highScore") + "", gameWorld.WIDTH / 10, 0);
        batcher.end();
        Money.draw(shaper, batcher, gameWorld.WIDTH / 10, gameWorld.HEIGHT - gameWorld.HEIGHT / 10, Money.money);
   /*     shaper.begin(ShapeRenderer.ShapeType.Filled);
        shaper.setColor(1, 1, 1, 1);
      //  shaper.rect(boundsSound.x, boundsSound.y, boundsSound.width, boundsSound.height);
        shaper.end();
     */   batcher.begin();
        batcher.draw(gameWorld.assetLoader.buttonSound, boundsSound.x, boundsSound.y, boundsSound.width, boundsSound.height);
        batcher.draw(playTexture, boundsPlay.x, boundsPlay.y, boundsPlay.width, boundsPlay.height);
        batcher.draw(shopTexture, boundsShop.x, boundsShop.y, boundsShop.width, boundsShop.height);
        batcher.end();
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
