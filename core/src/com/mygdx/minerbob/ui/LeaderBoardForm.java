package com.mygdx.minerbob.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
 * Created by Алексей on 20.02.2018.
 */

public class LeaderBoardForm {
    private Rectangle bound;
    private GameWorld gameWorld;
    private String [] users;

    public LeaderBoardForm(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        bound = new Rectangle(0, 0,
                gameWorld.WIDTH, gameWorld.HEIGHT);
    }

    public void draw(ShapeRenderer renderer, SpriteBatch batcher) {
        batcher.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(0f, 0f, 0f, 0.8f);
        renderer.rect(bound.x, bound.y, bound.width, bound.height);
        renderer.end();
        batcher.begin();
        gameWorld.assetLoader.font.draw(batcher, "BEST PLAYERS",
                gameWorld.WIDTH / 2 - TextSize.getWidth(gameWorld.assetLoader.font, "BEST PLAYERS") / 2,
                gameWorld.MARGIN);
       // batcher.draw(gameWorld.assetLoader.shopField, bound.x, bound.y, bound.width, bound.height);

        if (users != null) {
            String score;
            String login;
            float margin = 0;
            for (int i = 0; i < users.length; i++) {
                if (users[i].contains(" ")) {
                    login = users[i].substring(0, users[i].indexOf(" "));
                    score = users[i].substring(users[i].indexOf(" "), users[i].length());

                    if (i != users.length - 1) {
                        login = (i + 1) + ". " + login;
                        margin = (i + 1) * 5 * gameWorld.MARGIN;
                    } else
                        margin = (i + 0.75f) * 5 * gameWorld.MARGIN;

                    gameWorld.assetLoader.font.draw(batcher, login,
                            bound.x + gameWorld.MARGIN, gameWorld.buttonSize / 2 + bound.y + gameWorld.MARGIN + margin);

                    gameWorld.assetLoader.font.draw(batcher, score,
                            bound.width - gameWorld.MARGIN - TextSize.getWidth(gameWorld.assetLoader.font, score),
                            gameWorld.buttonSize / 2 + bound.y + gameWorld.MARGIN + margin);
                }
            }
        }
    }

    public void generateString(String str) {
        users = str.split("\n");
        if (users.length > 0) {
            String[] arr;
            String[] arrLast = users[users.length - 1].split(" ");
            users[users.length - 1] = "\n" + arrLast[1] + "(You) \n" + arrLast[2];
            for (int i = 0; i < users.length - 1; i++) {
                arr = users[i].split(" ");
                if (arr[0].equals(arrLast[0])) {
                    users[i] = arr[1] + "(You) " + arr[2];
                    users[users.length - 1] = "";
                } else
                    users[i] = arr[1] + " " + arr[2];
            }
        }
    }

    public void setUsers(String [] users) {
        this.users = users;
    }

   /* public boolean isClickedClose(float x, float y) {
      //  return boundClose.contains(x, y);
    }*/
}
