package com.github.donttouchit.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.donttouchit.DontTouchIt;

public class MenuScreen extends BasicScreen {
	private Stage stage = new Stage();
	private VerticalGroup buttonGroup = new VerticalGroup();
	private Button playButton;
	private Button exitButton;

	public MenuScreen(DontTouchIt game) {
		super(game);
		TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
		style.font = Fonts.menuFont;
		style.downFontColor = Color.ORANGE;
		style.fontColor = Color.YELLOW;
		playButton = new TextButton("Play", style);
		exitButton = new TextButton("Exit", style);

		stage.addActor(buttonGroup);
		buttonGroup.addActor(playButton);
		buttonGroup.addActor(exitButton);

		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				getGame().setScreen(new GameScreen(getGame()));
			}
		});

		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});

		buttonGroup.pack();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		float x = (Gdx.graphics.getWidth() - buttonGroup.getWidth()) / 2;
		float y = (Gdx.graphics.getHeight() - buttonGroup.getHeight()) / 2;
		buttonGroup.setPosition(x, y);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show() {
		System.err.println("Showing MenuScreen");
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	protected void draw() {
		super.draw();
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	protected void update(float delta) {
		super.update(delta);
		stage.act(delta);
	}
}
