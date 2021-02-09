package com.mygdx.tower_defence.tower.textures;

import com.mygdx.tower_defence.tower.textures.type.DirtTowerTextures;
import com.mygdx.tower_defence.tower.textures.type.FireTowerTextures;
import com.mygdx.tower_defence.tower.textures.type.IronTowerTextures;
import com.mygdx.tower_defence.tower.textures.type.StoneTowerTextures;

public class TowerTexturesFactory {
    public static TowerTextures createTowerTextures(TowerTexturesType type){
        switch (type){
            case STONE:
                return new StoneTowerTextures();
            case IRON:
                return new IronTowerTextures();
            case FIRE:
                return new FireTowerTextures();
            case DIRT:
                return new DirtTowerTextures();
            default:
                throw new IllegalArgumentException("Unknown tower texture type"+type);
        }
    }
}
