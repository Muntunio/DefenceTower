package com.mygdx.tower_defence.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.tower_defence.MainTowerDefence;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = MainTowerDefence.GAME_NAME;
		config.width = MainTowerDefence.WIDTH;
		config.height = MainTowerDefence.HEIGHT;

		config.resizable = false;
		config.forceExit = false;


		new LwjglApplication(new MainTowerDefence(), config);


	}
}
