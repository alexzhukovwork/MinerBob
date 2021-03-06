package com.mygdx.minerbob;


import com.badlogic.gdx.Game;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.TextSize;
import com.mygdx.minerbob.screen.GameScreen;
import com.mygdx.minerbob.screen.SplashScreen;


public class MainGame extends Game {
	private IActivityRequestHandler handler;
	private SplashScreen splashScreen;

	public MainGame(IActivityRequestHandler handler) {
		this.handler = handler;
	}

	// По желанию можно добавить открытый метод для доступа к handler
	public IActivityRequestHandler getHandler() {
		return handler;
	}

	@Override
	public void create() {
		splashScreen = new SplashScreen(this);
		splashScreen.getAssetLoader().loadSplashScreen();
		setScreen(splashScreen);
	}

	@Override
	public void dispose() {
		splashScreen.getAssetLoader().dispose();
	}
}
