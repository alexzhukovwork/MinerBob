package com.mygdx.minerbob.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.Money;
import com.mygdx.minerbob.helpers.TextSize;
import com.mygdx.minerbob.ui.ShopHelpers.Item;
import com.mygdx.minerbob.ui.ShopHelpers.Page;

/**
 * Created by Алексей on 18.09.2017.
 */

public class ShopForm {
    private Rectangle boundClose, boundNext, boundBack, boundVideo;
    private Rectangle boundForm, boundAccept, boundCancel;
    private int currentPage;
    private Array<Page> pages;
    private boolean isFormAccept;
    private Item item;
    private String textMessage;
    private boolean hasBought;
    private GameWorld gameWorld;


    public ShopForm(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        initPage();
        isFormAccept = false;
        hasBought = false;
        currentPage = 0;
        boundVideo = new Rectangle(gameWorld.MARGIN, gameWorld.MARGIN, gameWorld.buttonSize, gameWorld.buttonSize);
        boundClose = new Rectangle(gameWorld.WIDTH - gameWorld.buttonSize - gameWorld.MARGIN, gameWorld.MARGIN, gameWorld.buttonSize,
                gameWorld.buttonSize);
        boundBack = new Rectangle(gameWorld.MARGIN, gameWorld.HEIGHT - gameWorld.buttonSize - gameWorld.MARGIN, gameWorld.buttonSize,
                gameWorld.buttonSize);
        boundNext = new Rectangle(gameWorld.WIDTH - gameWorld.buttonSize - gameWorld.MARGIN, gameWorld.HEIGHT - gameWorld.buttonSize - gameWorld.MARGIN,
                gameWorld.buttonSize, gameWorld.buttonSize);

        float x = gameWorld.WIDTH / 6;
        float y = gameWorld.HEIGHT / 4;
        boundForm = new Rectangle(x, y, gameWorld.dialogSize, gameWorld.dialogSize);
        boundCancel = new Rectangle(x + gameWorld.dialogSize - gameWorld.buttonDialogWidth - gameWorld.dialogSize / 9,
                y + gameWorld.dialogSize / 10 * 7, gameWorld.buttonDialogWidth, gameWorld.buttonSize);
        boundAccept = new Rectangle(x + gameWorld.dialogSize / 9, y + gameWorld.dialogSize / 10 * 7, gameWorld.buttonDialogWidth,
                gameWorld.buttonSize);
    }

    public void update(float delta) {
        for (Page p : pages) {
            p.update(delta);
        }
    }

    public void initPage() {
        pages = new Array<Page>();
        int number = 0;
        int count = (int)Math.ceil(gameWorld.assetLoader.textures.size / 6f);
        for (int i = 0; i < count; i++) {
            if ((i + 1)*6 <= gameWorld.assetLoader.textures.size)
                number = 6;
            else
                number = gameWorld.assetLoader.textures.size - i * 6;

            pages.add(new Page(gameWorld, number, i));
        }
    }

    public void draw(ShapeRenderer shaper, SpriteBatch batcher, float runTime) {
       /* shaper.begin(ShapeRenderer.ShapeType.Filled);
        shaper.setColor(0, 0, 0, 1f);
        shaper.rect(0, 0, gameWorld.WIDTH, gameWorld.HEIGHT);
        shaper.end();
        /*
        shaper.setColor(1, 1, 1, 1);

        if (gameWorld.assetLoader.isInternet && AssetLoader.prefs.getInteger("countVideo") < 3)
            shaper.rect(boundVideo.x, boundVideo.y, boundVideo.width, boundVideo.height);

        if (currentPage != pages.size - 1)
            shaper.rect(boundNext.x, boundNext.y, boundNext.width, boundNext.height);
        if (currentPage != 0)
            shaper.rect(boundBack.x, boundBack.y, boundBack.width, boundBack.height);
        shaper.rect(boundClose.x, boundClose.y, boundClose.width, boundClose.height);

        shaper.end();*/

        batcher.begin();
        batcher.draw(gameWorld.assetLoader.shopField, 0, 0, gameWorld.WIDTH, gameWorld.HEIGHT);
        if (gameWorld.assetLoader.isInternet && AssetLoader.prefs.getInteger("countVideo") < 3)
            batcher.draw(gameWorld.assetLoader.buttonVideo, boundVideo.x, boundVideo.y, boundVideo.width, boundVideo.height);
        if (currentPage != pages.size - 1)
            batcher.draw(gameWorld.assetLoader.buttonRight, boundNext.x, boundNext.y, boundNext.width, boundNext.height);
        if (currentPage != 0)
            batcher.draw(gameWorld.assetLoader.buttonLeft, boundBack.x, boundBack.y, boundBack.width, boundBack.height);
        batcher.draw(gameWorld.assetLoader.buttonBack, boundClose.x, boundClose.y, boundClose.width, boundClose.height);
        batcher.end();
        for(int i = 0; i < pages.size; i++)
            pages.get(i).draw(shaper, batcher, runTime);

        if (isFormAccept) {
            shaper.begin(ShapeRenderer.ShapeType.Filled);
            shaper.setColor(0.75f, 0.75f, 0.75f, 1);
            shaper.rect(boundForm.x, boundForm.y, boundForm.width, boundForm.height);
            shaper.setColor(0, 0, 0, 1);
            if (hasBought) {
                shaper.rect(boundCancel.x, boundCancel.y, boundCancel.width, boundCancel.height);
                shaper.rect(boundAccept.x, boundAccept.y, boundAccept.width, boundAccept.height);
            } else {
                shaper.rect(boundCancel.x, boundCancel.y, boundCancel.width, boundCancel.height);
            }
            shaper.end();
            batcher.begin();

           // gameWorld.assetLoader.font.getData().setScale(0.03f, -0.03f);
            gameWorld.assetLoader.font.draw(batcher, textMessage, boundForm.x, boundForm.y);
           // gameWorld.assetLoader.font.getData().setScale(0.05f, -0.05f);
            float width = TextSize.getWidth(gameWorld.assetLoader.font, textMessage);
            float height = TextSize.getHeight(gameWorld.assetLoader.font, textMessage);
            //gameWorld.assetLoader.font.getData().setScale(0.03f, -0.03f);
            gameWorld.assetLoader.font.draw(batcher, textMessage, boundForm.x + boundForm.width / 2 - width / 2,
                    boundForm.y + boundForm.height / 2 - height);
            //gameWorld.assetLoader.font.getData().setScale(0.05f, -0.05f);
            batcher.end();
        }

        float width = TextSize.getWidth(gameWorld.assetLoader.font, Money.money + "");
        Money.draw(shaper, batcher, gameWorld.WIDTH / 2 - width, gameWorld.MARGIN + gameWorld.buttonSize / 2 - gameWorld.buttonSize / 6,
                Money.money);
    }

