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

	Avatar mario;
	BitmapFont font;

	Coin coin;
	Bomb bomb;
	Random random;

	int score = 0;
	int gameState = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();

		// initializing background
		background = new Texture("bg.png");

		// initializing mario avatar
		String[] textures = {"frame-1.png" , "frame-2.png" , "frame-3.png" ,"frame-4.png"};
		String dizzyTexture = "dizzy-1.png";
		mario = new Avatar(textures , dizzyTexture);

		// initializing coin and bomb object
		coin = new Coin("coin.png");
		bomb = new Bomb("bomb.png");

		random = new Random();

		// initializing font for score
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(10);
		
	}

	@Override
	public void render () {
		ScreenUtils.clear(0,0,0.2f,1);
		batch.begin();
		float height =(( Gdx.graphics.getHeight() / 100)*60);
		batch.draw(background , 0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if (gameState == 1){
			// game is live
			coin.coinsRender(batch);
			bomb.bombsRender(batch);

			if(Gdx.input.justTouched()){
				mario.jump(height);
			}
			mario.run();

		} else if (gameState == 0){
			// waiting to start
			if (Gdx.input.justTouched()) {
				gameState = 1;
			}
		} else if (gameState == 2){
			// game over
			if (Gdx.input.justTouched()) {
				mario.reset();
				coin.objectsClear();
				bomb.objectsClear();
				gameState = 1;
				score = 0;

			}
		}

		if (gameState == 2){
			mario.setDizzy(batch);
		} else {
			// Gdx.app.log("avatarState", ":" + mario.avatarState);
			mario.animate(batch);
		}

		if(coin.checkOverlap(mario.getRectangle())){
			score++;
		};

		if(bomb.checkOverlap(mario.getRectangle())){
			gameState = 2;
		}

		font.draw(batch, String.valueOf(score) , 100 ,200);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
