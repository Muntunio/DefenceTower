package com.mygdx.tower_defence.bullet.model;

import com.mygdx.tower_defence.bullet.animation.BulletAnimationType;

import java.awt.*;

public class BulletModel {

    private  BulletAnimationType type;
    private double coef[];
    private int position_x;
    private int position_y;
    private int end_x;
    private int end_y;
    private int dx = 1;
    private float state_time;

    public BulletModel(BulletAnimationType type) {
        this.type = type;
        state_time = 0.0f;
    }

    public void giveCoordinate(int start_x, int start_y, int end_x, int end_y){
        position_x = start_x;
        position_y = start_y;

        this.end_x = end_x;
        this.end_y = end_y;

        int between_x = (start_x + end_x)/2;
        int between_y = start_y + 100;

        coef = CoefficientQuadratic.threePoint(new Point(start_x,start_y),new Point(between_x,between_y),new Point(end_x,end_y));
    }

    public void setPosition(int x, int y){
        position_x = x;
        position_y = y;
    }

    public void update(float delta){
        state_time += delta;
//
//        if(position_x > end_x)
//            position_x -= dx;
//        else
//            position_x += dx;
//
//
//        position_y = (int)(coef[0]*position_x*position_x+coef[1]*position_x+coef[0]);
    }

    public BulletAnimationType getType() { return type; }

    public int getPositionX() { return position_x; }

    public int getPositionY() { return position_y; }

    public float getState_time() { return state_time; }
}

