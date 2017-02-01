package com.jamesdavy21.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jamesdavy21.game.runner;
import com.jamesdavy21.game.sprites.Bird;
import com.jamesdavy21.game.sprites.Tube;

/**
 * Created by James Davy on 28/01/2017.
 */

public class PlayState extends State{
    private static final int TUBE_SPACE = 125;
    private static final int TUBE_COUNT = 4;
    private static final int OFFSET = -30;
    private Bird bird;
    private Texture background;
    private Texture ground;
    private Vector2 ground1, ground2;
    private Tube tube;
    private Array<Tube> tubes;
    BitmapFont font;
    private int score = 0;
    private String point;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        cam.setToOrtho(false, runner.WIDTH/2, runner.HEIGHT/2);
        background = new Texture("background.png");
        ground = new Texture("ground.png");
        ground1 = new Vector2(cam.position.x-cam.viewportWidth /2,OFFSET);
        ground2 = new Vector2((cam.position.x-cam.viewportWidth/2)+ ground.getWidth(),OFFSET);
        tubes = new Array<Tube>();

        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACE + Tube.TUBE_WIDTH)));
        }
        font = new BitmapFont(Gdx.files.internal("arial-32.fnt"));
        Gdx.files.internal("default.png");
        font.setColor(Color.WHITE);
        score = 0;
        point = "0";
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;
        for(int i = 0; i < tubes.size; i++){
            Tube tube = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth/2) > tube.getPtt().x + tube.getTopTube().getWidth()){
                tube.reporsition(tube.getPtt().x + ((Tube.TUBE_WIDTH + TUBE_SPACE) * TUBE_COUNT));
            }

            if(tube.collides(bird.getBounds())){
                gsm.set(new OverState2(gsm, score));
            }

        }
        if(bird.getPosition().y <= ground.getHeight() + OFFSET)
            gsm.set(new OverState2(gsm, score));
        for(int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if ((bird.getPosition().x > tube.getPtt().x) & (bird.getPosition().x < (tube.getPtt().x+1.663875415))) {
                score++;
                point = Integer.toString(score);
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x-(cam.viewportWidth/2),0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPtt().x, tube.getPtt().y);
            sb.draw(tube.getBottomTube(), tube.getPbt().x, tube.getPbt().y);
        }
        sb.draw(ground, ground1.x, ground1.y);
        sb.draw(ground, ground2.x, ground2.y);
        font.draw(sb, point, cam.position.x - 15, (cam.position.y * 2) - 25 );
        sb.end();
    }

    @Override
    public void disopse() {
        background.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube : tubes)
            tube.dispose();
        font.dispose();
    }

    private void updateGround(){
        if(cam.position.x-(cam.viewportWidth/2) > ground1.x + ground.getWidth())
            ground1.add(ground.getWidth()*2,0);

        if(cam.position.x-(cam.viewportWidth/2) > ground2.x + ground.getWidth())
            ground2.add(ground.getWidth()*2,0);
        }

    public int getScore(){
        return score;
    }

    public void addScore(){
        score += 1;
    }
}
