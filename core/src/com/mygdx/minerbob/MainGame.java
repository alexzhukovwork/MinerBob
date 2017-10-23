package com.mygdx.minerbob;


import com.badlogic.gdx.Game;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.TextSize;
import com.mygdx.minerbob.screen.GameScreen;
import com.mygdx.minerbob.screen.SplashScreen;


public class MainGame extends Game {
	private IActivityRequestHandler handler;

	public MainGame(IActivityRequestHandler handler) {
		this.handler = handler;
	}

	// По желанию можно добавить открытый метод для доступа к handler
	public IActivityRequestHandler getHandler() {
		return handler;
	}

	@Override
	public void create() {
		SplashScreen splashScreen = new SplashScreen(this);
		AssetLoader.loadSplashScreen();
		setScreen(splashScreen);
	}

	@Override
	public void dispose() {
		AssetLoader.dispose();
	}
}
