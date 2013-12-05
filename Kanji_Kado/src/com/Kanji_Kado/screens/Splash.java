package com.Kanji_Kado.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.Kanji_Kado.tween.SpriteAccessor;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Splash implements Screen
{

	private SpriteBatch batch;
	private Sprite splash;
	private TweenManager tweenManager;
	
	@Override
	public void render(float delta) 
	{
		//sets the color
		Gdx.gl.glClearColor(0, 0, 0, 1);
		//does the clearing
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		tweenManager.update(delta);
		
		//have to do this
		batch.begin();
		splash.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) 
	{
	
		
	}

	@Override
	public void show() 
	{
		batch = new SpriteBatch();
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		
		Texture splashTexture= new Texture("img/title.png");
		splash = new Sprite(splashTexture);
	
		splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager); //empty for tweenManager is also ok
		Tween.to(splash, SpriteAccessor.ALPHA, 2).target(1).repeatYoyo(1, 0.5f).setCallback(new TweenCallback()
		{
			@Override
			public void onEvent(int type, BaseTween<?> source)
			{
				((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
			}
		}).start(tweenManager);
		
		
	}

	@Override
	public void hide() 
	{
	
		
	}

	@Override
	public void pause() 
	{
		
	}

	@Override
	public void resume() 
	{

		
	}

	@Override
	public void dispose() 
	{
		//must dispose these! always dispose textures
		batch.dispose();
		splash.getTexture().dispose();
		
		
	}

}
