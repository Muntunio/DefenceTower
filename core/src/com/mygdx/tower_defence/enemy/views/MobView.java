package com.mygdx.tower_defence.enemy.views;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.tower_defence.enemy.models.AbstractEnemyModel;
import com.mygdx.tower_defence.tower.controllers.AbstractTowerController;

public class MobView extends Actor {

    private AbstractEnemyModel model;

    public MobView(AbstractEnemyModel model) {
        this.model = model;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(model.getCurrent_health(),model.getPosXHealth(),model.getPosYHealth(),
                model.getWidthHealth(),model.getHeightHealth());
        batch.draw(model.getCurrent_frame(), model.getPosX(), model.getPosY(),
                model.getWidth(), model.getHeight());



    }

    @Override
    public void act(float delta) {
        super.act(delta);
        model.updateStateTime(delta);
    }

    //TODO not here
    public AbstractEnemyModel getModel(){ return model;}
}
