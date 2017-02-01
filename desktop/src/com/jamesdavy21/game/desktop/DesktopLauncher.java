package com.jamesdavy21.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jamesdavy21.game.runner;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = runner.WIDTH;
		config.height = runner.HEIGHT;
		config.title = runner.TITLE;
		new LwjglApplication(new runner(), config);
	}
}
