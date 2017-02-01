package com.jamesdavy21.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jamesdavy21.game.States.GameStateManager;
import com.jamesdavy21.game.States.MenuState;

public class runner extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Flappy space";
	private GameStateManager gsm;
	private SpriteBatch batch;
	private Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
//		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
//		music.setLooping(true);
//		music.setVolume(0.5f);
//		music.play();
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose() {
		super.dispose();
//		music.dispose();
	}
}
