package com.mygdx.minerbob.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.Money;
import com.mygdx.minerbob.screen.GameScreen;
import com.mygdx.minerbob.ui.ShopHelpers.Item;
import com.mygdx.minerbob.ui.ShopHelpers.Page;

import java.util.ArrayList;

/**
 * Created by Алексей on 18.09.2017.
 */

public class ShopForm {
    private Rectangle boundClose, boundNext, boundBack, boundVideo;
    private Rectangle boundFrom, boundAccept, boundCancel;
    private int currentPage;
    private ArrayList<Page> pages;
    private boolean isFormAccept;
    private Item item;
    private String textMessage;
    private boolean hasBought;

    TextureRegion rightTexture, leftTexture, closeTexture;


    public ShopForm() {
        initPage();
        isFormAccept = false;
        hasBought = false;
        currentPage = 0;
        boundVideo = new Rectangle(0, 0, GameScreen.WIDTH / 6, GameScreen.WIDTH / 6);
        boundClose = new Rectangle(GameScreen.WIDTH - GameScreen.WIDTH / 6, 0, GameScreen.WIDTH / 6, GameScreen.WIDTH / 6);
        boundBack = new Rectangle(0, GameScreen.HEIGHT - GameScreen.WIDTH / 6, GameScreen.WIDTH / 6, GameScreen.WIDTH / 6);
        boundNext = new Rectangle(GameScreen.WIDTH - GameScreen.WIDTH / 6, GameScreen.HEIGHT - GameScreen.WIDTH / 6, GameScreen.WIDTH / 6, GameScreen.WIDTH / 6);
        leftTexture = AssetLoader.buttonLeft;
        rightTexture = AssetLoader.buttonRight;
        closeTexture = AssetLoader.buttonClose;

        float x = GameScreen.WIDTH / 5;
        float y = GameScreen.HEIGHT / 5;
        float width = GameScreen.WIDTH / 5 * 3;
        float height = width;
        boundFrom = new Rectangle(x, y, width, height);
        boundCancel = new Rectangle(x + width / 2 + width / 10, y + height / 10 * 7, width / 2 - width / 10 * 2, height / 10 * 2);
        boundAccept = new Rectangle(x + width / 10, y + height / 10 * 7, width / 2 - width / 10 * 2, height / 10 * 2);
    }

    public void update(float delta) {
        for (Page p : pages) {
            p.update(delta);
        }
    }

    public void initPage() {
        pages = new ArrayList<Page>();
        int number = 0;
        int count = (int)Math.ceil(AssetLoader.textures.size / 6f);
        for (int i = 0; i < count; i++) {
            if ((i + 1)*6 <= AssetLoader.textures.size)
                number = 6;
            else
                number = AssetLoader.textures.size - i * 6;

            pages.add(new Page(number, i));
        }
    }

    public void draw(ShapeRenderer shaper, SpriteBatch batcher, float runTime) {
        shaper.begin(ShapeRenderer.ShapeType.Filled);
        shaper.setColor(0, 0, 0, 1f);
        shaper.rect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
        shaper.setColor(1, 1, 1, 1);

        if (AssetLoader.isInternet && AssetLoader.prefs.getInteger("countVideo") < 3)
            shaper.rect(boundVideo.x, boundVideo.y, boundVideo.width, boundVideo.height);

        shaper.end();

        batcher.begin();
        if (currentPage != pages.size() - 1)
            batcher.draw(rightTexture, boundNext.x, boundNext.y, boundNext.width, boundNext.height);
        if (currentPage != 0)
            batcher.draw(leftTexture, boundBack.x, boundBack.y, boundBack.width, boundBack.height);
        batcher.draw(closeTexture, boundClose.x, boundClose.y, boundClose.width, boundClose.height);
        batcher.end();
        for(Page p : pages)
            p.draw(shaper, batcher, runTime);

        if (isFormAccept) {
            shaper.begin(ShapeRenderer.ShapeType.Filled);
            shaper.setColor(0.75f, 0.75f, 0.75f, 1);
            shaper.rect(boundFrom.x, boundFrom.y, boundFrom.width, boundFrom.height);
            shaper.setColor(0, 0, 0, 1);
            if (hasBought) {
                shaper.rect(boundCancel.x, boundCancel.y, boundCancel.width, boundCancel.height);
                shaper.rect(boundAccept.x, boundAccept.y, boundAccept.width, boundAccept.height);
            } else {
                shaper.rect(boundCancel.x, boundCancel.y, boundCancel.width, boundCancel.height);
            }
            shaper.end();
            batcher.begin();
            AssetLoader.font.getData().setScale(0.03f, -0.03f);
            AssetLoader.font.draw(batcher, textMessage, boundFrom.x, boundFrom.y);
            AssetLoader.font.getData().setScale(0.05f, -0.05f);
            batcher.end();
        }

        Money.draw(shaper, batcher, GameScreen.WIDTH / 5, 1, Money.money);
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
        if (currentPage == pages.size() - 1 || pages.get(0).isMove())
            return false;
        return boundNext.contains(x, y);
    }

    public void setVelocity(float x, float y) {
        for (Page p : pages) {
            p.setVelocity(x, y);
        }
    }

    public void setAcceleration(float x, float y) {
        for (Page p : pages) {
            p.setAcceleration(x, y);
        }
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
                    textMessage = "Spend " + item.getCost() + "?";
                    boundCancel.set(boundFrom.x + boundFrom.width / 2 + boundFrom.width / 10, boundAccept.y,
                            boundCancel.width, boundCancel.height);
                    hasBought = true;
                } else {
                    textMessage = "Need more money";
                    boundCancel.set(boundFrom.x + boundFrom.width / 2 - boundCancel.width / 2, boundCancel.y, boundCancel.width, boundCancel.height);
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
        AssetLoader.currentAnimation = item.getAnimation();
        AssetLoader.currentTexture = item.getTextureRegion();

        for (int i = 0; i < pages.size(); i++) {
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
        return AssetLoader.isInternet && boundVideo.contains(x, y) && AssetLoader.prefs.getInteger("countVideo") < 3;
    }



    public boolean getFormAccept() {
        return isFormAccept;
    }

    public Item getItem() {
        return item;
    }
}
