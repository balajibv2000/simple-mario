package com.simplemario.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Random;

import sun.rmi.runtime.Log;




public class SimpleMario extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture[] mario;
	int marioState = 0;
	int pause = 0;
	float gravity = 0.2f;
	float velocity = 0;
	int marioY = 0;
	Rectangle marioRectangle;
	BitmapFont font;
	Texture  dizzy;

	Coin coin;
	Bomb bomb;
	Random random;

	int score = 0;
	int gameState = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
		mario = new Texture[4];
		mario[0] = new Texture("frame-1.png");
		mario[1] = new Texture("frame-2.png");
		mario[2] = new Texture("frame-3.png");
		mario[3] = new Texture("frame-4.png");

		marioY = Gdx.graphics.getHeight() / 2;

		coin = new Coin("coin.png");
		bomb = new Bomb("bomb.png");
		random = new Random();

		dizzy = new Texture("dizzy-1.png");

		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(10);
		
	}

	@Override
	public void render () {
		ScreenUtils.clear(0,0,0.2f,1);
		batch.begin();
		batch.draw(background , 0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if (gameState == 1){
			// game is live
			if(coin.objectCount< 100){
				coin.objectCount++;
			} else {
				coin.objectCount = 0;
				coin.makeObject();
			}

			if(bomb.objectCount < 200){
				bomb.objectCount++;
			} else {
				bomb.objectCount = 0;
				bomb.makeObject();
			}

			coin.drawRectangles(batch);
			bomb.drawRectangles(batch);

			if(Gdx.input.justTouched()){
				velocity = -10;
			}

			if(pause < 8){
				pause++;
			} else {
				pause = 0;
				if(marioState < 3){
					marioState++;
				} else {
					marioState = 0;
				}
			}

			velocity += gravity;
			marioY -= velocity;

			if(marioY <= 0) {
				marioY = 0;
			}
		} else if (gameState == 0){
			// waiting to start
			if (Gdx.input.justTouched()) {
				gameState = 1;
			}
		} else if (gameState == 2){
			// game over
			if (Gdx.input.justTouched()) {
				gameState = 1;
				marioY = Gdx.graphics.getHeight() / 2;
				score = 0;
				velocity = 0;

				coin.objectsClear();

				bomb.objectsClear();

			}
		}


		if (gameState == 2){
			batch.draw(dizzy , Gdx.graphics.getWidth() / 2 - mario[0].getWidth() / 2,  marioY);
		} else {
			batch.draw(mario[marioState],Gdx.graphics.getWidth() / 2 - mario[0].getWidth() / 2,  marioY);
		}

		marioRectangle = new Rectangle(Gdx.graphics.getWidth() / 2 - mario[0].getWidth() / 2 , marioY , mario[marioState].getWidth() , mario[marioState].getHeight());

		for(int i = 0 ; i < coin.objectRectangles.size() ; i++){
			if(Intersector.overlaps(marioRectangle , coin.objectRectangles.get(i))){
				score++;
				coin.objectRemove(i);
				break;
			}
		}

		for(int i = 0 ; i < bomb.objectRectangles.size() ; i++){
			if(Intersector.overlaps(marioRectangle , bomb.objectRectangles.get(i))){
				Gdx.app.log("bomb","collision");
				bomb.objectRemove(i);
				gameState = 2;
			}
		}

		font.draw(batch, String.valueOf(score) , 100 ,200);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
