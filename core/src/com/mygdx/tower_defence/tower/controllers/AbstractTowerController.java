package com.mygdx.tower_defence.tower.controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.tower_defence.map.model.MapModel;

import javax.activity.ActivityRequiredException;

public abstract class AbstractTowerController {
    protected MapModel map_model;

    public AbstractTowerController(MapModel map_model) {
        this.map_model = map_model;
    }

    public abstract void setPivot(int x, int y);

    public abstract Actor getView();

    public abstract void checkMenuTouchDown(float x, float y);

    public abstract void setIsActiveBuildMenu(boolean is_active_build_menu);
}
