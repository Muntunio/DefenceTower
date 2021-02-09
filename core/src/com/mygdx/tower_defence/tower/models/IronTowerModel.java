package com.mygdx.tower_defence.tower.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.tower_defence.tower.models.movable.DirtMovablePartModel;
import com.mygdx.tower_defence.tower.models.movable.IronMovablePartModel;
import com.mygdx.tower_defence.tower.textures.TowerTexturesCache;
import com.mygdx.tower_defence.tower.textures.TowerTexturesType;

public class IronTowerModel extends TowerModelAbstract {

    public IronTowerModel() {
        super();
        setPosition(0,0);
    }

    public IronTowerModel(int x,int y) {
        super();
        setPosition(x,y);
    }

    @Override
    protected void setModel() { movable_model = new IronMovablePartModel(); }

    @Override
    public TextureRegion getPillarTexture(){
        return TowerTexturesCache.getTowerTextures(TowerTexturesType.IRON)
                .getPillar(level); }

}
