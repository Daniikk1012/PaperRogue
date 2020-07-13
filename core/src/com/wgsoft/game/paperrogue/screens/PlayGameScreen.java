package com.wgsoft.game.paperrogue.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


import static com.wgsoft.game.paperrogue.MyGdxGame.game;

public class PlayGameScreen implements Screen {
    private Stage stage;

    public PlayGameScreen() {
        stage = new Stage(new ScreenViewport(), game.batch);
    }
    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int width, int height) {
        if((float)width/height > 1600f/960f){
            ((ScreenViewport)stage.getViewport()).setUnitsPerPixel(960f/height);
        }else{
            ((ScreenViewport)stage.getViewport()).setUnitsPerPixel(1600f/width);
        }
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }
}
