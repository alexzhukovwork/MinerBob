package com.mygdx.minerbob.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.minerbob.IActivityRequestHandler;
import com.mygdx.minerbob.IRewardVideo;
import com.mygdx.minerbob.MainGame;
import com.mygdx.minerbob.gameworld.GameWorld;

public class DesktopLauncher implements IActivityRequestHandler, IRewardVideo{

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 400;
		config.width = 250;
		new LwjglApplication(new MainGame(new DesktopLauncher() {
			@Override
			public void showAds(boolean show) {

			}
		}), config);
	}

	public void init() {

	}

	@Override
	public void showAds(boolean show) {

	}

	@Override
	public boolean hasVideoReward() {
		return false;
	}

	@Override
	public void showVideoAd() {

	}

	@Override
	public void setGameWorld(GameWorld gameWorld) {

	}
}
