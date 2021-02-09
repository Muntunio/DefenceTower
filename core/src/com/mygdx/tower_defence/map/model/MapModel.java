package com.mygdx.tower_defence.map.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.tower_defence.enemy.EnemyType;
import com.mygdx.tower_defence.enemy.models.AbstractEnemyModel;
import com.mygdx.tower_defence.enemy.models.GreenMobModel;
import com.mygdx.tower_defence.enemy.models.MeshMobModel;
import com.mygdx.tower_defence.enemy.models.RedMobModel;
import com.mygdx.tower_defence.enemy.views.MobView;
import com.mygdx.tower_defence.tower.controllers.TowerController;
import com.mygdx.tower_defence.tower.models.TowerModel;

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
    private TowerController[] towers;
    private boolean is_active_tower;
    private int tower_with_active_menu;


    public MapModel(String map_name) {
        map_settings = new ReadFileMapSetting("core/assets/map/"+map_name+".txt");
        loadAssets();

        AbstractEnemyModel.setPathPoints(map_settings.getPathPoints());

        init();
        letGoMobWave();
        initTowers();
        is_active_tower = false;
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

    private void initTowers() {
        towers = new TowerController[map_settings.getTowerPivot().length];

        for(int i=0; i<map_settings.getTowerPivot().length; ++i) {
            towers[i] = new TowerController(new TowerModel(assets));
            towers[i].setPivot(map_settings.getTowerPivot()[i].x,
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


    //Getters and Setters
    public Image getMapImage(){ return map_background; }

    public List<MobView> getEnemyList(){ return enemyList; }

    public boolean isEnemyToStage() { return is_enemy_to_stage; }

    public int getFirstIndexToAdd() { return first_index_to_add; }

    public Point[] getTowerPivot() { return map_settings.getTowerPivot(); }

    public void resetEnemyToStageFlag() { is_enemy_to_stage = false; }

    public TowerController[] getTowers() {return towers;}

    public boolean isIsActiveTower() { return is_active_tower; }

    public int getTowerWidthActiveMenu() { return tower_with_active_menu; }

    public void setIsActiveTower(boolean is_active_tower) { this.is_active_tower = is_active_tower; }

    public void setTowerWithActiveMenu(int tower_with_active_menu) { this.tower_with_active_menu = tower_with_active_menu; }
}