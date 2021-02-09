package com.mygdx.tower_defence.map.views;


import com.mygdx.tower_defence.MainTowerDefence;
import com.mygdx.tower_defence.map.model.MapModel;
import com.mygdx.tower_defence.screens.AbstractScreen;
import com.mygdx.tower_defence.tower.controllers.AbstractTowerController;


public class MapView extends AbstractScreen {

    public MapModel map_model;


    public MapView(MainTowerDefence game, MapModel map_model) {
        super(game);
        this.map_model = map_model;

        stage.addActor(map_model.getMapImage());
        addTowerControllers();
    }

    private void addTowerControllers() {
        for(AbstractTowerController build : map_model.getBuildControllers()){
            stage.addActor(build.getView());
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        updateStage();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();

    }

    private void updateStage() {
        stage.act();

        //Check is some enemy to add
        if(map_model.isEnemyToStage())
            addEnemiesToStage();

        if(map_model.isIsTowerToAdd()) {
            stage.addActor(map_model.getBuildControllers()
                    [map_model.getTowerWidthActiveMenu()].getView());

            map_model.resetIsTowerToAdd();
        }

    }

    private void addEnemiesToStage() {
        for( int i = map_model.getFirstIndexToAdd();
             i<map_model.getEnemyList().size(); i++ )
        {
            stage.addActor(map_model.getEnemyList().get(i));
        }
        map_model.resetEnemyToStageFlag();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
