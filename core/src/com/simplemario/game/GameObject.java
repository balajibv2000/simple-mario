package com.simplemario.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.math.Rectangle;

public class GameObject {

    Random random = new Random();

    ArrayList<Integer> objectXs = new ArrayList<Integer>();
    ArrayList<Integer> objectYs = new ArrayList<Integer>();
    ArrayList<Rectangle> objectRectangles =  new ArrayList<Rectangle>();
    Texture object;
    int objectCount;
    int speed;

    public void makeObject() {
        float height = random.nextFloat() * (( Gdx.graphics.getHeight() / 100)*60);
        objectYs.add((int)height);
        objectXs.add(Gdx.graphics.getWidth());
    }

    public void drawRectangles(SpriteBatch batch){
        objectRectangles.clear();
        for(int i =0 ; i < objectXs.size() ; i++){
            batch.draw(object , objectXs.get(i) , objectYs.get(i));
            objectXs.set(i , objectXs.get(i) - speed);
            objectRectangles.add(new Rectangle(objectXs.get(i) , objectYs.get(i) , object.getWidth() , object.getHeight()));
        }
    }

    public void objectsClear(){
        objectXs.clear();
        objectYs.clear();
        objectRectangles.clear();
        objectCount = 0;
    }

    public void objectRemove(int i){
        objectRectangles.remove(i);
        objectXs.remove(i);
        objectYs.remove(i);
    }

    public boolean checkOverlap(Rectangle rectangle){
        for(int i = 0 ; i < objectRectangles.size() ; i++){
            if(Intersector.overlaps(rectangle , objectRectangles.get(i))){
                objectRemove(i);
                return true;
            }
        }
        return false;
    }

}
