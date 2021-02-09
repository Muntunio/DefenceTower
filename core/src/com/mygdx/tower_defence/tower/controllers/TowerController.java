package com.mygdx.tower_defence.tower.controllers;

import com.mygdx.tower_defence.tower.models.TowerModel;
import com.mygdx.tower_defence.tower.views.TowerView;

public class TowerController {

    private TowerModel model;
    private TowerView view;

    public TowerController(TowerModel model) {
        this.model = model;
        this.view = new TowerView(model);
    }



    public void checkMenuTouchDown(float x, float y){
        if(x>model.getPosXBuildMenu()+5 && x<model.getPosXBuildMenu()+50){
            if(y>model.getPosYBuildMenu()+10 && y<model.getPosYBuildMenu()+44){
                System.out.println("Kula");
                return;
            }
            if(y>model.getPosYBuildMenu()+90 && y<model.getPosYBuildMenu()+120){
                System.out.println("Skala");
                return;
            }
        }
        if(x>model.getPosXBuildMenu()+100 && x<model.getPosXBuildMenu()+145){
            if(y>model.getPosYBuildMenu()+10 && y<model.getPosYBuildMenu()+44){
                System.out.println("Ogien");
                return;
            }
            if(y>model.getPosYBuildMenu()+90 && y<model.getPosYBuildMenu()+120){
                model.updateLevel();
            }
        }
    }

    //Getters and Setters
    public void setPivot(int x, int y){model.setPivot(x,y);}

    public void setActive(){model.setActiveBuildMenu();}

    public void resetActive(){model.resetActiveBuildMenu();}

    public TowerView getView() { return view; }
}
