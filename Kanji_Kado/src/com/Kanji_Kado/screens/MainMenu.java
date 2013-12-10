package com.Kanji_Kado.screens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.Kanji_Kado.KanjiKadoGame;
import com.Kanji_Kado.tween.ActorAccessor;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenu implements Screen 
{

	private Stage stage; // done
	private TextureAtlas atlas; //done
	private Skin skin; //Appearance of buttons and ui DONE
	private Table table; //done
	private TextButton buttonExit, buttonPlay;
	private Label heading;
	private TweenManager tweenManager;


	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta); //update everything in the stage
		stage.draw(); //everything draws

		tweenManager.update(delta);

	}

	@Override
	public void resize(int width, int height) 
	{
		stage.setViewport(width, height, true); //important for resizing
		table.invalidateHierarchy();
		table.setSize(width, height);

	}

	@Override
	public void show() 
	{

		stage = new Stage();

		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlas);

		table = new Table(skin); //dont have to put skin, but good practice
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		heading = new Label(KanjiKadoGame.TITLE, skin, "big");
		heading.setFontScale(2);

		buttonPlay = new TextButton("Play", skin);
		buttonPlay.addListener(new ClickListener() 
		{
			@Override
			public void clicked(InputEvent event, float x, float y) 
			{
				((Game) Gdx.app.getApplicationListener()).setScreen(new LevelMenu());

			}
		});

		TextButton buttonSettings = new TextButton("Settings", skin);
		buttonSettings.addListener(new ClickListener() 
		{
			@Override
			public void clicked(InputEvent event, float x, float y) 
			{
				stage.addAction(sequence(moveTo(0, -stage.getHeight(), .5f), run(new Runnable() 
				{
					@Override
					public void run() 
					{
						((Game) Gdx.app.getApplicationListener()).setScreen(new Settings());
					}
				})));
			}
		});

		buttonExit = new TextButton("Exit", skin); //reads the textbutton style from the skin
		buttonExit.addListener(new ClickListener() 
		{
			@Override
			public void clicked(InputEvent event, float x, float y) 
			{
				Gdx.app.exit();
			}
		});
		buttonExit.pad(15); // padding around the text of the button

		// putting stuff together
		table.add(heading).spaceBottom(100).row();
		table.add(buttonPlay).spaceBottom(15).row();
		table.add(buttonSettings).spaceBottom(15).row();
		table.add(buttonExit);
		
		stage.addActor(table);

		//Creating Tween Animations
		tweenManager = new TweenManager(); //everything inside the table is an actor
		Tween.registerAccessor(Actor.class, new ActorAccessor());

		//heading color animation
		Timeline.createSequence().beginSequence()
		.push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(0, 0, 1))
		.push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(0, 1, 0))
		.push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(1, 0, 0))
		.push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(1, 1, 0))
		.push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(0, 1, 1))
		.push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(1, 0, 1))
		.push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(1, 1, 1))
		.end().repeat(Tween.INFINITY, 0).start(tweenManager);

		//heading and buttons fade-in
		Timeline.createSequence().beginSequence()
		.push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
		.push(Tween.from(heading, ActorAccessor.ALPHA, 0.25f).target(0))
		//create timeline that's sequencial, add a from animaiotion that adjusts it's transparency from 0 to 1.
		.push(Tween.to(buttonPlay, ActorAccessor.ALPHA, 0.25f).target(1))
		.push(Tween.to(buttonExit, ActorAccessor.ALPHA, 0.25f).target(1))
		.end().start(tweenManager); //you must END the begin...()

		//table fade-in
		Tween.from(table, ActorAccessor.ALPHA, 0.5f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, 1f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);
		
		tweenManager.update(Float.MIN_VALUE);

	}

	@Override
	public void hide() 
	{
		dispose();
	}

	@Override
	public void pause() {


	}

	@Override
	public void resume() {


	}

	@Override
	public void dispose() 
	{
		stage.dispose();
		atlas.dispose();
		skin.dispose();
	}

}
