package com.mygdx.tower_defence;

import com.badlogic.gdx.utils.Timer;
import com.mygdx.tower_defence.map.controllers.MapController;
import com.mygdx.tower_defence.map.model.MapModel;
import com.mygdx.tower_defence.map.views.MapView;
import com.mygdx.tower_defence.screens.SplashScreen;


public class GameController {

    private SplashScreen splash_screen;
    private final MainTowerDefence game;

    private Thread thread_delay;


    public GameController(final MainTowerDefence game) {
        this.game = game;
        setSplashScreen(game);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                setMapScreen("map1");
            }
        },1);
    }


    private void setSplashScreen(MainTowerDefence game) {
        splash_screen = new SplashScreen(game);
        game.setScreen(splash_screen);
    }

    private void setMapScreen(String map) {
        MapModel map_model = new MapModel(map);
        MapView map_view = new MapView(game,map_model);
        MapController map_controller = new MapController(map_view,map_model);
        game.setScreen(map_view);
    }


}
