package com.wgsoft.game.paperrogue.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static com.wgsoft.game.paperrogue.MyGdxGame.game;


public class OptionScreen implements Screen {
    private Stage stage;
    private Table container;
    public OptionScreen() {
        stage = new Stage(new ScreenViewport(), game.batch);
        stage.addActor(new Image(game.skin, "background"){{
            setFillParent(true);
            setScaling(Scaling.fill);
        }});
        container = new Table(){{
            setFillParent(true);
            setDebug(false);
            add(new Slider(0f, 1f, 0.1f, false, game.skin, "normal")).size(400f, 50f);
        }};
        stage.addActor(container);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if((float)width/height > 800f/480f){
            ((ScreenViewport)stage.getViewport()).setUnitsPerPixel(480f/height);
        }else{
            ((ScreenViewport)stage.getViewport()).setUnitsPerPixel(800f/width);
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
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    }
}
