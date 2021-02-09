package com.mygdx.tower_defence.tower.textures;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class TowerTextures {
    private TextureRegion [] pillar;
    private TextureRegion [] front;
    private TextureRegion [] back;
    private TextureRegion bullet;
    private TextureRegion [] build;

    public TowerTextures() {
        pillar = new TextureRegion[3];
        front = new TextureRegion[3];
        back = new TextureRegion[3];
        build = new TextureRegion[3];

        TextureAtlas atlas = loadAsset();
        pullTextures(atlas);
    }


    protected abstract TextureAtlas loadAsset();

    protected void pullTextures(TextureAtlas atlas){
        for(int i=1;i<4; ++i) {
            pillar[i-1] = atlas.findRegion("pillar_"+i);
            front[i-1] = atlas.findRegion("front_"+i);
            back[i-1] = atlas.findRegion("back_"+i);
            build[i-1] = atlas.findRegion("upgrade_"+i);
        }
        bullet = atlas.findRegion("bullet");
    }

    public TextureRegion getPillar(int level) { return pillar[level]; }

    public TextureRegion getFront(int level) { return front[level]; }

    public TextureRegion getBack(int level) { return back[level]; }

    public TextureRegion getBuildMenu(int level) { return back[level]; }

    public TextureRegion getBullet() { return bullet; }

}
