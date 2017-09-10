package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.helpers.AssetLoader;
import com.mygdx.game.screen.GameScreen;

public class Main extends Game {

	@Override
	public void create() {
		Gdx.app.log("ZBGame", "Created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		AssetLoader.dispose();
	}
}
