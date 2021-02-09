package com.mygdx.tower_defence.tower.models.movable;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.tower_defence.tower.textures.TowerTexturesCache;
import com.mygdx.tower_defence.tower.textures.TowerType;

public class FireMovablePartModel extends  MovablePartModel{

    public FireMovablePartModel(){
        super();
        initType();
    }

    public FireMovablePartModel(int x_position, int y_position) {
        super(x_position, y_position);
        initType();
    }

    private void initType() {
        moving_distance = 50;
    }

    @Override
    public void setPosition(int x, int y){
        this.x_position = x-1;
        this.y_position = y+58;
        recalculatePosition();
    }

    @Override
    protected void recalculatePosition() {
        x_position_front = x_position;
        y_position_front = y_position;

        x_position_back = x_position+6;
        y_position_back = y_position+21;

        recalculateBulletPosition();
    }

    @Override
    protected void recalculateBulletPosition() {
        int width = getFrontTexture().getRegionWidth();
        if( width < getBackTexture().getRegionWidth() )
            width = getBackTexture().getRegionWidth();

        x_position_bullet = x_position + width/2 - getBulletTexture().getRegionWidth()/2;
        y_position_bullet = y_position + 17;
    }

    @Override
    public TextureRegion getBackTexture(){
        return TowerTexturesCache.getTowerTextures(TowerType.FIRE).getBack(level);
    }

    @Override
    public TextureRegion getFrontTexture(){
        return TowerTexturesCache.getTowerTextures(TowerType.FIRE).getFront(level);
    }

    @Override
    public TextureRegion getBulletTexture(){
        return TowerTexturesCache.getTowerTextures(TowerType.FIRE).getBullet();
    }
}
