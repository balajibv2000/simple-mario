package com.simplemario.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Coin extends GameObject{

    Coin (String path){
        this.object = new Texture(path);
        this.speed = 4;
    }

}
