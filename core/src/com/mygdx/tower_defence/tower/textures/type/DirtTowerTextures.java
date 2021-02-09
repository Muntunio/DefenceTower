package com.mygdx.tower_defence.tower.textures.type;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.tower_defence.tower.textures.TowerTextures;

public class DirtTowerTextures extends TowerTextures {

    public DirtTowerTextures() {
        super();
    }

    @Override
    protected TextureAtlas loadAsset() {
        AssetManager manager = new AssetManager();
        manager.load("tower/dirt_tower.txt", TextureAtlas.class);
        manager.finishLoading();
        return manager.get("tower/dirt_tower.txt", TextureAtlas.class);
    }


}
