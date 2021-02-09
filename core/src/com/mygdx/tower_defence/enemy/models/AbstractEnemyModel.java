package com.mygdx.tower_defence.enemy.models;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.tower_defence.map.model.MapAssetModel;

import java.awt.*;

public abstract class AbstractEnemyModel {

    // Field to display (Textures and Animation)
    protected Animation<TextureRegion> walk_animation;
    protected TextureRegion current_frame;

    protected TextureAtlas health_bar_atlas;
    protected TextureRegion current_health;

    // Scale
    private float scale;

    //Position and size
    private int pos_y;
    private int pos_x;

    private  float width;
    private  float height;

    //Position and size health_bar
    private int pos_y_health;
    private int pos_x_health;

    private float width_health;
    private float height_health;

    //Enemy attributes
    protected int max_hp;
    protected int hp;
    protected float speed;

    //Settings to move
    public static void  setPathPoints(Point [] path){
        path_points = path;
    }
    static private Point [] path_points;
    private int path_next_step;

    private double step_angle;
    private double waiting_step_x;
    private double waiting_step_y;

    protected boolean isFlipped;
    protected float state_time;


    public AbstractEnemyModel(MapAssetModel manager){
        state_time = 0.0f;
        scale = 1.0f;
        initMoveSettings();

        this.health_bar_atlas = manager.manager.get(MapAssetModel.health_bar);
        current_health = health_bar_atlas.createSprite("100_health_bar");
        pullTexturesFromManager(manager);
        updateSize();
    }

    protected abstract void pullTexturesFromManager(MapAssetModel manager);

    private void initMoveSettings() {
        path_next_step = 1;

        step_angle = 0.0;
        waiting_step_x = 0.0;
        waiting_step_y = 0.0;

        isFlipped = false;

        calculateStepAngle();
        setPosition(path_points[0].x,path_points[0].y);
    }

    protected void updateSize(){
        width = current_frame.getRegionWidth()*scale;
        height = current_frame.getRegionHeight()*scale;

        float scale_to_texture = 0.35f;
        float divisor = width/(float)current_health.getRegionWidth()*scale_to_texture;

        width_health = current_health.getRegionWidth()*divisor;
        height_health = current_health.getRegionHeight()*divisor+0.2f;

        updatePositionHealth();
    }

    protected void updatePositionHealth(){
        pos_x_health = pos_x + (int)((width-width_health)/1.8);
        pos_y_health = pos_y + (int)height;
    }

    protected void updateHealthTexture(){
        float percentage_hp = hp/(float)max_hp*10;
        int hp_int = Math.round(percentage_hp)*10;
        current_health = health_bar_atlas.createSprite(hp_int+"_health_bar");
    }

    public void updateStateTime(float dt){
        state_time +=  dt;
        move();
        updateFrame();
    }

    private void move() {
        if( step_angle > 0 && step_angle <= Math.PI ){
            if( step_angle <= Math.PI/2){
                if( getPosY() > path_points[path_next_step].y ||
                        getPosX() > path_points[path_next_step].x )
                    nextStep();
            }
            else {
                if( getPosY() > path_points[path_next_step].y ||
                        getPosX() < path_points[path_next_step].x )
                    nextStep();
            }
        }
        else{
            if( step_angle <= 3.0/2.0*Math.PI){
                if( getPosY() < path_points[path_next_step].y ||
                        getPosX() < path_points[path_next_step].x )
                    nextStep();
            }
            else{
                if( getPosY() < path_points[path_next_step].y ||
                        getPosX() > path_points[path_next_step].x )
                    nextStep();
            }
        }

        waiting_step_x += speed*Math.cos(step_angle);
        waiting_step_y += speed*Math.sin(step_angle);

        setPosition(getPosX()+(int)waiting_step_x,
                getPosY()+(int)waiting_step_y);

        waiting_step_x = waiting_step_x%1;
        waiting_step_y = waiting_step_y%1;
    }



    private void nextStep(){
        if(path_next_step == path_points.length-1)
            return;
        ++path_next_step;
        calculateStepAngle();
    }

    private void calculateStepAngle() {
        step_angle = Math.atan2(path_points[path_next_step].y - path_points[path_next_step-1].y,
                path_points[path_next_step].x - path_points[path_next_step-1].x);

        if( step_angle < 0 )
            step_angle = 2*Math.PI + step_angle;

        isFlipped = step_angle > Math.PI / 2 && step_angle < 3.0 / 2.0 * Math.PI;
    }

    protected void updateFrame(){
        current_frame = walk_animation.getKeyFrame(state_time);
        if(current_frame.isFlipX() != isFlipped)
            current_frame.flip(true,false);
    }


    //Getters and Setters
    public void setScale(float scale){
        this.scale = scale;
        updateSize();
    }

    public void setPosition(int pos_x, int pos_y){
        this.pos_x = pos_x;
        this.pos_y = pos_y;

        updatePositionHealth();
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getPosYHealth() {
        return pos_y_health;
    }

    public int getPosXHealth() {
        return pos_x_health;
    }

    public float getWidthHealth() {
        return width_health;
    }

    public float getHeightHealth() {
        return height_health;
    }

    public int getPosY() { return pos_y; }

    public int getPosX() { return pos_x; }

    public float getScale() {
        return scale;
    }

    public int getMaxHp() {
        return max_hp;
    }

    public int getHp() {
        return hp;
    }

    public float getSpeed() { return speed; }

    public TextureRegion getCurrent_frame() {
        return current_frame;
    }

    public TextureRegion getCurrent_health() {
        return current_health;
    }
}
