package com.wgsoft.game.paperrogue;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.wgsoft.game.paperrogue.screens.MenuScreen;
import com.wgsoft.game.paperrogue.screens.OptionScreen;

public class MyGdxGame extends Game {
	public static MyGdxGame game;

	public SpriteBatch batch;

	public Skin skin;

	public MenuScreen menuScreen;

	public OptionScreen optionScreen;

	public Music bgmusic;

	public MyGdxGame(){
		game = this;
	}
	
	@Override
	public void create () {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("img/skin.json"));

		menuScreen = new MenuScreen();

		optionScreen = new OptionScreen();
		bgmusic = Gdx.audio.newMusic(Gdx.files.internal("lol.mp3"));
		bgmusic.play();
		bgmusic.setLooping(true);

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
		menuScreen.dispose();
		optionScreen.dispose();
		batch.dispose();
		skin.dispose();
		bgmusic.dispose();
	}
}
