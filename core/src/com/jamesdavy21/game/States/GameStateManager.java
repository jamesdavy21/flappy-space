package com.jamesdavy21.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by James Davy on 28/01/2017.
 */

public class GameStateManager {

    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();

    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().disopse();
    }

    public void set(State state){
        states.pop().disopse();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

}
