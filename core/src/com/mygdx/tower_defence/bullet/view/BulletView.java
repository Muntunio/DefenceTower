package com.mygdx.tower_defence.bullet.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.tower_defence.bullet.animation.BulletAnimationCache;
import com.mygdx.tower_defence.bullet.animation.BulletAnimationType;
import com.mygdx.tower_defence.bullet.model.BulletModel;

public class BulletView extends Actor {

    public BulletModel model;
    public BulletView(BulletAnimationType type) {
        model = new BulletModel(type);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion b = BulletAnimationCache.getAnimation(model.getType()).getTexture(model.getState_time());
        batch.draw(b,model.getPositionX(),model.getPositionY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        model.update(delta);
    }
}
