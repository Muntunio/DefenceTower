package com.mygdx.tower_defence.tower.models.movable;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.tower_defence.tower.textures.TowerTexturesCache;
import com.mygdx.tower_defence.tower.textures.TowerTexturesType;

public class StoneMovablePartModel extends MovablePartModel {

    public StoneMovablePartModel() {
        super();
        moving_distance = 100;
    }

    public StoneMovablePartModel(int x_position, int y_position) {
        super(x_position, y_position);
        moving_distance = 100;
    }

    @Override
    protected void recalculatePosition() {
        x_position_front = x_position;
        y_position_front = y_position;

        x_position_back = x_position+50;
        y_position_back = y_position;

        recalculateBulletPosition();
    }

    @Override
    protected void recalculateBulletPosition() {
        int width = getFrontTexture().getRegionWidth();
        if( width < getBackTexture().getRegionWidth() )
            width = getBackTexture().getRegionWidth();

        x_position_bullet = x_position + width/2 +5;
        y_position_bullet = y_position + 50;
    }

    @Override
    public TextureRegion getBackTexture(){
        return TowerTexturesCache.getTowerTextures(TowerTexturesType.STONE).getBack(level);
    }

    @Override
    public TextureRegion getFrontTexture(){
        return TowerTexturesCache.getTowerTextures(TowerTexturesType.STONE).getFront(level);
    }

    @Override
    public TextureRegion getBulletTexture(){
        return TowerTexturesCache.getTowerTextures(TowerTexturesType.STONE).getBullet();
    }

}
