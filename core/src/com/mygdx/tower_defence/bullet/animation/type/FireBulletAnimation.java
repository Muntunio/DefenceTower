package com.mygdx.tower_defence.bullet.animation.type;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.tower_defence.bullet.animation.BulletAnimation;


public class FireBulletAnimation extends BulletAnimation {

    public FireBulletAnimation() { super(); }

    @Override
    protected TextureAtlas loadAsset() {
        AssetManager manager = new AssetManager();
        manager.load("bullet/fire.txt", TextureAtlas.class);
        manager.finishLoading();
        return manager.get("bullet/fire.txt", TextureAtlas.class);
    }
}


