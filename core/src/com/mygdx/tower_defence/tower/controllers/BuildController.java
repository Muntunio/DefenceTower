package com.mygdx.tower_defence.tower.controllers;

import com.mygdx.tower_defence.map.model.MapModel;
import com.mygdx.tower_defence.tower.models.BuildModel;
import com.mygdx.tower_defence.tower.textures.TowerType;
import com.mygdx.tower_defence.tower.views.BuildView;

public class BuildController extends AbstractTowerController {
    private BuildModel model;
    private BuildView view;

    public BuildController(MapModel map_model) {
        super(map_model);
        model = new BuildModel();
        view = new BuildView(model);
    }

    @Override
    public BuildView getView() { return view; }

    public void setIsActiveBuildMenu(boolean is_active_build_menu) {
        model.setIsActiveBuildMenu(is_active_build_menu);
    }

    @Override
    public void checkMenuTouchDown(float x, float y) {
        if(x>model.getXPosition()+5 && x<model.getXPosition()+50){
            if(y>model.getYPosition()+10 && y<model.getYPosition()+44){
                createTower(TowerType.IRON);
                return;
            }
            if(y>model.getYPosition()+90 && y<model.getYPosition()+130){
                createTower(TowerType.STONE);
                return;
            }
        }
        if(x>model.getXPosition()+100 && x<model.getXPosition()+145){
            if(y>model.getYPosition()+10 && y<model.getYPosition()+44){
                createTower(TowerType.FIRE);
                return;
            }
            if(y>model.getYPosition()+90 && y<model.getYPosition()+130){
                createTower(TowerType.DIRT);
            }
        }
    }

    private void createTower(TowerType type) {
        model.setToRemove(true);
        map_model.addNewTower( new TowerController2(map_model, type) );
    }

    @Override
    public void setPivot(int x, int y) { model.setPivot(x,y);}
}
