package com.mygdx.tower_defence.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.tower_defence.MainTowerDefence;
import com.mygdx.tower_defence.instance.LoadingCircle;
import com.mygdx.tower_defence.map.views.MapView;


public class SplashScreen extends AbstractScreen{
    
    private AssetManager manager;

    private LoadingCircle circle;


    public SplashScreen(final MainTowerDefence game) {
        super(game);
        init();

//        Timer.schedule(new Timer.Task() {
//            @Override
//            public void run() {
//                game.setScreen(new MapView(game));
//            }
//        },1);


    }

    private void init() {
        manager = new AssetManager();
        manager.load("icon/loading.png", Texture.class);
        manager.load("icon/loading_bg.png", Texture.class);
        manager.finishLoading();
        initBackground();
        initCircle();

    }

    private void initBackground() {
        Image background_image = new Image(manager.get("icon/loading_bg.png", Texture.class));
        stage.addActor(background_image);
    }

    private void initCircle() {
        circle = new LoadingCircle(
                manager.get("icon/loading.png", Texture.class),
                new Vector2(MainTowerDefence.WIDTH/2,MainTowerDefence.HEIGHT/2-100),
                new Vector2(100,100));
        stage.addActor(circle);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        stage.act();
        circle.rotateBy(-2);
    }

    @Override
    public void dispose() {
        super.dispose();
        manager.dispose();
    }
}


