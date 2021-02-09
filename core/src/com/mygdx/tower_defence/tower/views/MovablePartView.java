package com.mygdx.tower_defence.tower.views;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.tower_defence.tower.models.movable.MovablePartModel;
import com.mygdx.tower_defence.tower.models.movable.StoneMovablePartModel;

public class MovablePartView extends Actor {

    MovablePartModel model;

    public MovablePartView() {

        model = new StoneMovablePartModel(100,100);
        model.setVerticalPivot(200);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(model.getFrontTexture(), model.getXPositionFront(), model.getYPositionFront());
        batch.draw(model.getBackTexture(), model.getXPositionBack(), model.getYPositionBack());

        if( model.isBullet() )
            batch.draw(model.getBulletTexture(), model.getXPositionBullet(), model.getYPositionBullet());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        model.update(delta);
    }
}
