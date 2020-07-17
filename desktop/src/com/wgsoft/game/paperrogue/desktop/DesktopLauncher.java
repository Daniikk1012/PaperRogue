package com.wgsoft.game.paperrogue.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.wgsoft.game.paperrogue.MyGdxGame;

public class DesktopLauncher {
	private static final boolean PACK = true;
	public static void main (String[] arg) {
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.combineSubdirectories = true;
		TexturePacker.process(settings, "raw/img", "img", "skin");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.forceExit = false;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
