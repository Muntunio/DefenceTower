package com.mygdx.tower_defence.tower.controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;

import javax.activity.ActivityRequiredException;

public abstract class AbstractTowerController {


    public abstract void setPivot(int x, int y);

    public abstract Actor getView();

    public abstract void checkMenuTouchDown(float x, float y);

    public abstract void setIsActiveBuildMenu(boolean is_active_build_menu);
}
