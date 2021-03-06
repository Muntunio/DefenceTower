package com.mygdx.tower_defence.tower.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.tower_defence.tower.models.movable.StoneMovablePartModel;
import com.mygdx.tower_defence.tower.textures.TowerTexturesCache;
import com.mygdx.tower_defence.tower.textures.TowerType;

public class StoneTowerModel extends TowerModelAbstract{

    public StoneTowerModel(){
        super();
        setPosition(0,0);
    }

    public StoneTowerModel(int x, int y){
        super();
        setPosition(x,y);
    }

    @Override
    protected void setModel() {
        movable_model = new StoneMovablePartModel();
    }

    @Override
    public TextureRegion getPillarTexture() {
        return TowerTexturesCache.getTowerTextures(TowerType.STONE)
                .getPillar(level);
    }

    @Override
    public TextureRegion getBuildMenu() {
        return TowerTexturesCache.getTowerTextures(TowerType.STONE)
                .getBuildMenu(level);
    }

    @Override
    public TowerType getType() {
        return TowerType.STONE;
    }
}
