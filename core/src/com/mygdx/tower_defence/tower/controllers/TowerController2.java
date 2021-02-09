package com.mygdx.tower_defence.tower.controllers;

import com.mygdx.tower_defence.map.model.MapModel;
import com.mygdx.tower_defence.tower.models.*;
import com.mygdx.tower_defence.tower.textures.TowerType;
import com.mygdx.tower_defence.tower.views.TowerView2;

import java.nio.channels.FileChannel;

public class TowerController2 extends AbstractTowerController {
    private TowerModelAbstract model;
    private TowerView2 view;

    public TowerController2(MapModel map_model, TowerType type) {
        super(map_model);
        model = chooseTowerType(type);
        view = new TowerView2(model);
    }

    private TowerModelAbstract chooseTowerType(TowerType type) {
        switch (type){
            case DIRT:
                return new DirtTowerModel();
            case FIRE:
                return new FireTowerModel();
            case IRON:
                return new IronTowerModel();
            case STONE:
                return new StoneTowerModel();
            default:
                throw new IllegalArgumentException("Unknown tower type"+type);
        }
    }

    public TowerModelAbstract getModel() { return model; }

    public void setPivot(int x, int y) { model.setPivot(x,y); }

    public TowerView2 getView() { return view; }

    public void setIsActiveBuildMenu(boolean is_active_build_menu) {
        model.setActiveMenu(is_active_build_menu);
    }

    @Override
    public void checkMenuTouchDown(float x, float y) {
        if(x>model.getXPositionBuildMenu()+5 && x<model.getXPositionBuildMenu()+50){
            if(y>model.getYPositionBuildMenu()+10 && y<model.getYPositionBuildMenu()+44){
                //System.out.println("Kula");
                return;
            }
            if(y>model.getYPositionBuildMenu()+90 && y<model.getYPositionBuildMenu()+120){
                model.destroy();
                return;
            }
        }
        if(x>model.getXPositionBuildMenu()+100 && x<model.getXPositionBuildMenu()+145){
            if(y>model.getYPositionBuildMenu()+10 && y<model.getYPositionBuildMenu()+44){
                //System.out.println("Ogien");
                return;
            }
            if(y>model.getYPositionBuildMenu()+90 && y<model.getYPositionBuildMenu()+120){
                model.upgrade();
                return;
            }
        }
    }
}
