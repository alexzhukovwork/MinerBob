package com.mygdx.minerbob.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.Money;
import com.mygdx.minerbob.helpers.TextSize;

/**
 * Created by Алексей on 18.09.2017.
 */

public class MenuForm {
    Rectangle boundsPlay;
    Rectangle boundsShop;
    Rectangle boundsSound;
    Rectangle boundStudy;
    Rectangle boundScore;
    Rectangle boundMoney;

    private float scale;
    private boolean up;
    private GameWorld gameWorld;

    TextureRegion playTexture, shopTexture, soundTexture;

    public MenuForm(GameWorld gameWorld) {
        scale = 0.25f;
        up = false;
        this.gameWorld = gameWorld;
        playTexture = this.gameWorld.assetLoader.buttonPlay;
        shopTexture = this.gameWorld.assetLoader.buttonShop;
        soundTexture = this.gameWorld.assetLoader.buttonSound;
        boundsPlay = new Rectangle(gameWorld.WIDTH / 2 - gameWorld.WIDTH / 6, gameWorld.HEIGHT / 2 - gameWorld.WIDTH / 6,
                gameWorld.WIDTH / 3, gameWorld.WIDTH / 3);
        boundsSound = new Rectangle(gameWorld.WIDTH - gameWorld.buttonSize * 2 - gameWorld.MARGIN * 2f, gameWorld.MARGIN, gameWorld.buttonSize, gameWorld.buttonSize);
        boundsShop = new Rectangle(gameWorld.WIDTH - gameWorld.buttonSize - gameWorld.MARGIN, gameWorld.MARGIN, gameWorld.buttonSize, gameWorld.buttonSize);
        boundStudy = new Rectangle(gameWorld.WIDTH - gameWorld.buttonSize - gameWorld.MARGIN, gameWorld.MARGIN * 2 + gameWorld.buttonSize,
                gameWorld.buttonSize, gameWorld.buttonSize);


        // исправить тута
        // (вроде все норм) (с) Вовчик
        boundScore = new Rectangle(gameWorld.buttonSize / 2 + gameWorld.MARGIN * 2f, gameWorld.MARGIN, gameWorld.buttonSize * 2, gameWorld.buttonSize);

        //тута исправлять не над
        boundMoney = new Rectangle(gameWorld.WIDTH / 10 - 3f, gameWorld.HEIGHT - gameWorld.HEIGHT / 7 - gameWorld.MARGIN,
                TextSize.getWidth(this.gameWorld.assetLoader.font, Money.money + "") + gameWorld.MARGIN + 1,
                TextSize.getHeight(this.gameWorld.assetLoader.font, Money.money + ""));
    }

    public void draw(ShapeRenderer shaper, SpriteBatch batcher) {
        float textWidth = TextSize.getWidth(gameWorld.assetLoader.font, AssetLoader.prefs.getInteger("highScore") + "");
        float textHeight = TextSize.getHeight(gameWorld.assetLoader.font, AssetLoader.prefs.getInteger("highScore") + "");
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shaper.begin(ShapeRenderer.ShapeType.Filled);
        if (up) {
            scale += 0.05;
            if (scale > 1)
                up = false;
        } else {
            scale -= 0.05;
            if (scale < 0.1)
                up = true;
        }


        gameWorld.roundedRectangle(shaper, gameWorld.buttonSize / 2 + gameWorld.MARGIN * 2f - scale / 2,
                gameWorld.MARGIN - scale / 2,
                textWidth + 4 + scale, gameWorld.buttonSize + scale,
                gameWorld.buttonSize * 0.21f, new Color(0.153f - scale / 10, 0.486f - scale / 10, 0.533f - scale / 10, 1)); //radius = 2.5f

        shaper.end();
        batcher.begin();
        gameWorld.assetLoader.font.getData().setScale(0.1f, -0.1f);
        gameWorld.assetLoader.font.draw(batcher, AssetLoader.prefs.getInteger("highScore") + "",gameWorld.buttonSize / 2 + gameWorld.MARGIN *3f,
                gameWorld.buttonSize / 2 - textHeight / 2 + gameWorld.MARGIN - 1f);
        gameWorld.assetLoader.font.getData().setScale(0.1f, -0.1f);
        batcher.draw(gameWorld.assetLoader.starTexture, gameWorld.MARGIN, gameWorld.buttonSize / 2 - gameWorld.buttonSize / 4 + gameWorld.MARGIN,
                gameWorld.buttonSize / 2, gameWorld.buttonSize / 2);
        batcher.draw(gameWorld.assetLoader.buttonStudy, boundStudy.x, boundStudy.y, boundStudy.width, boundStudy.height);
        //gameWorld.assetLoader.font.draw(batcher, "RECORD " + AssetLoader.prefs.getInteger("highScore") + "", gameWorld.WIDTH / 10, 0);

        Money.draw(batcher, gameWorld.WIDTH / 10 - 3f, gameWorld.HEIGHT - gameWorld.HEIGHT / 7 - gameWorld.MARGIN, Money.money);

        if(gameWorld.isSound)
            soundTexture = gameWorld.assetLoader.buttonSound;
        else
            soundTexture = gameWorld.assetLoader.buttonNoneSound;

        batcher.draw(soundTexture, boundsSound.x, boundsSound.y, boundsSound.width, boundsSound.height);
        batcher.draw(playTexture, boundsPlay.x, boundsPlay.y, boundsPlay.width, boundsPlay.height);
        batcher.draw(shopTexture, boundsShop.x, boundsShop.y, boundsShop.width, boundsShop.height);
        batcher.end();
    }

    public boolean isClickedScore(float x, float y) {
        return boundScore.contains(x, y);
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

    public boolean isClickedMoney(float x, float y) {
        return boundMoney.contains(x, y);
    }
}
