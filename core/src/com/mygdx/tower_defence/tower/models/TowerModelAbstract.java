package com.mygdx.tower_defence.tower.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.tower_defence.tower.models.movable.MovablePartModel;
import com.mygdx.tower_defence.tower.textures.TowerType;

public abstract class TowerModelAbstract {
    protected MovablePartModel movable_model;
    protected int level;
    private int x_position;
    private int y_position;
    private int x_pivot;
    private int y_pivot;
    private int x_position_build_menu;
    private int y_position_build_menu;

    private boolean is_active_build_menu;

    public TowerModelAbstract() {
        is_active_build_menu = false; //TODO Change this
        setModel();
        level = 0;
        movable_model.setLevel(level);
    }

    protected abstract void setModel();

    public void setPosition(int x, int y){
        x_position = x;
        y_position = y;
        movable_model.setPosition(x_position,y_position);

        x_pivot = x + getPillarTexture().getRegionWidth()/2;
        y_pivot = y + 40;

        calculatePositionBuildMenu();
    }

    public void setPivot(int x, int y){
        x_pivot = x;
        y_pivot = y;

        x_position = x - getPillarTexture().getRegionWidth()/2 + 5;
        y_position = y - 25;
        movable_model.setPosition(x_position,y_position);

        calculatePositionBuildMenu();
    }

    private void calculatePositionBuildMenu(){
        x_position_build_menu = x_pivot - getBuildMenu().getRegionWidth()/2;
        y_position_build_menu = y_pivot - getBuildMenu().getRegionHeight()/2;
    }

    public void upgrade(){
        if(level < 2)
            ++level;
        movable_model.setLevel(level);
    }

    public void destroy(){
        if(level > 0)
            --level;
        movable_model.setLevel(level);
    }

    //Interface
    public abstract TextureRegion getPillarTexture();
    public abstract TextureRegion getBuildMenu();
    public int getXPositionPillar(){ return  x_position; }
    public int getYPositionPillar(){ return  y_position; }
    public int getXPositionBuildMenu() { return x_position_build_menu; }
    public int getYPositionBuildMenu() { return y_position_build_menu; }
    public void update(float delta){
        movable_model.update(delta);
    }
    public int getLevel(){ return level; }

    public boolean isActiveMenu() { return is_active_build_menu; }
    public void setActiveMenu(boolean activeMenu) { is_active_build_menu = activeMenu; }

    public abstract TowerType getType();

    //Interface to movable
    public TextureRegion getBulletTexture(){ return movable_model.getBulletTexture(); }
    public TextureRegion getFrontTexture(){ return movable_model.getFrontTexture(); }
    public TextureRegion getBackTexture(){ return movable_model.getBackTexture(); }
    public int getXPositionBack() { return movable_model.getXPositionBack(); }
    public int getYPositionBack() { return movable_model.getYPositionBack(); }
    public int getXPositionFront() { return movable_model.getXPositionFront(); }
    public int getYPositionFront() { return movable_model.getYPositionFront(); }
    public int getXPositionBullet() { return movable_model.getXPositionBullet(); }
    public int getYPositionBullet() { return movable_model.getYPositionBullet(); }
    public boolean isBullet() { return movable_model.isBullet(); }

}
