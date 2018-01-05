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

        float x = gameWorld.WIDTH / 2 - gameWorld.dialogWidth / 2;
        float y = gameWorld.HEIGHT / 2 - gameWorld.dialogHeight / 2;
        boundForm = new Rectangle(x, y, gameWorld.dialogWidth, gameWorld.dialogHeight);
        boundAccept = new Rectangle(boundForm.x + boundForm.width / 13, boundForm.y + boundForm.height - boundForm.height / 5,
                gameWorld.buttonDialogWidth, gameWorld.buttonDialogHeight);
        boundCancel = new Rectangle(boundForm.x + boundForm.width - gameWorld.buttonDialogWidth - boundForm.width / 13,
                boundForm.y + boundForm.height - boundForm.height / 5,
                gameWorld.buttonDialogWidth, gameWorld.buttonDialogHeight);
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

    public void draw(SpriteBatch batcher, float runTime) {
        batcher.draw(gameWorld.assetLoader.shopField, 0, 0, gameWorld.WIDTH, gameWorld.HEIGHT);
        if (gameWorld.assetLoader.isInternet && AssetLoader.prefs.getInteger("countVideo") < 3)
            batcher.draw(gameWorld.assetLoader.buttonVideo, boundVideo.x, boundVideo.y, boundVideo.width, boundVideo.height);
        if (currentPage != pages.size - 1)
            batcher.draw(gameWorld.assetLoader.buttonRight, boundNext.x, boundNext.y, boundNext.width, boundNext.height);
        if (currentPage != 0)
            batcher.draw(gameWorld.assetLoader.buttonLeft, boundBack.x, boundBack.y, boundBack.width, boundBack.height);
        batcher.draw(gameWorld.assetLoader.buttonBack, boundClose.x, boundClose.y, boundClose.width, boundClose.height);

        for(int i = 0; i < pages.size; i++)
            pages.get(i).draw(batcher, runTime);

        if (isFormAccept) {
            batcher.draw(gameWorld.assetLoader.boxTexture, boundForm.x, boundForm.y, boundForm.width, boundForm.height);
            if (hasBought) {
                batcher.draw(gameWorld.assetLoader.noMenuTexture, boundCancel.x, boundCancel.y, boundCancel.width, boundCancel.height);
                batcher.draw(gameWorld.assetLoader.yesMenuTexture, boundAccept.x, boundAccept.y, boundAccept.width, boundAccept.height);
            } else {
                batcher.draw(gameWorld.assetLoader.okMenuTexture, boundCancel.x, boundCancel.y, boundCancel.width, boundCancel.height);
            }

           // gameWorld.assetLoader.font.getData().setScale(0.03f, -0.03f);
            float width = TextSize.getWidth(gameWorld.assetLoader.font, textMessage);
            gameWorld.assetLoader.font.draw(batcher, textMessage, boundForm.x + boundForm.width / 2 - width / 2,
                    boundForm.y + boundForm.height / 15);
        }

        float width = TextSize.getWidth(gameWorld.assetLoader.font, Money.money + "");
        Money.draw(batcher, gameWorld.WIDTH / 2 - width, gameWorld.MARGIN + gameWorld.buttonSize / 2 - gameWorld.buttonSize / 6,
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
                    boundAccept.set(boundForm.x + boundForm.width / 13, boundForm.y + boundForm.height - boundForm.height / 5,
                            gameWorld.buttonDialogWidth, gameWorld.buttonDialogHeight);
                    boundCancel.set(boundForm.x + boundForm.width - gameWorld.buttonDialogWidth - boundForm.width / 13,
                            boundForm.y + boundForm.height - boundForm.height / 5,
                            gameWorld.buttonDialogWidth, gameWorld.buttonDialogHeight);
                    hasBought = true;
                } else {
                    textMessage = "NOT ENOUGH";
                    boundCancel.set(boundForm.x + boundForm.width / 2 - this.gameWorld.buttonDialogWidth / 2,
                            boundForm.y + boundForm.height - boundForm.height / 5,
                            this.gameWorld.buttonDialogWidth, this.gameWorld.buttonDialogHeight);
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
