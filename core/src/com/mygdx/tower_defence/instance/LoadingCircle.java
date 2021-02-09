package com.mygdx.tower_defence.instance;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.awt.*;

public class LoadingCircle extends Image {

    private Vector2 size;
    private Vector2 position;



    public LoadingCircle(Texture texture) {
        super(texture);
        position = new Vector2(0,0);
        size = new Vector2(texture.getWidth(),texture.getHeight());
        init();
    }

    public LoadingCircle(Texture texture, Vector2 position, Vector2 size) {
        super(texture);

        this.position = position;
        this.size = size;

        init();
    }

    private void init() {

        this.setSize(size.x,size.y);
        this.setOrigin(size.x/2,size.y/2);

        this.setPosition(position.x,position.y);
    }

}
