package com.mygdx.tower_defence.map.controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.tower_defence.map.model.MapModel;
import com.mygdx.tower_defence.map.views.MapView;

import java.awt.*;


public class MapController {

    private MapView map_view;
    private MapModel map_model;


    public MapController(MapView view, MapModel model) {
        this.map_view = view;
        this.map_model = model;

        addMapListener();
    }

    private void addMapListener() {
        map_model.getMapImage().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (button == 0)
                    handleLMB(x,y);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }


    private void handleLMB(float x, float y) {
        checkOnTowerClick(x,y);
    }

    private void checkOnTowerClick(float x, float y){
        if(map_model.isIsActiveTower()){
            Point active_pivot = map_model.getTowerPivot()[map_model.getTowerWidthActiveMenu()];
            if(Math.pow((x - active_pivot.x),2)+Math.pow((y - active_pivot.y),2) < Math.pow(85,2)){
                map_model.getBuildControllers()[map_model.getTowerWidthActiveMenu()].checkMenuTouchDown(x,y);
            }
        }

        for(int i=0; i<map_model.getTowerPivot().length; ++i){
            if(Math.pow((x - map_model.getTowerPivot()[i].x),2)+Math.pow((y - map_model.getTowerPivot()[i].y),2) < Math.pow(40,2)) {
                if(map_model.isIsActiveTower())
                    resetActiveBuildMenu();

                map_model.getBuildControllers()[i].setIsActiveBuildMenu(true);
                map_model.setTowerWithActiveMenu(i);
                map_model.setIsActiveTower(true);
                return;
            }
        }
        resetActiveBuildMenu();

    }

    private void resetActiveBuildMenu(){
        map_model.setIsActiveTower(false);
        map_model.getBuildControllers()[map_model.getTowerWidthActiveMenu()].setIsActiveBuildMenu(false);
    }


}
