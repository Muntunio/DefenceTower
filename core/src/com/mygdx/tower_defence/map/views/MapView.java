package com.mygdx.tower_defence.map.views;


import com.mygdx.tower_defence.MainTowerDefence;
import com.mygdx.tower_defence.bullet.view.BulletView;
import com.mygdx.tower_defence.map.model.MapModel;
import com.mygdx.tower_defence.screens.AbstractScreen;
import com.mygdx.tower_defence.tower.controllers.TowerController;
import com.mygdx.tower_defence.tower.views.TowerView2;


public class MapView extends AbstractScreen {

    public MapModel map_model;

    private BulletView a;
    private TowerView2 b;
    private TestingActor c;

    public MapView(MainTowerDefence game, MapModel map_model) {
        super(game);
        this.map_model = map_model;

        stage.addActor(map_model.getMapImage());
        //addTowersToStage();


//        a = new BulletView();
//        stage.addActor(a);
//
//        b = new TowerView2();
//        stage.addActor(b);
//
//        c = new TestingActor();
//        stage.addActor(c);

    }

    private void addTowersToStage() {
        for(TowerController tower : map_model.getTowers()){
            stage.addActor(tower.getView());
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
