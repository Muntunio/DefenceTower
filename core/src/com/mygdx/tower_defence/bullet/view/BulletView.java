package com.mygdx.tower_defence.bullet.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.tower_defence.bullet.animation.BulletAnimationCache;
import com.mygdx.tower_defence.bullet.animation.BulletAnimationType;

public class BulletView extends Actor {

    private float a=0;
    public BulletView() {
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion b = BulletAnimationCache.getAnimation(BulletAnimationType.IRON).getTexture(a);
        batch.draw(b,100,100);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        a+=delta;
    }
}
