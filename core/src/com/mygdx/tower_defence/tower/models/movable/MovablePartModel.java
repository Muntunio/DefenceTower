package com.mygdx.tower_defence.tower.models.movable;


import com.badlogic.gdx.graphics.g2d.TextureRegion;


public abstract class MovablePartModel {
    protected int moving_distance;

    protected int x_position;
    protected int y_position;
    protected int level;
    protected float state_time;


    //back
    protected int x_position_back;
    protected int y_position_back;
    //front
    protected int x_position_front;
    protected int y_position_front;
    //bullet
    protected int x_position_bullet;
    protected int y_position_bullet;

    //animation
    protected int speed;
    protected boolean directionUp;
    protected float delay;
    protected float last_state;
    protected boolean is_waiting;
    protected boolean active;



    public MovablePartModel() {
        this.x_position = 0;
        this.y_position = 0;
        setDefault();
        recalculatePosition();
    }

    public MovablePartModel(int x_position, int y_position) {
        this.x_position = x_position;
        this.y_position = y_position;
        setDefault();
        recalculatePosition();

    }

    private void setDefault() {
        level = 0;
        state_time = 0.0f;
        speed = 1;
        directionUp = true;

        delay = 3.0f;
        last_state = 0.0f;
        is_waiting = true;
        active = false;
    }

    protected abstract void recalculatePosition();

    protected abstract void recalculateBulletPosition();

    public void update(float delta) {
        state_time += delta;
        if(!is_waiting){
            move();
        }
        else
            waiting();
    }

    protected void waiting(){
        if(state_time-last_state > delay) {
            is_waiting = false;
        }
    }

    private void move() {
        if( directionUp ){
            moveUp();
            if(y_position_back > y_position + moving_distance){
                directionUp = false;
            }
        }
        else{
            if(y_position_front < y_position){
                if(active){
                    directionUp = true;
                    is_waiting = true;
                    last_state = state_time;
                }
            }else
                moveDown();
        }
    }


    private void moveUp() {
        y_position_back+=speed;
        y_position_front+=speed;
        y_position_bullet+=speed;
    }

    private void moveDown() {
        y_position_back-=speed;
        y_position_front-=speed;
        y_position_bullet-=speed;
    }

    public void setPosition(int x, int y){
        this.x_position = x;
        this.y_position = y;
        recalculatePosition();
    }

    public void setVerticalPivot(int x){
        x_position = x - getBackTexture().getRegionWidth()/2;
        recalculatePosition();
    }

    public void setLevel(int level) { this.level = level; }

    public abstract TextureRegion getBackTexture();

    public abstract TextureRegion getFrontTexture();

    public abstract TextureRegion getBulletTexture();

    public boolean isBullet() { return directionUp; }

    public int getXPositionBack() { return x_position_back; }

    public int getYPositionBack() { return y_position_back; }

    public int getXPositionFront() { return x_position_front; }

    public int getYPositionFront() { return y_position_front; }

    public int getXPositionBullet() { return x_position_bullet; }

    public int getYPositionBullet() { return y_position_bullet; }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public boolean isIsWaiting() { return is_waiting; }
}
