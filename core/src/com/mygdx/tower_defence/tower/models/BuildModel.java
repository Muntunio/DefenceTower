package com.mygdx.tower_defence.tower.models;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BuildModel {
    static TextureRegion menu;

    public static void loadTexture(){
        AssetManager manager = new AssetManager();
        manager.load("tower/build_menu.txt", TextureAtlas.class);
        manager.finishLoading();
        menu = manager.get("tower/build_menu.txt", TextureAtlas.class).findRegion("build_menu");
    }


    private int x_position;
    private int y_position;
    private boolean is_active_build_menu;
    private boolean to_remove;

    public BuildModel() {
        is_active_build_menu = false;
        to_remove = false;
    }

    public void setPivot(int x, int y){
        x_position = x - menu.getRegionWidth()/2;
        y_position = y - menu.getRegionHeight()/2;

        is_active_build_menu = false;
    }

    public static TextureRegion getMenu() { return menu; }

    public int getXPosition() { return x_position; }

    public int getYPosition() { return y_position; }

    public boolean isIsActiveBuildMenu() { return is_active_build_menu; }

    public void setIsActiveBuildMenu(boolean is_active_build_menu) {
        this.is_active_build_menu = is_active_build_menu;
    }

    public void setToRemove(boolean to_remove) { this.to_remove = to_remove; }

    public boolean isToRemove() { return to_remove; }
}
