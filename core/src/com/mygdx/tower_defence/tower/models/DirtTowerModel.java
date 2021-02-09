package com.mygdx.tower_defence.tower.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.tower_defence.tower.models.movable.DirtMovablePartModel;
import com.mygdx.tower_defence.tower.textures.TowerTexturesCache;
import com.mygdx.tower_defence.tower.textures.TowerTexturesType;

public class DirtTowerModel extends TowerModelAbstract{

    public DirtTowerModel() {
        super();
        setPosition(0,0);
    }

    public DirtTowerModel(int x,int y) {
        super();
        setPosition(x,y);
    }

    @Override
    public TextureRegion getPillarTexture(){
        return TowerTexturesCache.getTowerTextures(TowerTexturesType.DIRT)
                .getPillar(level); }


    @Override
    protected void setModel() { movable_model = new DirtMovablePartModel(); }

    @Override
    public TextureRegion getBuildMenu(){
        return TowerTexturesCache.getTowerTextures(TowerTexturesType.DIRT)
                .getBuildMenu(level);
    }


}
