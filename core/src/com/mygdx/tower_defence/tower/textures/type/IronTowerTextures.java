package com.mygdx.tower_defence.tower.textures.type;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.tower_defence.tower.textures.TowerTextures;

public class IronTowerTextures extends TowerTextures {

    public IronTowerTextures() {
        super();
    }

    @Override
    protected TextureAtlas loadAsset() {
        AssetManager manager = new AssetManager();
        manager.load("tower/iron_tower.txt", TextureAtlas.class);
        manager.finishLoading();
        return manager.get("tower/iron_tower.txt", TextureAtlas.class);
    }
}
