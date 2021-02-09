package com.mygdx.tower_defence.tower.views;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.tower_defence.tower.models.*;
import com.mygdx.tower_defence.tower.textures.TowerType;

public class TowerView2 extends Actor {
    TowerModelAbstract model;

    public TowerView2(TowerModelAbstract model) {
        this.model = model;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(model.getBackTexture(), model.getXPositionBack(), model.getYPositionBack());

        if(model.getType() != TowerType.STONE) {
            batch.draw(model.getPillarTexture(), model.getXPositionPillar(), model.getYPositionPillar());
            batch.draw(model.getFrontTexture(), model.getXPositionFront(), model.getYPositionFront());
        }else{
            batch.draw(model.getFrontTexture(), model.getXPositionFront(), model.getYPositionFront());
            batch.draw(model.getPillarTexture(), model.getXPositionPillar(), model.getYPositionPillar());
        }

        if( model.isBullet() )
            batch.draw(model.getBulletTexture(), model.getXPositionBullet(), model.getYPositionBullet());

        if( model.isActiveMenu() )
            batch.draw(model.getBuildMenu(), model.getXPositionBuildMenu(), model.getYPositionBuildMenu());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        model.update(delta);
    }


}
