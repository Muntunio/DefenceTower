package com.mygdx.tower_defence.tower.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.tower_defence.tower.models.movable.MovablePartModel;

public abstract class TowerModelAbstract {
    protected MovablePartModel movable_model;
    protected int level;
    private int x_position;
    private int y_position;

    public TowerModelAbstract() {
        setModel();
        level = 2;
        movable_model.setLevel(level);
    }

    protected abstract void setModel();

    public void setPosition(int x, int y){
        x_position = x;
        y_position = y;
        movable_model.setPosition(x_position,y_position);
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
    public void update(float delta){
        movable_model.update(delta);
    }
    public int getLevel(){ return level; }

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
