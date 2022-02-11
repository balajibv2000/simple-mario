package com.simplemario.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Coin extends GameObject{

    Coin (String path){
        this.object = new Texture(path);
        this.speed = 4;
    }

    public void coinsRender(SpriteBatch batch){
        if(this.objectCount< 100){
            this.objectCount++;
        } else {
            this.objectCount = 0;
            this.makeObject();
        }

        this.drawRectangles(batch);
    }

}
