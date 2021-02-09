package com.mygdx.tower_defence.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.tower_defence.MainTowerDefence;
import com.mygdx.tower_defence.enemy.models.AbstractEnemyModel;
import com.mygdx.tower_defence.enemy.models.GreenMobModel;
import com.mygdx.tower_defence.enemy.views.MobView;
import com.mygdx.tower_defence.enemy.models.RedMobModel;
import com.mygdx.tower_defence.map.model.MapAssetModel;

import java.awt.*;


public class MapScreen extends AbstractScreen{

    MapAssetModel asset_model;

    private Image background_image;

    Point [] points = new Point[22];

    //TODO to this better
    RedMobModel model_red;
    MobView view_red;
    GreenMobModel model_green;
    MobView view_green;


    public MapScreen(MainTowerDefence game) {
        super(game);
        loadPoints();
        loadAssets();
        init();
        //TODO do this not here

        AbstractEnemyModel.setPathPoints(points);

        model_red = new RedMobModel(asset_model);
        view_red = new MobView(model_red);
        stage.addActor(view_red);
        model_green = new GreenMobModel(asset_model);
        view_green = new MobView(model_green);
        stage.addActor(view_green);

    }

    private void loadPoints() {
        //TODO Load form file
        points[0] = new Point(1656, MainTowerDefence.HEIGHT -281);
        points[1] = new Point(1555, MainTowerDefence.HEIGHT -282);
        points[2] = new Point(1420, MainTowerDefence.HEIGHT -342);
        points[3] = new Point(1175,MainTowerDefence.HEIGHT-343);
        points[4] = new Point(1077,MainTowerDefence.HEIGHT-315);
        points[5] = new Point(1047,MainTowerDefence.HEIGHT-226);
        points[6] = new Point(1028,MainTowerDefence.HEIGHT-180);
        points[7] = new Point(983,MainTowerDefence.HEIGHT-159);
        points[8] = new Point(848,MainTowerDefence.HEIGHT-181);
        points[9] = new Point(825,MainTowerDefence.HEIGHT-281);
        points[10] = new Point(753,MainTowerDefence.HEIGHT-352);
        points[11] = new Point(612,MainTowerDefence.HEIGHT-354);
        points[12] = new Point(516,MainTowerDefence.HEIGHT-431);
        points[13] = new Point(598,MainTowerDefence.HEIGHT-510);
        points[14] = new Point(720,MainTowerDefence.HEIGHT-551);
        points[15] = new Point(913,MainTowerDefence.HEIGHT-513);
        points[16] = new Point(971,MainTowerDefence.HEIGHT-556);
        points[17] = new Point(952,MainTowerDefence.HEIGHT-619);
        points[18] = new Point(895,MainTowerDefence.HEIGHT-690);
        points[19] = new Point(755,MainTowerDefence.HEIGHT-726);
        points[20] = new Point(739,MainTowerDefence.HEIGHT-849);
        points[21] = new Point(739,MainTowerDefence.HEIGHT-950);
    }

    private void loadAssets() {
        asset_model = new MapAssetModel();
        asset_model.load();
        asset_model.manager.finishLoading();
    }


    private void init() {
        background_image = new Image(asset_model.manager.get(MapAssetModel.map));
        stage.addActor(background_image);
    }



    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();

    }

    private void update() {
        stage.act();
    }

    @Override
    public void dispose() {
        super.dispose();
    }


}
