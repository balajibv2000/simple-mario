package com.simplemario.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Avatar {
    Texture[] avatar;
    Texture  dizzy;
    int avatarState = 0;
    int pause = 0;
    float gravity = 0.2f;
    float velocity = 0;
    int avatarY = 0;
    Rectangle avatarRectangle;

    private static Avatar avatar_instance = null;

    private Avatar(){
        avatar = new Texture[4];
        avatar[0] = new Texture("frame-1.png");
        avatar[1] = new Texture("frame-2.png");
        avatar[2] = new Texture("frame-3.png");
        avatar[3] = new Texture("frame-4.png");
        dizzy = new Texture("dizzy-1.png");

        avatarY = Gdx.graphics.getHeight() / 2;

    }

    public static Avatar getInstance(){
        if(avatar_instance == null){
            avatar_instance = new Avatar();
        }
        return avatar_instance;
    }

    public void jump(float height){
        if(avatarY >= height) {
            velocity = 0;
        } else {
            velocity = -10;
        }
    }

    public void run(){
        if(pause < 8){
            pause++;
        } else {
            pause = 0;
            if(avatarState < 3){
                avatarState++;
            } else {
                avatarState = 0;
            }
        }

        velocity += gravity;
        avatarY -= velocity;


        if(avatarY <= 0) {
            avatarY = 0;
        }
    }

    public void animate(SpriteBatch batch){
        batch.draw(avatar[avatarState],Gdx.graphics.getWidth() / 4 - avatar[0].getWidth() / 2,  avatarY);
    }

    public void setDizzy(SpriteBatch batch){
        batch.draw(dizzy , Gdx.graphics.getWidth() / 4- avatar[0].getWidth() / 2,  avatarY);
    }

    public Rectangle getRectangle(){
        avatarRectangle = new Rectangle(Gdx.graphics.getWidth() / 4 - avatar[0].getWidth() / 2 , avatarY , avatar[avatarState].getWidth() , avatar[avatarState].getHeight());

        return avatarRectangle;
    }

    public void reset(){
        avatarY = Gdx.graphics.getHeight() / 2;
        velocity = 0;
    }
}
