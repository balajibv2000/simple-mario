package com.simplemario.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Arrow extends GameObject{

    Arrow(String path){
        this.object = new Texture(path);
        this.speed = 15;
    }

    public void boomerangRender(SpriteBatch batch){
        if(this.objectCount< 300){
            this.objectCount++;
        } else {
            this.objectCount = 0;
            this.makeObject();
        }

        this.drawRectangles(batch);
    }
}
