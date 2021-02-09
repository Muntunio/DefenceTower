package com.mygdx.tower_defence.tower.textures;

import java.util.HashMap;
import java.util.Map;

public class TowerTexturesCache {
    private static Map<TowerTexturesType, TowerTextures> towerTextures = new HashMap<>();

    public static TowerTextures getTowerTextures(TowerTexturesType type){
        TowerTextures result = towerTextures.get(type);

        if(result == null){
            result = TowerTexturesFactory.createTowerTextures(type);
            towerTextures.put(type,result);
        }
        return result;
    }
}
