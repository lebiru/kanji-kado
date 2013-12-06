package com.Kanji_Kado;

import com.Kanji_Kado.screens.Splash;
import com.badlogic.gdx.Game;

public class KanjiKadoGame extends Game
{
	
	public static final String TITLE = "Kanji Kado", VERSION = "0.01";
	
	@Override
	/**
	 * This instantiates the class, called once.
	 */
	public void create() 
	{		
		setScreen(new Splash());
	}

	@Override
	public void dispose() 
	{
		super.dispose();
	}

	@Override
	public void render() 
	{		
		super.render();
	}

	@Override
	public void resize(int width, int height) 
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
}
