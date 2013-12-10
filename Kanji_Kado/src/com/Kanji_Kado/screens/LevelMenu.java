package com.Kanji_Kado.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LevelMenu implements Screen{

	private Stage stage;
	private Table table;
	private TextureAtlas atlas;
	private Skin skin;
	private List list;
	private ScrollPane scrollPane;
	private TextButton play, back;
	
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		
		//table.drawDebug(stage);
		
	}

	@Override
	public void resize(int width, int height) 
	{
		stage.setViewport(width, height, false);
		table.setClip(true);
		setupTable();
		
	}

	@Override
	public void show() 
	{
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlas);
		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//table.debug();
		
		list = new List(new String[] {"one", "two", "three", "four", "five", "six", "seven", "two", "three", "four", "five", "six", "seven"
				, "two", "three", "four", "five", "six", "seven", "two", "three", "four", "five", "six", "seven"}, skin);
		
		scrollPane = new ScrollPane(list, skin);
		
		play = new TextButton("PLAY", skin);
		play.pad(15);
		
		back = new TextButton("BACK", skin);
		back.addListener(new ClickListener() 
		{
			@Override
			public void clicked(InputEvent event, float x, float y) 
			{
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
			}
		});
		back.pad(15);
		
		setupTable();
		
		stage.addActor(table);
		
	}
	
	// Adds everything in the table and resizes it. In an extra method for easier resizing because of table.getWidth() / 3
	private void setupTable()
	{
		table.clear(); // removes all children so we can add them again
		table.setBounds(0, 0, stage.getWidth(), stage.getHeight());
		table.add(new Label("Select Levels", skin)).colspan(3).expandX().spaceBottom(50).row();
		table.add().width(table.getWidth() / 3); // adding three empty cells just for looks
		table.add().width(table.getWidth() / 3);
		table.add().width(table.getWidth() / 3).row();
		table.add(scrollPane).expandY().top().left();
		table.add(play);
		table.add(back).bottom().right();
		
	}

	@Override
	public void hide() 
	{
		dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() 
	{
		stage.dispose();
		atlas.dispose();
		skin.dispose();
	}

}
