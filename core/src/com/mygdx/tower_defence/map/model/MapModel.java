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

    //BuildMenu




    public MapModel(String map_name) {
        map_settings = new ReadFileMapSetting("core/assets/map/"+map_name+".txt");
        loadAssets();

        AbstractEnemyModel.setPathPoints(map_settings.getPathPoints());

        init();
        letGoMobWave();
 //       initTowers();
        initBuildMenu();
        active_controller = false;
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

//    private void initTowers() {
//        towers = new TowerController[map_settings.getTowerPivot().length];
//
//        for(int i=0; i<map_settings.getTowerPivot().length; ++i) {
//            towers[i] = new TowerController(new TowerModel(assets));
//            towers[i].setPivot(map_settings.getTowerPivot()[i].x,
//                    map_settings.getTowerPivot()[i].y);
//        }
//    }

    private void initBuildMenu() {
        BuildModel.loadTexture();

        build_controller = new AbstractTowerController[map_settings.getTowerPivot().length];

        for(int i=0; i<map_settings.getTowerPivot().length; ++i) {
            build_controller[i] = new BuildController();
            build_controller[i].setPivot(map_settings.getTowerPivot()[i].x,
                    map_settings.getTowerPivot()[i].y);
        }

        build_controller[1] = new TowerController2(TowerType.DIRT);
        build_controller[1].setPivot(map_settings.getTowerPivot()[1].x,
                map_settings.getTowerPivot()[1].y);
        build_controller[2] = new TowerController2(TowerType.FIRE);
        build_controller[2].setPivot(map_settings.getTowerPivot()[2].x,
                map_settings.getTowerPivot()[2].y);
        build_controller[3] = new TowerController2(TowerType.STONE);
        build_controller[3].setPivot(map_settings.getTowerPivot()[3].x,
                map_settings.getTowerPivot()[3].y);
        build_controller[4] = new TowerController2(TowerType.IRON);
        build_controller[4].setPivot(map_settings.getTowerPivot()[4].x,
                map_settings.getTowerPivot()[4].y);
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
}
