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
        float x = gameWorld.WIDTH / 2 - gameWorld.dialogWidth / 2;
        float y = gameWorld.HEIGHT / 2 - gameWorld.dialogHeight / 2;
        boundRectangle = new Rectangle(x, y, gameWorld.dialogWidth, gameWorld.dialogHeight);
        boundOkey = new Rectangle(boundRectangle.x + boundRectangle.width / 2 - this.gameWorld.buttonDialogWidth / 2,
                boundRectangle.y + boundRectangle.height - boundRectangle.height / 5,
                this.gameWorld.buttonDialogWidth, this.gameWorld.buttonDialogHeight);
    }

    public void draw(SpriteBatch batcher) {
        batcher.draw(gameWorld.assetLoader.boxTexture, boundRectangle.x, boundRectangle.y, boundRectangle.width, boundRectangle.height);
        batcher.draw(gameWorld.assetLoader.okMenuTexture, boundOkey.x, boundOkey.y, boundOkey.width, boundOkey.height);
        String dayNum = "DAY " + AssetLoader.prefs.getInteger("countBonus");
        gameWorld.assetLoader.font.draw(batcher, dayNum, boundRectangle.x + boundRectangle.width / 12, boundRectangle.y + boundRectangle.height / 15);
        float width = TextSize.getWidth(gameWorld.assetLoader.font, AssetLoader.prefs.getInteger("countBonus") * 10 + "");
        float height = TextSize.getHeight(gameWorld.assetLoader.font, AssetLoader.prefs.getInteger("countBonus") * 10 + "");
        Money.draw(batcher, boundRectangle.x + boundRectangle.width - boundRectangle.width / 12 - width - height - 1f,
                boundRectangle.y + boundRectangle.height / 15, AssetLoader.prefs.getInteger("countBonus") * 10);
    }

    public boolean isClicked(float x, float y) {
        return boundOkey.contains(x, y);
    }
}
