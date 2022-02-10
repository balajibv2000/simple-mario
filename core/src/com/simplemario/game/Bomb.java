package com.simplemario.game;

import com.badlogic.gdx.graphics.Texture;

public class Bomb extends GameObject{
    Bomb (String path){
        this.object = new Texture(path);
        this.speed = 8;
    }
}
