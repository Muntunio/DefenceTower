package com.mygdx.tower_defence.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.tower_defence.MainTowerDefence;



public class AbstractScreen implements Screen {

    protected MainTowerDefence game;

    protected Stage stage;
    private OrthographicCamera camera;

    protected SpriteBatch spriteBatch;

    public AbstractScreen(MainTowerDefence game){
        this.game = game;
        createCamera();
        stage = new Stage(new StretchViewport(MainTowerDefence.WIDTH,
                MainTowerDefence.HEIGHT, camera));
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
    }


    private void createCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MainTowerDefence.WIDTH,
                MainTowerDefence.HEIGHT);
        camera.update();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }


    @Override
    public void dispose() {

    }

    @Override
    public void pause() {
        game.setIs_paused(true);
    }

    @Override
    public void resume() {
        game.setIs_paused(false);
    }

    @Override
    public void hide() { }

    @Override
    public void resize(int width, int height) { }


}
