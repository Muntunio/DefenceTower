package com.mygdx.tower_defence.tower.views;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.tower_defence.tower.models.BuildModel;

public class BuildView extends Actor {
    private final BuildModel model;

    public BuildView(BuildModel model) {
        this.model = model;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if(model.isIsActiveBuildMenu())
            batch.draw(BuildModel.getMenu(),model.getXPosition(),model.getYPosition());
    }

    @Override
    public void act(float delta) {

        super.act(delta);

        if(model.isToRemove())
            this.remove();
    }
}
