package com.jamesdavy21.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by James Davy on 28/01/2017.
 */

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVE = 100;
    private Vector3 position;
    private  Vector3 speed;
    private Texture bird;
    private Rectangle bounds;
    private Animation birdAnimtion;
    private Texture texture;


    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        speed = new Vector3(0,0,0);
        texture = new Texture("char1.png");
        birdAnimtion = new Animation(new TextureRegion(texture),1,0.5f);
        bounds = new Rectangle(x,y,texture.getWidth()/9, texture.getHeight());
    }

    public void update(float dt){
        birdAnimtion.update(dt);
        if(position.y>0)
            speed.add(0, GRAVITY, 0);
        speed.scl(dt);
        position.add(MOVE * dt,speed.y, 0);
        if(position.y<0)
            position.y = 0;

        speed.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimtion.getFrame();
    }

    public void jump(){
        speed.y = 250;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        texture.dispose();
    }
}
