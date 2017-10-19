package com.mygdx.minerbob.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.screen.GameScreen;
import com.mygdx.minerbob.ui.PauseForm;
import com.mygdx.minerbob.ui.ShopHelpers.Item;

/**
 * Created by Алексей on 11.09.2017.
 */

public class InputHandler implements InputProcessor {
    private float sclX;
    private float sclY;
    private GameWorld gameWorld;
    private Item item;
    private long touch;


    public void setScale() {
        sclY = GameScreen.screenHeight / GameScreen.HEIGHT;
        sclX = GameScreen.screenWidth / GameScreen.WIDTH;
    }
    private int currentElement;

    public InputHandler(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        setScale();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == 4) {
            onBackClicked();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }



    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float x = scaleX(screenX);
        float y = scaleY(screenY);

        if (gameWorld.isDailyBonus()) {
            if (gameWorld.getDailyBonus().isClicked(x, y)) {

            }
            return true;
        }

        if (gameWorld.isRunning()) {
            if (gameWorld.getRunningForm().isClickedPause(x, y)) {

            }
            else
                gameWorld.getActor().onTouch(x, y);
            return true;

        }

        if (gameWorld.isShop()) {
            if (gameWorld.getShop().getFormAccept()) {

            } else {
                if (gameWorld.getShop().isClickedClose(x, y)) {

                }
                if (gameWorld.getShop().isClickedBack(x, y)) {

                }
                if (gameWorld.getShop().isClickedNext(x, y)) {

                }

                if (gameWorld.getShop().isClickedVideo(x, y)) {

                }

                if (gameWorld.getShop().isClickedElement(x, y)) {
                    item = gameWorld.getShop().getItem();
                }
            }
            return true;
        }

        if (gameWorld.isMenu()) {
            if (gameWorld.getMenu().isClickedPlay(x, y)) {

            }
            if (gameWorld.getMenu().isClickedShop(x, y)) {

            }
            if (gameWorld.getMenu().isClickedSound(x, y));



            return true;
        }

        if (gameWorld.isRestart()) {


            if (gameWorld.getPauseForm().isClickedMenu(x, y)) {

            }

            if (gameWorld.getPauseForm().isClickedRestart(x, y)) {

            }
            return true;
        }

        if (gameWorld.isPause()) {
            if (gameWorld.getPauseForm().isClickedResume(x, y)) {

            }

            if (gameWorld.getPauseForm().isClickedMenu(x, y)) {

            }
            return true;
        }


        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (item != null) {
            item.isAnimation = false;
            item = null;
        }

        float x = scaleX(screenX);
        float y = scaleY(screenY);

        if (gameWorld.isDailyBonus()) {
            if (gameWorld.getDailyBonus().isClicked(x, y)) {
                gameWorld.setState(GameWorld.GameState.MENU);
                if (AssetLoader.prefs.getBoolean("isNextDay")) {
                    Money.add(AssetLoader.prefs.getInteger("countBonus") * 10);
                    AssetLoader.prefs.putBoolean("isNextDay", false);
                    AssetLoader.prefs.flush();
                }
            }
            return true;
        }

        if (gameWorld.isRunning()) {
            if (gameWorld.getRunningForm().isClickedPause(x, y)) {
                gameWorld.setState(GameWorld.GameState.PAUSE);
                gameWorld.getPauseForm().setState(PauseForm.State.PAUSE);
            }

            return true;

        }

        if (gameWorld.isShop()) {
            if (gameWorld.getShop().getFormAccept()) {
                gameWorld.getShop().onAcceptClicked(x, y);
                gameWorld.getShop().onCancelClicked(x, y);
            } else {
                if (gameWorld.getShop().isClickedClose(x, y)) {
                    gameWorld.setState(GameWorld.GameState.MENU);
                }
                if (gameWorld.getShop().isClickedBack(x, y)) {
                    gameWorld.getShop().setVelocity(GameScreen.WIDTH * 3, 0);
                    gameWorld.getShop().setAcceleration(-GameScreen.WIDTH / 10, 0);
                    gameWorld.getShop().dicrementPage();
                }
                if (gameWorld.getShop().isClickedNext(x, y)) {
                    gameWorld.getShop().setVelocity(-GameScreen.WIDTH * 3, 0);
                    gameWorld.getShop().setAcceleration(GameScreen.WIDTH / 10, 0);
                    gameWorld.getShop().incrementPage();
                }

                if (gameWorld.getShop().isClickedVideo(x, y)) {
                    gameWorld.rewardVideo.showVideoAd();
                }
            }
            return true;
        }

        if (gameWorld.isMenu()) {
            if (gameWorld.getMenu().isClickedPlay(x, y)) {
                GameWorld.currentMoney = 0;
                gameWorld.setState(GameWorld.GameState.RUNNING);
            }
            if (gameWorld.getMenu().isClickedShop(x, y)) {
                gameWorld.setState(GameWorld.GameState.SHOP);
            }
            if (gameWorld.getMenu().isClickedSound(x, y));
            gameWorld.getSound().setState();


            return true;
        }

        if (gameWorld.isRestart()) {
            gameWorld.getPauseForm().checkRecord();

            if (gameWorld.getPauseForm().isClickedMenu(x, y)) {
                gameWorld.setState(GameWorld.GameState.MENU);
                gameWorld.restart();
                AssetLoader.prefs.flush();
            }

            if (gameWorld.getPauseForm().isClickedRestart(x, y)) {
                gameWorld.setState(GameWorld.GameState.RUNNING);
                gameWorld.restart();
                AssetLoader.prefs.flush();
            }
            return true;
        }

        if (gameWorld.isPause()) {
            if (gameWorld.getPauseForm().isClickedResume(x, y)) {
                gameWorld.setState(GameWorld.GameState.RUNNING);
                gameWorld.getPauseForm().checkRecord();
                AssetLoader.prefs.flush();
            }

            if (gameWorld.getPauseForm().isClickedMenu(x, y)) {
                gameWorld.getPauseForm().checkRecord();
                AssetLoader.prefs.flush();
                gameWorld.restart();
                gameWorld.setState(GameWorld.GameState.MENU);
            }
            return true;
        }


        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(item != null && item.getBought() && TimeUtils.timeSinceMillis(touch) > 100) {
            item.isAnimation = true;
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int scaleX(float screenX) {
        return (int) (screenX / sclX);
    }

    private int scaleY(float screenY) {
        return (int) (screenY / sclY);
    }

    private void onBackClicked() {
        if (gameWorld.isShop())
            gameWorld.setState(GameWorld.GameState.MENU);
        else if (gameWorld.isRunning())
            gameWorld.setState(GameWorld.GameState.PAUSE);
        else if (gameWorld.isMenu())
            Gdx.app.exit();
    }
}
