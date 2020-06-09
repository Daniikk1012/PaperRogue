package com.wgsoft.game.paperrogue;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.wgsoft.game.paperrogue.screens.MenuScreen;

public class MyGdxGame extends Game {
	public static MyGdxGame game;

	public SpriteBatch batch;

	public Skin skin;

	public MenuScreen menuScreen;

	public MyGdxGame(){
		game = this;
	}
	
	@Override
	public void create () {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("img/skin.json"));

		menuScreen = new MenuScreen();

		setScreen(menuScreen);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();

		batch.dispose();
		skin.dispose();
	}
}
