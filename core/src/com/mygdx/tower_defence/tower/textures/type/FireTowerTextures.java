package com.mygdx.tower_defence.tower.textures.type;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.tower_defence.tower.textures.TowerTextures;

public class FireTowerTextures extends TowerTextures {

    public FireTowerTextures() {
        super();
    }

    @Override
    protected TextureAtlas loadAsset() {
        AssetManager manager = new AssetManager();
        manager.load("tower/fire_tower.txt", TextureAtlas.class);
        manager.finishLoading();
        return manager.get("tower/fire_tower.txt", TextureAtlas.class);
    }
}
