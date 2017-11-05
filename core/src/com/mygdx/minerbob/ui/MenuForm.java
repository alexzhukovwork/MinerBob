package com.mygdx.minerbob.ui;

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
    private GameWorld gameWorld;

    TextureRegion playTexture, shopTexture, soundTexture;
    public MenuForm(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        playTexture = this.gameWorld.assetLoader.buttonPlay;
        shopTexture = this.gameWorld.assetLoader.buttonShop;
        boundsPlay = new Rectangle(gameWorld.WIDTH / 2 - gameWorld.WIDTH / 6, gameWorld.HEIGHT / 2 - gameWorld.WIDTH / 6,
                gameWorld.WIDTH / 3, gameWorld.WIDTH / 3);
        boundsSound = new Rectangle(gameWorld.WIDTH - gameWorld.buttonSize * 2 - gameWorld.MARGIN * 2f, gameWorld.MARGIN, gameWorld.buttonSize, gameWorld.buttonSize);
        boundsShop = new Rectangle(gameWorld.WIDTH - gameWorld.buttonSize - gameWorld.MARGIN, gameWorld.MARGIN, gameWorld.buttonSize, gameWorld.buttonSize);
    }

    private void roundedRectangle(ShapeRenderer shaper, float x, float y, float width, float height, float radius) {
        shaper.begin(ShapeRenderer.ShapeType.Filled);
        //shaper.setColor(0.204f, 0.255f, 0.298f, 0.2f);
        shaper.setColor(0.153f, 0.486f, 0.533f, 1f); //39, 124, 136

        // Width rectangle
        shaper.rect(x, y + radius, width, height - 2 * radius);

        // Height rectangle
        shaper.rect(x + radius, y, width - 2 * radius, height);

        // Bottom-left circle
        shaper.circle(x + radius, y + height - radius, radius, 1000);

        // Top-left circle
        shaper.circle(x + radius, y + radius, radius, 1000);

        // Top-right circle
        shaper.circle(x + width - radius, y + radius, radius, 1000);

        // Bottom-right circle
        shaper.circle(x + width - radius, y + height - radius, radius, 1000);

        shaper.end();
    }

    public void draw(ShapeRenderer shaper, SpriteBatch batcher) {
        float textWidth = TextSize.getWidth(gameWorld.assetLoader.font, AssetLoader.prefs.getInteger("highScore") + "");
        float textHeight = TextSize.getHeight(gameWorld.assetLoader.font, AssetLoader.prefs.getInteger("highScore") + "");
        roundedRectangle(shaper, gameWorld.buttonSize / 2 + gameWorld.MARGIN * 2f, gameWorld.MARGIN, textWidth + 4, gameWorld.buttonSize,
                gameWorld.buttonSize * 21f / 100f); //radius = 2.5f
        batcher.begin();
        gameWorld.assetLoader.font.draw(batcher, AssetLoader.prefs.getInteger("highScore") + "",gameWorld.buttonSize / 2 + gameWorld.MARGIN *3f,
                gameWorld.buttonSize / 2 - textHeight / 2 + gameWorld.MARGIN);
        batcher.draw(gameWorld.assetLoader.starTexture, gameWorld.MARGIN, gameWorld.buttonSize / 2 - gameWorld.buttonSize / 4 + gameWorld.MARGIN,
                gameWorld.buttonSize / 2, gameWorld.buttonSize / 2);
        //gameWorld.assetLoader.font.draw(batcher, "RECORD " + AssetLoader.prefs.getInteger("highScore") + "", gameWorld.WIDTH / 10, 0);
        batcher.end();
        Money.draw(shaper, batcher, gameWorld.WIDTH / 10, gameWorld.HEIGHT - gameWorld.HEIGHT / 10, Money.money);
   /*     shaper.begin(ShapeRenderer.ShapeType.Filled);
        shaper.setColor(1, 1, 1, 1);1
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
