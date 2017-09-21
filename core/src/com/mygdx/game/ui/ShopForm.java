package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.ui.ShopHelpers.Page;

import java.util.ArrayList;

/**
 * Created by Алексей on 18.09.2017.
 */

public class ShopForm {
    private Rectangle bound, boundClose, boundNext, boundBack;
    private int currentPage;
    private int amountRow = 3, amountCol = 2;
    private ArrayList<Page> pages;

    public ShopForm() {
        initPage();
        currentPage = 0;
        bound = new Rectangle(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
        boundClose = new Rectangle(GameScreen.WIDTH - GameScreen.WIDTH / 6, 0, GameScreen.WIDTH / 6, GameScreen.HEIGHT / 12);
        boundBack = new Rectangle(0, GameScreen.HEIGHT - GameScreen.HEIGHT / 12, GameScreen.WIDTH / 6, GameScreen.HEIGHT / 12);
        boundNext = new Rectangle(GameScreen.WIDTH - GameScreen.WIDTH / 6, GameScreen.HEIGHT - GameScreen.HEIGHT / 12, GameScreen.WIDTH / 6, GameScreen.HEIGHT / 12);
    }

    public void update(float delta) {
        for (Page p : pages) {
            p.update(delta);
        }
    }

    public void initPage() {
        pages = new ArrayList<Page>();
        pages.add(new Page(6, 0));
        pages.add(new Page(4, 1));
        pages.add(new Page(3, 2));
        pages.add(new Page(3, 3));
        pages.add(new Page(3, 4));
        pages.add(new Page(3, 5));
    }

    public void draw(ShapeRenderer shaper, SpriteBatch batcher) {
        shaper.begin(ShapeRenderer.ShapeType.Filled);
        shaper.setColor(0, 0, 0, 1f);
        shaper.rect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
        shaper.setColor(1, 1, 1, 1);
        shaper.rect(boundClose.x, boundClose.y, boundClose.width, boundClose.height);
        if (currentPage != pages.size() - 1)
            shaper.rect(boundNext.x, boundNext.y, boundNext.width, boundNext.height);
        if (currentPage != 0)
            shaper.rect(boundBack.x, boundBack.y, boundBack.width, boundBack.height);
        shaper.end();
        for(Page p : pages)
            p.draw(shaper, batcher);

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

    public int getCurrentPage() {
        return currentPage;
    }
}
