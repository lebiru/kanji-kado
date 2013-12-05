package com.Kanji_Kado.screens;

import com.Kanji_Kado.KanjiKadoGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenu implements Screen 
{

	private Stage stage; // done
	private TextureAtlas atlas; //done
	private Skin skin; //Appearance of buttons and ui DONE
	private Table table; //done
	private TextButton buttonExit;
	private Label heading;
	private BitmapFont white; //done


	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



		Table.drawDebug(stage);

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
		
		Gdx.input.setInputProcessor(stage);
		
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(atlas);
		
		table = new Table(skin); //dont have to put skin, but good practice
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		white = new BitmapFont(Gdx.files.internal("fonts/white.fnt"), false); 
		//black = new BitmapFont(Gdx.files.internal("fonts/black.fnt"), false); 


		//Creating buttons
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button.up");
		textButtonStyle.down = skin.getDrawable("button.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = white;

		buttonExit = new TextButton("Exit", textButtonStyle);
		buttonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) 
			{
				Gdx.app.exit();
			}
		});
		buttonExit.pad(20); // padding around the text of the button
		
		//Creating heading
		LabelStyle headingStyle = new LabelStyle(white, Color.WHITE);

		heading = new Label(KanjiKadoGame.TITLE, headingStyle);
		heading.setFontScale(3);


		//putting stuff together
		table.add(heading);
		table.row(); // adding a new row
		table.add(buttonExit);
		table.debug(); //enables all the debug lines
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
