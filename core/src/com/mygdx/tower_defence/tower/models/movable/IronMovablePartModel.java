package com.mygdx.tower_defence.tower.models.movable;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.tower_defence.tower.textures.TowerTexturesCache;
import com.mygdx.tower_defence.tower.textures.TowerType;

public class IronMovablePartModel extends MovablePartModel {
    public IronMovablePartModel() {
        super();
        moving_distance = 55;
    }

    public IronMovablePartModel(int x_position, int y_position) {
        super(x_position, y_position);
        moving_distance = 55;
    }

    @Override
    public void setPosition(int x, int y){
        this.x_position = x+1;
        this.y_position = y+45;
        recalculatePosition();
    }

    @Override
    protected void recalculatePosition() {
        x_position_front = x_position;
        y_position_front = y_position;

        x_position_back = x_position+1;
        y_position_back = y_position+22;

        recalculateBulletPosition();
    }

    @Override
    protected void recalculateBulletPosition() {
        int width = getFrontTexture().getRegionWidth();
        if( width < getBackTexture().getRegionWidth() )
            width = getBackTexture().getRegionWidth();

        x_position_bullet = x_position + width/2 - getBulletTexture().getRegionWidth()/2;
        y_position_bullet = y_position + 22;
    }

    @Override
    public TextureRegion getBackTexture(){
        return TowerTexturesCache.getTowerTextures(TowerType.IRON).getBack(level);
    }

    @Override
    public TextureRegion getFrontTexture(){
        return TowerTexturesCache.getTowerTextures(TowerType.IRON).getFront(level);
    }

    @Override
    public TextureRegion getBulletTexture(){
        return TowerTexturesCache.getTowerTextures(TowerType.IRON).getBullet();
    }
}
