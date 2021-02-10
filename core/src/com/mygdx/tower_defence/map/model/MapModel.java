package com.mygdx.tower_defence.map.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.tower_defence.enemy.EnemyType;
import com.mygdx.tower_defence.enemy.models.AbstractEnemyModel;
import com.mygdx.tower_defence.enemy.models.GreenMobModel;
import com.mygdx.tower_defence.enemy.models.MeshMobModel;
import com.mygdx.tower_defence.enemy.models.RedMobModel;
import com.mygdx.tower_defence.enemy.views.MobView;
import com.mygdx.tower_defence.tower.controllers.AbstractTowerController;
import com.mygdx.tower_defence.tower.controllers.BuildController;
import com.mygdx.tower_defence.tower.controllers.TowerController2;
import com.mygdx.tower_defence.tower.models.BuildModel;
import com.mygdx.tower_defence.tower.textures.TowerType;


import java.awt.*;
import java.util.LinkedList;
import java.util.List;


public class MapModel {

    //Assets manager
    private MapAssetModel assets;

    //...
    private Image map_background;

    //Enemies Actor
    private List<MobView> enemyList;
    private boolean is_enemy_to_stage;
    private int first_index_to_add;

    //Variables to set adding mob group
    private Thread thread_add_enemy;

    //...
    private final ReadFileMapSetting map_settings;

    //Towers
    private AbstractTowerController[] build_controller;
    private boolean active_controller;
    private int number_of_active_controller;

    private boolean is_tower_to_add;

    //BuildMenu




    public MapModel(String map_name) {
        map_settings = new ReadFileMapSetting("core/assets/map/"+map_name+".txt");
        loadAssets();

        AbstractEnemyModel.setPathPoints(map_settings.getPathPoints());

        init();
        letGoMobWave();
        initBuildController();
        active_controller = false;
        is_tower_to_add = false;
    }


    private void loadAssets() {
        assets = new MapAssetModel();
        assets.load();
        assets.manager.finishLoading();
    }

    private void init() {
        map_background = new Image(assets.manager.get(MapAssetModel.map));
        enemyList = new LinkedList<>();
    }


    private void initBuildController() {
        BuildModel.loadTexture();

        build_controller = new AbstractTowerController[map_settings.getTowerPivot().length];

        for(int i=0; i<map_settings.getTowerPivot().length; ++i) {
            build_controller[i] = new BuildController(this);
            build_controller[i].setPivot(map_settings.getTowerPivot()[i].x,
                    map_settings.getTowerPivot()[i].y);
        }
    }

    private void addEnemy(EnemyType type){
        switch (type){
            case RED:
                enemyList.add(new MobView(new RedMobModel(assets)));
                break;
            case MESH:
                enemyList.add(new MobView(new MeshMobModel(assets)));
                break;
            case GREEN:
                enemyList.add(new MobView(new GreenMobModel(assets)));
                break;
            default:
                return;
        }

        if( !is_enemy_to_stage )
            first_index_to_add = enemyList.size()-1;

        is_enemy_to_stage = true;
    }

    private void addEnemyGroup(final EnemyType type, final int count, final int delay_ms){

        thread_add_enemy = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<count; i++){

                    addEnemy(type);

                    try {
                        Thread.sleep(delay_ms);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                letGoMobWave();
            }
        });
        thread_add_enemy.start();
    }

    private void letGoMobWave(){
        if(map_settings.getEnemyWaveQueue().isEmpty())
            return;

        final EnemyWave wave = map_settings.getEnemyWaveQueue().poll();
        if (wave == null)
            return;
        thread_add_enemy = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(wave.getDelayBetweenPreWave());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                addEnemyGroup(wave.getEnemyType(),wave.getCountOfMob(),wave.getDelayBetweenMobs());
            }
        });
        thread_add_enemy.start();
    }

    public void addNewTower(AbstractTowerController towerController){
        build_controller[number_of_active_controller] = towerController;


        build_controller[number_of_active_controller]
                .setPivot(map_settings.getTowerPivot()[number_of_active_controller].x,
                map_settings.getTowerPivot()[number_of_active_controller].y);

        is_tower_to_add = true;
    }


    public void checkEnemy(float delta) {
        enemyIsInTowerRange();
    }

    private void enemyIsInTowerRange() {
        for(AbstractTowerController tower : build_controller){
            if( tower.getTowerType() != TowerType.BUILD){
                TowerController2 tower2 = (TowerController2)tower;
                int range = tower2.getModel().getRange();
                AbstractEnemyModel mob = enemyList.get(0).getModel();

                if( Math.pow(tower2.getModel().getXPositionPillar()-mob.getPosX(),2) +
                        Math.pow(tower2.getModel().getYPositionPillar()-mob.getPosY(),2) < Math.pow(range,2) ){

                    tower2.getModel().isEnemyInRange(mob);
                    System.out.println("JEST");
                }
            }
        }
    }


    //Getters and Setters
    public Image getMapImage(){ return map_background; }

    public List<MobView> getEnemyList(){ return enemyList; }

    public boolean isEnemyToStage() { return is_enemy_to_stage; }

    public int getFirstIndexToAdd() { return first_index_to_add; }

    public Point[] getTowerPivot() { return map_settings.getTowerPivot(); }

    public void resetEnemyToStageFlag() { is_enemy_to_stage = false; }


    public AbstractTowerController[] getBuildControllers() { return build_controller; }

    public boolean isIsActiveTower() { return active_controller; }

    public int getTowerWidthActiveMenu() { return number_of_active_controller; }

    public void setIsActiveTower(boolean is_active_tower) { this.active_controller = is_active_tower; }

    public void setTowerWithActiveMenu(int tower_with_active_menu) { this.number_of_active_controller = tower_with_active_menu; }


    public boolean isIsTowerToAdd() { return is_tower_to_add; }

    public void resetIsTowerToAdd() { this.is_tower_to_add = false; }


}
