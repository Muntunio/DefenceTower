package com.mygdx.tower_defence;

import com.badlogic.gdx.Game;
import com.mygdx.tower_defence.screens.SplashScreen;

public class MainTowerDefence extends Game {

	//private ScreenController screen_controler;
	private SplashScreen splash_screen;

	private GameController game_controller;

	public static String GAME_NAME = "Tower Defence";;
	public static int WIDTH= 1660;
	public static final int HEIGHT = 850;

	private boolean is_paused;


	@Override
	public void create () {
		game_controller = new GameController(this);
	}

	public boolean isIs_paused() {
		return is_paused;
	}

	public void setIs_paused(boolean is_paused) {
		this.is_paused = is_paused;
	}
}
