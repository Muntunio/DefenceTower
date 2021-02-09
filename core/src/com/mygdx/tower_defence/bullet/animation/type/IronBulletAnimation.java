package com.mygdx.tower_defence.bullet.animation.type;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.tower_defence.bullet.animation.BulletAnimation;


public class IronBulletAnimation extends BulletAnimation {

    public IronBulletAnimation() { super(); }

    @Override
    protected TextureAtlas loadAsset() {
        AssetManager manager = new AssetManager();
        manager.load("bullet/iron.txt", TextureAtlas.class);
        manager.finishLoading();
        return manager.get("bullet/iron.txt", TextureAtlas.class);
    }
}


