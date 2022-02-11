package com.simplemario.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bomb extends GameObject{
    Bomb (String path){
        this.object = new Texture(path);
        this.speed = 8;
    }

    public void bombsRender(SpriteBatch batch){
        if(this.objectCount< 200){
            this.objectCount++;
        } else {
            this.objectCount = 0;
            this.makeObject();
        }

        this.drawRectangles(batch);
    }

}
