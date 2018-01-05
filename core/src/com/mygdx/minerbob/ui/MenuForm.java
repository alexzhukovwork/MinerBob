package com.mygdx.minerbob.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.Money;
import com.mygdx.minerbob.helpers.TextSize;
import com.mygdx.minerbob.screen.GameScreen;

/**
 * Created by Алексей on 18.09.2017.
 */

public class MenuForm {
    Rectangle boundsPlay;
    Rectangle boundsShop;
    Rectangle boundsSound;
    Rectangle boundStudy;
    private GameWorld gameWorld;

    TextureRegion playTexture, shopTexture, soundTexture;

    public MenuForm(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        playTexture = this.gameWorld.assetLoader.buttonPlay;
        shopTexture = this.gameWorld.assetLoader.buttonShop;
        soundTexture = this.gameWorld.assetLoader.buttonSound;
        boundsPlay = new Rectangle(gameWorld.WIDTH / 2 - gameWorld.WIDTH / 6, gameWorld.HEIGHT / 2 - gameWorld.WIDTH / 6,
                gameWorld.WIDTH / 3, gameWorld.WIDTH / 3);
        boundsSound = new Rectangle(gameWorld.WIDTH - gameWorld.buttonSize * 2 - gameWorld.MARGIN * 2f, gameWorld.MARGIN, gameWorld.buttonSize, gameWorld.buttonSize);
        boundsShop = new Rectangle(gameWorld.WIDTH - gameWorld.buttonSize - gameWorld.MARGIN, gameWorld.MARGIN, gameWorld.buttonSize, gameWorld.buttonSize);
        boundStudy = new Rectangle(gameWorld.WIDTH - gameWorld.buttonSize - gameWorld.MARGIN, gameWorld.HEIGHT - gameWorld.MARGIN - gameWorld.buttonSize,
                gameWorld.buttonSize, gameWorld.buttonSize);
    }

    public void draw(ShapeRenderer shaper, SpriteBatch batcher) {
        float textWidth = TextSize.getWidth(gameWorld.assetLoader.font, AssetLoader.prefs.getInteger("highScore") + "");
        float textHeight = TextSize.getHeight(gameWorld.assetLoader.font, AssetLoader.prefs.getInteger("highScore") + "");
        shaper.begin(ShapeRenderer.ShapeType.Filled);
        gameWorld.roundedRectangle(shaper, gameWorld.buttonSize / 2 + gameWorld.MARGIN * 2f, gameWorld.MARGIN, textWidth + 4, gameWorld.buttonSize,
                gameWorld.buttonSize * 21f / 100f, new Color(0.153f, 0.486f, 0.533f, 1f)); //radius = 2.5f
        shaper.end();
        batcher.begin();
        gameWorld.assetLoader.font.draw(batcher, AssetLoader.prefs.getInteger("highScore") + "",gameWorld.buttonSize / 2 + gameWorld.MARGIN *3f,
                gameWorld.buttonSize / 2 - textHeight / 2 + gameWorld.MARGIN - 1f);
        batcher.draw(gameWorld.assetLoader.starTexture, gameWorld.MARGIN, gameWorld.buttonSize / 2 - gameWorld.buttonSize / 4 + gameWorld.MARGIN,
                gameWorld.buttonSize / 2, gameWorld.buttonSize / 2);
        batcher.draw(gameWorld.assetLoader.buttonStudy, boundStudy.x, boundStudy.y, boundStudy.width, boundStudy.height);
        //gameWorld.assetLoader.font.draw(batcher, "RECORD " + AssetLoader.prefs.getInteger("highScore") + "", gameWorld.WIDTH / 10, 0);
        batcher.end();
        batcher.begin();
        Money.draw(batcher, gameWorld.WIDTH / 10, gameWorld.HEIGHT - gameWorld.HEIGHT / 7, Money.money);
        batcher.end();
        if(gameWorld.isSound)
            soundTexture = gameWorld.assetLoader.buttonSound;
        else
            soundTexture = gameWorld.assetLoader.buttonNoneSound;
        batcher.begin();
        batcher.draw(soundTexture, boundsSound.x, boundsSound.y, boundsSound.width, boundsSound.height);
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

    public boolean isClickedStudy(float x, float y) {
        return boundStudy.contains(x, y);
    }
}
