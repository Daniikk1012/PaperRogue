package com.wgsoft.game.paperrogue.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static com.wgsoft.game.paperrogue.MyGdxGame.game;

public class PlayGameScreen {
    private Stage stage;

    public PlayGameScreen() {
        stage = new Stage(new ScreenViewport(), game.batch);
    }
}
