package com.jamesdavy21.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by James Davy on 28/01/2017.
 */

public class Tube {
    public static final int TUBE_WIDTH = 52;
    private static final int FLUC =130;
    private static final int TubeGap = 95;
    private static final int LOWEST = 120;
    private Texture topTube, bottomTube;
    private Vector2 ptt, pbt;
    private Rectangle boundsTop, boundsBot;
    private Random rand;

    public Tube(float x){
        topTube = new Texture("tube.png");
        bottomTube = new Texture("tube1.png");
        rand = new Random();
        ptt = new Vector2(x, rand.nextInt(FLUC)+ TubeGap + LOWEST);
        pbt = new Vector2(x, ptt.y -TubeGap - bottomTube.getHeight());
        boundsTop = new Rectangle(ptt.x, ptt.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(pbt.x, pbt.y, bottomTube.getWidth(), bottomTube.getHeight());

    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPbt() {
        return pbt;
    }

    public Vector2 getPtt() {
        return ptt;
    }

    public void reporsition(float x){
        ptt.set(x, rand.nextInt(FLUC)+ TubeGap + LOWEST);
        pbt.set(x,ptt.y - TubeGap - bottomTube.getHeight());
        boundsTop.setPosition(ptt.x, ptt.y);
        boundsBot.setPosition(pbt.x, pbt.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }

}

