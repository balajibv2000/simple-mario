package com.simplemario.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SimpleMario extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture[] mario;
	int marioState = 0;
	int pause = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
		mario = new Texture[4];
		mario[0] = new Texture("frame-1.png");
		mario[1] = new Texture("frame-2.png");
		mario[2] = new Texture("frame-3.png");
		mario[3] = new Texture("frame-4.png");

	}

	@Override
	public void render () {
		ScreenUtils.clear(0,0,0.2f,1);
		batch.begin();
		batch.draw(background , 0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

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

		batch.draw(mario[marioState],Gdx.graphics.getWidth() / 2 - mario[0].getWidth() / 2,  Gdx.graphics.getHeight() / 2);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
