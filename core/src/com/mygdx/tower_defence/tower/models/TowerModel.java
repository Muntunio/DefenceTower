package com.mygdx.tower_defence.tower.models;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.tower_defence.map.model.MapAssetModel;

public class TowerModel {

    private int pivot_x;
    private int pivot_y;
    private int active_radius;
    private boolean upDirection;

    private int pos_x_build_menu;
    private int pos_y_build_menu;

    private int pos_x_pillar;
    private int pos_y_pillar;

    private int pos_x_front;
    private int pos_y_front;

    private int pos_x_back;
    private int pos_y_back;

    private TextureAtlas tower_assets;
    private TextureRegion build_menu;
    private TextureRegion pillar;
    private TextureRegion front;
    private TextureRegion back;

    private boolean isActiveBuildMenu;
    private boolean isBuilt;

    private float time_status;

    //Setting
    int level;

    public TowerModel(MapAssetModel manager) {
        tower_assets = manager.manager.get(MapAssetModel.soil_tower);
        isActiveBuildMenu = false;
        isBuilt = false;
        level = 0;
        build_menu = tower_assets.createSprite("build");
        active_radius = 0;
        upDirection = true;
    }


    public void updateLevel(){
        if(level>2)
            return;

        ++level;
        if(level == 1)
            createTower();
        else
            upgradeTower();

    }

    private void createTower() {
        isBuilt = true;
        active_radius = 200;
        upgradeTower();
        setTowerTexturesPosition();
    }

    private void upgradeTower() {
        pillar = tower_assets.createSprite("lvl"+level+"_pillar");
        front = tower_assets.createSprite("lvl"+level+"_front");
        back = tower_assets.createSprite("lvl"+level+"_back");
        build_menu = tower_assets.createSprite("lvl"+level+"_build");
    }

    public void animationTowerFire(float dt){
        time_status += dt;
        moveUpAndDownPlatform();
    }

    private void moveUpAndDownPlatform() {
        if(upDirection) {
            if (pos_y_pillar + 70 > pos_y_front) {
                pos_y_front += 1;
                pos_y_back += 1;
            } else
                upDirection = false;
        }
        else {
            if (pos_y_pillar + 30 < pos_y_front) {
                pos_y_front -= 1;
                pos_y_back -= 1;
            } else
                upDirection = true;
        }

    }

    //Getters and Setters
    public void setActiveBuildMenu(){ isActiveBuildMenu = true; }

    public void resetActiveBuildMenu(){ isActiveBuildMenu = false; }

    public boolean isActiveBuildMenu() { return isActiveBuildMenu; }

    public boolean isBuilt() { return isBuilt; }

    public TextureRegion getBuildMenu() { return build_menu; }

    public TextureRegion getPillar() { return pillar; }

    public TextureRegion getFront() { return front; }

    public TextureRegion getBack() { return back; }

    public int getPivot_x() { return pivot_x; }

    public int getPivot_y() { return pivot_y; }

    public int getPosXBuildMenu() { return pos_x_build_menu; }

    public int getPosYBuildMenu() { return pos_y_build_menu; }

    public int getPosXPillar() { return pos_x_pillar; }

    public int getPosYPillar() { return pos_y_pillar; }

    public int getPosXFront() { return pos_x_front; }

    public int getPosYFront() { return pos_y_front; }

    public int getPosXBack() { return pos_x_back; }

    public int getPosYBack() { return pos_y_back; }

    public void setPivot(int x, int y){
        pivot_x = x;
        pivot_y = y;
        updateTexturesPosition();
    }

    private void updateTexturesPosition() {
        setPositionBuildMenu();

        if(isBuilt)
        setTowerTexturesPosition();
    }

    private void setTowerTexturesPosition() {
        setPositionPillar();
        setPositionFront();
        setPositionBack();
    }


    private void setPositionBuildMenu() {
        pos_x_build_menu = pivot_x - build_menu.getRegionWidth()/2;
        pos_y_build_menu = pivot_y - build_menu.getRegionHeight()/2;
    }

    private void setPositionPillar() {
        //It's const offset in soil tower
        int offset_y = 28;
        int offset_x = 6;

        pos_x_pillar = pivot_x - pillar.getRegionWidth()/2 + offset_x;
        pos_y_pillar = pivot_y - pillar.getRegionHeight()/2 + offset_y;
    }

    private void setPositionFront() {
        int offset_y = 16;
        int offset_x = 3;

        pos_x_front = pivot_x - front.getRegionWidth()/2 + offset_x;
        pos_y_front = pivot_y - front.getRegionHeight()/2 + offset_y;
    }

    private void setPositionBack() {
        //It's const offset in soil tower
        int offset_y = 33;
        int offset_x = 3;

        pos_x_back = pivot_x - back.getRegionWidth()/2 + offset_x;
        pos_y_back = pivot_y - back.getRegionHeight()/2 + offset_y;
    }

}
