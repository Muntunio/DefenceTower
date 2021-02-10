package com.mygdx.tower_defence.tower.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.tower_defence.enemy.models.AbstractEnemyModel;
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

    private boolean to_remove;

    //For enemy hit
    private int range;
    private boolean haveTarget;
    private AbstractEnemyModel target_model;

    public TowerModelAbstract() {
        is_active_build_menu = false;
        setModel();
        level = 0;
        movable_model.setLevel(level);
        range = 300;
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
        isTargetStillInRange();
    }

    public int getLevel(){ return level; }

    public boolean isActiveMenu() { return is_active_build_menu; }
    public void setActiveMenu(boolean activeMenu) { is_active_build_menu = activeMenu; }

    public abstract TowerType getType();

    public void setToRemove(boolean to_remove) { this.to_remove = to_remove; }
    public boolean isToRemove() { return to_remove; }

    public int getRange() { return range; }

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

    public void isEnemyInRange(AbstractEnemyModel mob) {
        haveTarget = true;
        target_model = mob;
    }

    private void isTargetStillInRange() {
        if (Math.pow(getXPositionPillar() - target_model.getPosX(), 2) +
                Math.pow(getYPositionPillar() - target_model.getPosY(), 2) > Math.pow(range, 2)) {
            haveTarget = false;
        }
    }

}
