package com.Kanji_Kado.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class MainMenu implements Screen 
{

	private Stage stage; // done
	private TextureAtlas atlas; //done
	private Skin skin; //Appearance of buttons and ui DONE
	private Table table; //done
	private TextButton buttonPlay, buttonExit;
	private Label heading;
	private BitmapFont white, black; //done
	
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Table.drawDebug(stage);
		
		stage.act(delta); //update everything in the stage
		stage.draw(); //everything draws
	}

	@Override
	public void resize(int width, int height) 
	{


	}

	@Override
	public void show() 
	{
	
		stage = new Stage();
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(atlas);
		table = new Table(skin); //dont have to put skin, but good practice
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		white = new BitmapFont(Gdx.files.internal("fonts/white.fnt"), false); 
		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button.up");
		textButtonStyle.down = skin.getDrawable("button.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = white;
		
		buttonExit = new TextButton("Exit", textButtonStyle);
		buttonExit.pad(20); // padding around the text of the button
		
		table.add(buttonExit);
		//table.debug(); //enables all the debug lines
		stage.addActor(table);
		
		

	}

	@Override
	public void hide() {
	

	}

	@Override
	public void pause() {


	}

	@Override
	public void resume() {


	}

	@Override
	public void dispose() {


	}

}
