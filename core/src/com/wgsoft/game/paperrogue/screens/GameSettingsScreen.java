package com.wgsoft.game.paperrogue.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static com.wgsoft.game.paperrogue.MyGdxGame.game;

public class GameSettingsScreen implements Screen {
    private Stage stage;
    private Table container;
    private Label kolvo;


    public GameSettingsScreen() {
        stage = new Stage(new ScreenViewport(), game.batch);
        stage.addActor(new Image(game.skin, "background"){{
            setFillParent(true);
            setScaling(Scaling.fill);
        }});
        container = new Table(){{
            setFillParent(true);
            setDebug(false);
            add(kolvo = new Label( "Amount of players: 1", game.skin, "normal"));
            row();
            add(new Slider(1f, 4f, 1f, false, game.skin, "normal"){{
                setValue(1f);
                addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        kolvo.setText("Amount of players: " + (int)getValue());
                    }
                });
            }}).size(400f, 50f).padBottom(10f);
            row();
            add(new TextButton("PLAY", game.skin, "normal"){{
                addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        game.setScreen(game.playGameScreen);
                    }
                });
            }});
            row();
            add(new TextButton("BACK", game.skin, "normal"){{
                addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        game.setScreen(game.menuScreen);
                    }
                });
            }});
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
