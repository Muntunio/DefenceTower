package com.mygdx.tower_defence.map.model;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;


public class MapAssetModel implements Disposable {

    public AssetManager manager = new AssetManager();
    /*
    *
    * Site to add description for loading item
    *
    */
    public static final AssetDescriptor<Texture> map =
            new AssetDescriptor<>("map/map1.png", Texture.class);

    public static final AssetDescriptor<TextureAtlas> green_mob =
            new AssetDescriptor<>("enemy/green_mob.txt", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> mesh_mob =
            new AssetDescriptor<>("enemy/mesh_mob.txt", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> red_mob =
            new AssetDescriptor<>("enemy/red_mob.txt", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> health_bar =
            new AssetDescriptor<>("enemy/health_bar.txt", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> soil_tower =
            new AssetDescriptor<>("tower/soil_tower.txt", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> dirt_bullet =
            new AssetDescriptor<>("bullet/dirt.txt", TextureAtlas.class);


    public void load(){
        manager.load(map);
        //LOAD MOB
        manager.load(green_mob);
        manager.load(mesh_mob);
        manager.load(red_mob);
        manager.load(health_bar);

        //LOAD TOWER
        manager.load(soil_tower);
        manager.load(dirt_bullet);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}