    public boolean isClickedClose(float x, float y) {
        return boundClose.contains(x, y);
    }

    public boolean isClickedBack(float x, float y) {
        if (currentPage == 0 || pages.get(0).isMove())
            return false;
        return boundBack.contains(x, y);
    }

    public boolean isClickedNext(float x, float y) {
        if (currentPage == pages.size - 1 || pages.get(0).isMove())
            return false;
        return boundNext.contains(x, y);
    }

    public void setVelocity(float x, float y) {
        for (int i = 0; i < pages.size; i++)
            pages.get(i).setVelocity(x, y);
    }

    public void setAcceleration(float x, float y) {
        for (int i = 0; i < pages.size; i++)
            pages.get(i).setAcceleration(x, y);
    }

    public void dicrementPage() {
        currentPage--;
    }

    public void incrementPage() {
        currentPage++;
    }

    public boolean isClickedElement(float x, float y) {
        if ((item = pages.get(currentPage).isClicked(x, y)) != null) {
            if (!item.getBought()) {
                isFormAccept = true;
                if (Money.hasEnough(item.getCost())) {
                    textMessage = "SPEND " + item.getCost() + "?";
                    boundCancel.set(boundForm.x + boundForm.width / 2 + boundForm.width / 10, boundAccept.y,
                            boundCancel.width, boundCancel.height);
                    boundAccept.set(boundForm.x + gameWorld.dialogSize / 9, boundForm.y + gameWorld.dialogSize / 10 * 7,
                            boundAccept.width, boundAccept.height);
                    hasBought = true;
                } else {
                    textMessage = "NOT ENOUGH";
                    boundCancel.set(boundForm.x + boundForm.width / 2 - boundCancel.width / 2, boundCancel.y, boundCancel.width, boundCancel.height);
                    hasBought = false;
                }
            }
            else {
                selectItem(item);
            }
            return true;
        }
        return false;
    }

    private void selectItem(Item item) {
        gameWorld.assetLoader.currentAnimation = item.getAnimation();
        gameWorld.assetLoader.currentTexture = item.getTextureRegion();

        for (int i = 0; i < pages.size; i++) {
            pages.get(i).setSelected(false);
        }

        item.setSelected(true);
    }

    private void boughtItem(Item item) {
        if (Money.buy(item)) {
            isFormAccept = false;
        } else {
            //Типа донат азазаззазаза
           // textMessage = "DONATb DEBIL";

        }
    }

    public void onAcceptClicked(float x, float y) {
        if (boundAccept.contains(x, y)) {
            boughtItem(item);
        }
    }

    public void onCancelClicked(float x, float y) {
        if (boundCancel.contains(x, y)) {
            isFormAccept = false;
            item = null;
        }
    }

    public boolean isClickedVideo(float x, float y) {
        return gameWorld.assetLoader.isInternet && boundVideo.contains(x, y) && AssetLoader.prefs.getInteger("countVideo") < 3;
    }

    public boolean getFormAccept() {
        return isFormAccept;
    }

    public Item getItem() {
        return item;
    }
}
