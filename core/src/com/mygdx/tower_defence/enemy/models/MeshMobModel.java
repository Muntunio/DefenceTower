package com.mygdx.tower_defence.enemy.models;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.tower_defence.map.model.MapAssetModel;


public class MeshMobModel extends AbstractEnemyModel {

    public MeshMobModel(MapAssetModel manager) {
        super(manager);

        initMobStatic();
        updateHealthTexture();
    }

    private void initMobStatic() {
        this.max_hp = 100;
        this.hp = max_hp;
        this.speed = 0.5f;
        setScale(0.35f);
    }

    @Override
    protected void pullTexturesFromManager(MapAssetModel manager) {
        this.walk_animation = new Animation<TextureRegion>(0.07f,
                manager.manager.get(MapAssetModel.mesh_mob).findRegions(
                        "1_enemies_1_walk"), Animation.PlayMode.LOOP);

        current_frame = walk_animation.getKeyFrame(state_time);
    }
}
