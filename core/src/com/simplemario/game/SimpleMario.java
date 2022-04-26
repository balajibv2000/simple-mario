package com.simplemario.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class SimpleMario extends ApplicationAdapter {
	SpriteBatch batch;

	Texture background;

	Avatar mario;
	BitmapFont scoreMap;
	BitmapFont healthMap;

	Coin coin;
	Bomb bomb;
	Arrow arrow;
	Random random;

	int score = 0;
	int health = 3;
	int gameState = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();

		// initializing background
		background = new Texture("bg.png");

		// initializing mario avatar
		mario = Avatar.getInstance();

		// initializing coin and bomb object
		coin = new Coin("coin.png");
		bomb = new Bomb("bomb.png");
		arrow = new Arrow("arrow.png");

		random = new Random();

		// initializing font for score
		scoreMap = new BitmapFont();
		scoreMap.setColor(Color.WHITE);
		scoreMap.getData().setScale(10);

		healthMap = new BitmapFont();
		healthMap.setColor(Color.RED);
		healthMap.getData().setScale(7);
		
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
			arrow.boomerangRender(batch);

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
				health = 3;

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

		if(arrow.checkOverlap(mario.getRectangle())){
			health--;
			mario.setDizzy(batch);
			if(health == 0)
				gameState = 2;
		}

		scoreMap.draw(batch, String.valueOf(score) , 900 ,2100);
		healthMap.draw(batch, String.valueOf("Health: "+health) , 100 ,2100);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
