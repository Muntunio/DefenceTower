package com.mygdx.tower_defence.tower.views;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.tower_defence.tower.models.TowerModel;




public class TowerView extends Actor {

    private TowerModel model;

    public TowerView(TowerModel model) { this.model = model; }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(model.isBuilt()) {
            batch.draw(model.getBack(), model.getPosXBack(), model.getPosYBack());
            batch.draw(model.getPillar(), model.getPosXPillar(), model.getPosYPillar());
            batch.draw(model.getFront(), model.getPosXFront(), model.getPosYFront());
        }
        if(model.isActiveBuildMenu())
            batch.draw(model.getBuildMenu(),model.getPosXBuildMenu(),model.getPosYBuildMenu());

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        update(delta);
    }

    private void update(float delta) {
        model.animationTowerFire(delta);
    }


}
