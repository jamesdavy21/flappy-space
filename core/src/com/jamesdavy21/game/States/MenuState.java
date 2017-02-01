package com.jamesdavy21.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jamesdavy21.game.runner;

/**
 * Created by James Davy on 28/01/2017.
 */

public class MenuState extends State {
    private Texture background;
    private Texture playbt;
    private String name;
    BitmapFont font;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, runner.WIDTH/2, runner.HEIGHT/2);
        background = new Texture("background.png");
        playbt = new Texture("play.png");
        font = new BitmapFont(Gdx.files.internal("arial-32.fnt"));
        Gdx.files.internal("default.png");
        font.setColor(Color.WHITE);
        name = new String("Flappy space");
    }



    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0);
        sb.draw(playbt, cam.position.x-playbt.getWidth()/2, cam.position.y-playbt.getWidth()/2 );
        font.draw(sb, name, cam.position.x -50, (cam.position.y *2) -50  );
        sb.end();

    }

    @Override
    public void disopse() {
        background.dispose();
        playbt.dispose();
        font.dispose();
    }


}
