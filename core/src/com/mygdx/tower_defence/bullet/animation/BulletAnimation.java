package com.mygdx.tower_defence.bullet.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class BulletAnimation {
    private Animation<TextureRegion> animation;

    public BulletAnimation() {
        TextureAtlas bullet = loadAsset();
        createAnimation(bullet);
    }


    protected abstract TextureAtlas loadAsset();

    protected void createAnimation(TextureAtlas bullet){
        animation = new Animation<TextureRegion>(0.13f,
                bullet.findRegions(
                        "bullet"), Animation.PlayMode.LOOP);
    }

    public TextureRegion getTexture(float stateTime){
        return animation.getKeyFrame(stateTime);
    }
}
