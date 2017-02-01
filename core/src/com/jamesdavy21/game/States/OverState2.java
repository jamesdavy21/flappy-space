package com.jamesdavy21.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jamesdavy21.game.runner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by James Davy on 28/01/2017.
 */

public class OverState2 extends State {
    private Texture background;
    private Texture playbt;
    private int score;
    private String point;
    BitmapFont font, font2;
    private String highScore = "";

    public OverState2(GameStateManager gsm, int score) {
        super(gsm);
        this.score = score;
        cam.setToOrtho(false, runner.WIDTH/2, runner.HEIGHT/2);
        background = new Texture("background.png");
        playbt = new Texture("play.png");
        font = new BitmapFont(Gdx.files.internal("arial-32.fnt"));
        Gdx.files.internal("default.png");
        font.setColor(Color.WHITE);
        font2 = new BitmapFont(Gdx.files.internal("arial-32.fnt"));
        Gdx.files.internal("default.png");
        font2.setColor(Color.WHITE);
        if(highScore == "")
        {
            highScore = this.getHighScore();
        }
        checkScore();
        point = "Your score is: " + score;
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
        sb.draw(playbt, cam.position.x-playbt.getWidth()/2, cam.position.y );
        font.draw(sb, point, cam.position.x - 55, (cam.position.y * 2) - 45 );
        font2.draw(sb, "Highscore: " + highScore, cam.position.x - 55, (cam.position.y * 2) - 25 );
        sb.end();

    }

    @Override
    public void disopse() {
        background.dispose();
        playbt.dispose();
        font.dispose();
    }

    public String getHighScore(){
        FileReader readfile = null;
        BufferedReader reader = null;
        try{
            readfile = new FileReader("highscore.dat");
            reader = new BufferedReader(readfile);
            return reader.readLine();
        }
        catch(Exception e){
            return "0";
        }
        finally {
            try {
                if(reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkScore(){
        if(score > Integer.parseInt(highScore)){
            highScore = "" + score;
            File scoreFile = new File("highscore.dat");
            if(!scoreFile.exists())
                try {
                    scoreFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            FileWriter filewriter = null;
            BufferedWriter writer = null;
            try {
                filewriter = new FileWriter(scoreFile);
                writer = new BufferedWriter(filewriter);
                writer.write(this.highScore);
            }
            catch (Exception e){

            }
            finally {
                if(writer != null)
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }


}
