package com.mygdx.tower_defence.tower.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.tower_defence.tower.models.movable.FireMovablePartModel;
import com.mygdx.tower_defence.tower.textures.TowerTexturesCache;
import com.mygdx.tower_defence.tower.textures.TowerType;

public class FireTowerModel extends TowerModelAbstract {
    public FireTowerModel() {
        super();
        setPosition(0,0);
    }

    public FireTowerModel(int x,int y) {
        super();
        setPosition(x,y);
    }

    @Override
    protected void setModel() {
        movable_model = new FireMovablePartModel();
    }

    @Override
    public TextureRegion getPillarTexture() {
        return TowerTexturesCache.getTowerTextures(TowerType.FIRE)
                .getPillar(level);
    }

    @Override
    public TextureRegion getBuildMenu() {
        return TowerTexturesCache.getTowerTextures(TowerType.FIRE)
                .getBuildMenu(level);
    }
}
